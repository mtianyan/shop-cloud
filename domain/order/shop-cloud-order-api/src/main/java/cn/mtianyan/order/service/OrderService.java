package cn.mtianyan.order.service;

import cn.mtianyan.order.pojo.OrderStatus;
import cn.mtianyan.order.pojo.bo.PlaceOrderBO;
import cn.mtianyan.order.pojo.bo.SubmitOrderBO;
import cn.mtianyan.order.pojo.vo.OrderVO;
import cn.mtianyan.pojo.ShopCartBO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order-api")
public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param orderBO
     */
    @PostMapping("placeOrder")
    public OrderVO createOrder(@RequestBody PlaceOrderBO orderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    @PostMapping("updateStatus")
    public void updateOrderStatus(@RequestParam("orderId") String orderId,
                                  @RequestParam("orderStatus") Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    @GetMapping("orderStatus")
    public OrderStatus queryOrderStatusInfo(@RequestParam("orderId") String orderId);

    /**
     * 关闭超时未支付订单
     */
    @PostMapping("closePendingOrders")
    public void closeOrder();

}
