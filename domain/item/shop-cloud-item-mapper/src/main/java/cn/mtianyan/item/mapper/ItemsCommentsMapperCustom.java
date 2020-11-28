package cn.mtianyan.item.mapper;

import cn.mtianyan.my.mapper.MyMapper;
import cn.mtianyan.item.pojo.ItemsComments;
import cn.mtianyan.item.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    public void saveComments(Map<String, Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}