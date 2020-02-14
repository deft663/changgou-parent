package com.changgou.system.service.impl;

import com.changgou.system.dao.AdminMapper;
import com.changgou.system.dao.AdminRoleMapper;
import com.changgou.system.pojo.Admin;
import com.changgou.system.pojo.AdminRole;
import com.changgou.system.service.AdminService;
import com.changgou.system.vo.AdminVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Admin业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public boolean login(Admin admin) {
        //根据登录名查询管理员
        Admin admin1=new Admin();
        admin1.setLoginName(admin.getLoginName());
        admin1.setStatus("1");
        Admin admin2 = adminMapper.selectOne(admin1);//数据库查询出的对象
        if(admin2==null){
            return false;
        }else{
            //验证密码, Bcrypt为spring的包, 第一个参数为明文密码, 第二个参数为密文密码
            return BCrypt.checkpw(admin.getPassword(),admin2.getPassword());
        }
    }

    /**
     * Admin条件+分页查询
     * @param admin 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Admin> findPage(Admin admin, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(admin);
        //执行搜索
        return new PageInfo<Admin>(adminMapper.selectByExample(example));
    }

    /**
     * Admin分页查询
     *
     * @param map
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<AdminVo> findPage(Map map, int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<AdminVo>(adminMapper.selectAdminList(map));
    }

    /**
     * Admin条件查询
     * @param admin
     * @return
     */
    @Override
    public List<Admin> findList(Admin admin){
        //构建查询条件
        Example example = createExample(admin);
        //根据构建的条件查询数据
        return adminMapper.selectByExample(example);
    }


    /**
     * Admin构建查询对象
     * @param admin
     * @return
     */
    public Example createExample(Admin admin){
        Example example=new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        if(admin!=null){
            // 
            if(!StringUtils.isEmpty(admin.getId())){
                    criteria.andEqualTo("id",admin.getId());
            }
            // 用户名
            if(!StringUtils.isEmpty(admin.getLoginName())){
                    criteria.andEqualTo("loginName",admin.getLoginName());
            }
            // 密码
            if(!StringUtils.isEmpty(admin.getPassword())){
                    criteria.andEqualTo("password",admin.getPassword());
            }
            // 状态
            if(!StringUtils.isEmpty(admin.getStatus())){
                    criteria.andEqualTo("status",admin.getStatus());
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
        adminMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Admin
     * @param admin
     */
    @Override
    public void update(AdminVo admin){
        adminMapper.updateByPrimaryKeySelective(admin);
        AdminRole adminRole=new AdminRole();
        adminRole.setAdminId(admin.getId());
        adminRole.setRoleId(admin.getRoleId());
        adminRoleMapper.updateByPrimaryKeySelective(adminRole);
    }

    /**
     * 增加Admin
     * @param admin
     */
    @Override
    public void add(AdminVo admin){
        //密碼加密
        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(password);
        admin.setStatus("1");
        adminMapper.insert(admin);
        AdminRole adminRole=new AdminRole();
        adminRole.setAdminId(admin.getId());
        adminRole.setRoleId(admin.getRoleId());
        adminRoleMapper.insert(adminRole);
    }

    /**
     * 根据ID查询Admin
     * @param id
     * @return
     */
    @Override
    public Admin findById(Integer id){
        return  adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Admin全部数据
     * @return
     */
    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }
}
