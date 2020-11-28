package cn.mtianyan.order.pojo.bo;

import cn.mtianyan.pojo.ShopCartBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderBO {

    private SubmitOrderBO order;

    private List<ShopCartBO> items;
}
