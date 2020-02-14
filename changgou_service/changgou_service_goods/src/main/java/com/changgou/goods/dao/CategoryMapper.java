package com.changgou.goods.dao;
import com.changgou.goods.pojo.Category;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Category的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface CategoryMapper extends Mapper<Category> {
    List<Category> selectCategoryList(Category category);
}