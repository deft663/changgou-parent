package com.changgou.goods.dao;
import com.changgou.goods.pojo.Para;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Para的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface ParaMapper extends Mapper<Para> {
    @Select("select * from tb_para where template_id=#{id}")
    List<Para> findByTemplateId(Integer id);
}
