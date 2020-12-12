package cn.mtianyan.order.stream;

import cn.mtianyan.enums.OrderStatusEnum;
import cn.mtianyan.order.mapper.OrderStatusMapper;
import cn.mtianyan.order.pojo.OrderStatus;
import cn.mtianyan.order.pojo.bo.OrderStatusCheckBO;
import cn.mtianyan.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by 半仙.
 */
@Slf4j
@EnableBinding(value = {
        CheckOrderTopic.class
})
public class CheckOrderConsumer {

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @StreamListener(CheckOrderTopic.INPUT)
    public void consumeOrderStatusMessage(OrderStatusCheckBO bean) {
        log.info("received order check request, orderId={}",
                bean.getOrderID());

        // 查询所有未付款订单，判断时间是否超时（1天），超时则关闭交易
        OrderStatus queryOrder = new OrderStatus();
        queryOrder.setOrderId(bean.getOrderID());
        queryOrder.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> list = orderStatusMapper.select(queryOrder);

        if (CollectionUtils.isEmpty(list)) {
            log.info("order paid or closed, orderId={}", bean.getOrderID());
            return;
        }

        // 获得订单创建时间
        Date createdTime = list.get(0).getCreatedTime();
        // 和当前时间进行对比
        int days = DateUtil.daysBetween(createdTime, new Date());
        if (days >= 1) {
            // 超过1天，关闭订单
            OrderStatus update = new OrderStatus();
            update.setOrderId(bean.getOrderID());
            update.setOrderStatus(OrderStatusEnum.CLOSE.type);
            update.setCloseTime(new Date());

            int count = orderStatusMapper.updateByPrimaryKey(update);
            log.info("Closed order, orderId={}, count={}",
                    bean.getOrderID(), count);
        }
    }
}
