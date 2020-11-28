package cn.mtianyan.item.pojo.vo;

import cn.mtianyan.item.pojo.Items;
import cn.mtianyan.item.pojo.ItemsImg;
import cn.mtianyan.item.pojo.ItemsParam;
import cn.mtianyan.item.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品详情VO
 * Create By mtianyan
 * 2019/12/27 11:24
 */
public class ItemInfoVO {

    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }
}

