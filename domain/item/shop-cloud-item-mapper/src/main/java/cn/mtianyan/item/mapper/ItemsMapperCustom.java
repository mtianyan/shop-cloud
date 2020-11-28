package cn.mtianyan.item.mapper;

import cn.mtianyan.item.pojo.vo.ItemCommentVO;
//import cn.mtianyan.item.pojo.vo.SearchItemsVO;
import cn.mtianyan.item.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create By mtianyan
 * 2019/12/27 13:55
 */
public interface ItemsMapperCustom {

    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    // FIXME 将搜索划分到主搜food-search模块
//    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);
//
//    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    public List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);

    public int decreaseItemSpecStock(@Param("specId") String specId,
                                     @Param("pendingCounts") int pendingCounts);
}