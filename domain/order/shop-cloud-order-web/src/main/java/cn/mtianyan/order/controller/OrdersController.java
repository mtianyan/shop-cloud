package cn.mtianyan.order.controller;

import cn.mtianyan.controller.BaseController;
import cn.mtianyan.enums.OrderStatusEnum;
import cn.mtianyan.enums.PayMethod;
import cn.mtianyan.order.pojo.OrderStatus;
import cn.mtianyan.order.pojo.bo.OrderStatusCheckBO;
import cn.mtianyan.order.pojo.bo.PlaceOrderBO;
import cn.mtianyan.order.stream.CheckOrderTopic;
import cn.mtianyan.pojo.ShopCartBO;
import cn.mtianyan.order.pojo.bo.SubmitOrderBO;
import cn.mtianyan.order.pojo.vo.MerchantOrdersVO;
import cn.mtianyan.order.pojo.vo.OrderVO;
import cn.mtianyan.order.service.OrderService;
import cn.mtianyan.utils.CookieUtils;
import cn.mtianyan.pojo.MJSONResult;
import cn.mtianyan.utils.JsonUtils;
import cn.mtianyan.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    final static Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private CheckOrderTopic orderStatusProducer;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public MJSONResult create(
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
            && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type ) {
            return MJSONResult.errorMsg("支付方式不支持！");
        }

//        System.out.println(submitOrderBO.toString());

        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + submitOrderBO.getUserId());
        if (StringUtils.isBlank(shopcartJson)) {
            return MJSONResult.errorMsg("购物数据不正确");
        }

        List<ShopCartBO> shopcartList = JsonUtils.jsonToList(shopcartJson, ShopCartBO.class);

        // 1. 创建订单
        PlaceOrderBO orderBO = new PlaceOrderBO(submitOrderBO, shopcartList);
        OrderVO orderVO = orderService.createOrder(orderBO);
        String orderId = orderVO.getOrderId();

        // 2. 创建订单以后，移除购物车中已结算（已提交）的商品
        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        // 清理覆盖现有的redis汇总的购物数据
        shopcartList.removeAll(orderVO.getToBeRemovedShopcatdList());
        redisOperator.set(FOODIE_SHOPCART + ":" + submitOrderBO.getUserId(), JsonUtils.objectToJson(shopcartList));
        // 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, JsonUtils.objectToJson(shopcartList), true);

        // order status检查
        OrderStatusCheckBO msg = new OrderStatusCheckBO();
        msg.setOrderID(orderId);
        // 可以采用更短的Delay时间, 在consumer里面重新投递消息
        orderStatusProducer.output().send(
                MessageBuilder.withPayload(msg)
                        .setHeader("x-delay", 3600 * 24 * 1000 + 300 * 1000)
                        .build()
        );

        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("mtianyanUserId","mtianyan");
        headers.add("password","mtianyan");

        HttpEntity<MerchantOrdersVO> entity =
                new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<MJSONResult> responseEntity =
                restTemplate.postForEntity(paymentUrl,
                                            entity,
                                            MJSONResult.class);
        MJSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            logger.error("发送错误：{}", paymentResult.getMsg());
            return MJSONResult.errorMsg("支付中心订单创建失败，请联系管理员！");
        }

        return MJSONResult.ok(orderId);
    }

    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("getPaidOrderInfo")
    public MJSONResult getPaidOrderInfo(String orderId) {

        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return MJSONResult.ok(orderStatus);
    }
}
