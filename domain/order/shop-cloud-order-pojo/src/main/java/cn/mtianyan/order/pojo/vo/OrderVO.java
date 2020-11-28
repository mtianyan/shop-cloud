package cn.mtianyan.order.pojo.vo;

import cn.mtianyan.pojo.ShopCartBO;
import java.util.List;

public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;

    private List<ShopCartBO> toBeRemovedShopcatdList;

    public List<ShopCartBO> getToBeRemovedShopcatdList() {
        return toBeRemovedShopcatdList;
    }

    public void setToBeRemovedShopcatdList(List<ShopCartBO> toBeRemovedShopcatdList) {
        this.toBeRemovedShopcatdList = toBeRemovedShopcatdList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public MerchantOrdersVO getMerchantOrdersVO() {
        return merchantOrdersVO;
    }

    public void setMerchantOrdersVO(MerchantOrdersVO merchantOrdersVO) {
        this.merchantOrdersVO = merchantOrdersVO;
    }
}