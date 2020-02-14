package com.changgou.system.service.impl;
import com.changgou.system.dao.LoginLogMapper;
import com.changgou.system.pojo.LoginLog;
import com.changgou.system.service.LoginLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:LoginLog业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;


    /**
     * LoginLog条件+分页查询
     * @param loginLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<LoginLog> findPage(LoginLog loginLog, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(loginLog);
        //执行搜索
        return new PageInfo<LoginLog>(loginLogMapper.selectByExample(example));
    }

    /**
     * LoginLog分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<LoginLog> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<LoginLog>(loginLogMapper.selectAll());
    }

    /**
     * LoginLog条件查询
     * @param loginLog
     * @return
     */
    @Override
    public List<LoginLog> findList(LoginLog loginLog){
        //构建查询条件
        Example example = createExample(loginLog);
        //根据构建的条件查询数据
        return loginLogMapper.selectByExample(example);
    }


    /**
     * LoginLog构建查询对象
     * @param loginLog
     * @return
     */
    public Example createExample(LoginLog loginLog){
        Example example=new Example(LoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(loginLog!=null){
            // 
            if(!StringUtils.isEmpty(loginLog.getId())){
                    criteria.andEqualTo("id",loginLog.getId());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getLoginName())){
                    criteria.andEqualTo("loginName",loginLog.getLoginName());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getIp())){
                    criteria.andEqualTo("ip",loginLog.getIp());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getBrowserName())){
                    criteria.andEqualTo("browserName",loginLog.getBrowserName());
            }
            // 地区
            if(!StringUtils.isEmpty(loginLog.getLocation())){
                    criteria.andEqualTo("location",loginLog.getLocation());
            }
            // 登录时间
            if(!StringUtils.isEmpty(loginLog.getLoginTime())){
                    criteria.andEqualTo("loginTime",loginLog.getLoginTime());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        loginLogMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改LoginLog
     * @param loginLog
     */
    @Override
    public void update(LoginLog loginLog){
        loginLogMapper.updateByPrimaryKey(loginLog);
    }

    /**
     * 增加LoginLog
     * @param loginLog
     */
    @Override
    public void add(LoginLog loginLog){
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据ID查询LoginLog
     * @param id
     * @return
     */
    @Override
    public LoginLog findById(Integer id){
        return  loginLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询LoginLog全部数据
     * @return
     */
    @Override
    public List<LoginLog> findAll() {
        return loginLogMapper.selectAll();
    }
}
