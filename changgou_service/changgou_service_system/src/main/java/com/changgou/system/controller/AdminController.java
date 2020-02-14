package com.changgou.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.system.pojo.Admin;
import com.changgou.system.service.AdminService;
import com.changgou.system.util.JwtUtil;
import com.changgou.system.vo.AdminVo;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    private static final String AUTHORIZE_TOKEN = "token";
    @Autowired
    private AdminService adminService;

    /**
     * 刷新token
     * @return
     */
    @GetMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request, HttpServletResponse response){
        //4. 获取请求头
        String token = request.getHeader(AUTHORIZE_TOKEN);
        try {
            Claims claims = JwtUtil.parseJWT(token);
            String newToken = JwtUtil.createJWT(UUID.randomUUID().toString(), claims.getSubject(), null);
            return new Result(newToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.INVAILD_TOKEN,"无效token");
        }
    }

    /**
     * 登录
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin, HttpServletRequest request, HttpServletResponse response){
        boolean login = adminService.login(admin);
        if(login){  //如果验证成功
            Map<String,String> info = new HashMap<>();
            info.put("username",admin.getLoginName());
            info.put("avatar","https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2841648446,236398816&fm=26&gp=0.jpg");
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), admin.getLoginName(), null);
            info.put("token",token);
            return new Result(true, StatusCode.OK,"登录成功",info);
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }
    /***
     * Admin分页条件搜索实现
     * @param jsonObject
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody JSONObject jsonObject, @PathVariable  int page, @PathVariable  int size){
        Map map=new HashMap();
        map.put("loginName",jsonObject.get("loginName"));
        map.put("roleId",jsonObject.get("roleId"));
        //调用AdminService实现分页条件查询Admin
        PageInfo<AdminVo> pageInfo = adminService.findPage(map, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }


    /***
     * 多条件搜索品牌数据
     * @param admin
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Admin>> findList(@RequestBody(required = false)  Admin admin){
        //调用AdminService实现条件查询Admin
        List<Admin> list = adminService.findList(admin);
        return new Result<List<Admin>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AdminService实现根据主键删除
        adminService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Admin数据
     * @param admin
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  AdminVo admin,@PathVariable Integer id){
        //设置主键值
        admin.setId(id);
        //调用AdminService实现修改Admin
        adminService.update(admin);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Admin数据
     * @param admin
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   AdminVo admin){
        //调用AdminService实现添加Admin
        adminService.add(admin);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Admin数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Admin> findById(@PathVariable Integer id){
        //调用AdminService实现根据主键查询Admin
        Admin admin = adminService.findById(id);
        return new Result<Admin>(true,StatusCode.OK,"查询成功",admin);
    }

    /***
     * 查询Admin全部数据
     * @return
     */
    @GetMapping
    public Result<List<Admin>> findAll(){
        //调用AdminService实现查询所有Admin
        List<Admin> list = adminService.findAll();
        return new Result<List<Admin>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
