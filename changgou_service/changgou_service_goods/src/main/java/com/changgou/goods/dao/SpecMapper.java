package com.changgou.goods.dao;
import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Spec的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface SpecMapper extends Mapper<Spec> {
    @Select("select * from tb_spec where template_id=#{id} ")
    List<Spec> findByTemplateId(Integer id);
}
