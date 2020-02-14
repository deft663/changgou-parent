package com.changgou.system.dao;

import com.changgou.system.pojo.Admin;
import com.changgou.system.vo.AdminVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Admin的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface AdminMapper extends Mapper<Admin> {
    List<AdminVo> selectAdminList(Map map);
}
