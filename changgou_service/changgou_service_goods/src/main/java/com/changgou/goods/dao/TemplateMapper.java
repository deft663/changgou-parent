package com.changgou.goods.dao;
import com.changgou.goods.pojo.Template;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:传智播客
 * @Description:Template的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface TemplateMapper extends Mapper<Template> {
    @Select("select * from  tb_template t1,tb_category t2 where t2.template_id=t1.id and t2.id=#{id}")
    Template findByCateGoryId(Integer id);
}
