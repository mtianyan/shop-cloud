package cn.mtianyan.controller;

import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * Create By mtianyan
 * 2019/12/27 10:18
 */
@Controller
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    public static final String REDIS_USER_TOKEN = "redis_user_token";

    // 支付中心的调用地址
    public String paymentUrl = "http://payment.t.mukewang.com/shop-cloud-payment/payment/createMerchantOrder";        // produce

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    public String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";

    // 用户上传头像的位置
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
            File.separator + "images" +
            File.separator + "shop-cloud" +
            File.separator + "faces";
//    public static final String IMAGE_USER_FACE_LOCATION = "/workspaces/images/shop-cloud/faces";

// FIXME 下面逻辑移到订单中心
//    @Autowired
//    public MyOrdersService myOrdersService;
//
//    /**
//     * 用于验证用户和订单是否有关联关系，避免非法用户调用
//     *
//     * @return
//     */
//    public MJSONResult checkUserOrder(String userId, String orderId) {
//        Orders order = myOrdersService.queryMyOrder(userId, orderId);
//        if (order == null) {
//            return MJSONResult.errorMsg("订单不存在！");
//        }
//        return MJSONResult.ok(order);
//    }
//
//    public UsersVO conventUsersVO(Users user) {
//        // 实现用户的redis会话
//        String uniqueToken = UUID.randomUUID().toString().trim();
//        redisOperator.set(REDIS_USER_TOKEN + ":" + user.getId(),
//                uniqueToken);
//
//        UsersVO usersVO = new UsersVO();
//        BeanUtils.copyProperties(user, usersVO);
//        usersVO.setUserUniqueToken(uniqueToken);
//        return usersVO;
//    }
}

