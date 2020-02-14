package com.changgou.system.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.system.pojo.ResourceMenu;
import com.changgou.system.service.ResourceMenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/resourceMenu")
@CrossOrigin
public class ResourceMenuController {

    @Autowired
    private ResourceMenuService resourceMenuService;

    /***
     * ResourceMenu分页条件搜索实现
     * @param resourceMenu
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  ResourceMenu resourceMenu, @PathVariable  int page, @PathVariable  int size){
        //调用ResourceMenuService实现分页条件查询ResourceMenu
        PageInfo<ResourceMenu> pageInfo = resourceMenuService.findPage(resourceMenu, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * ResourceMenu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ResourceMenuService实现分页查询ResourceMenu
        PageInfo<ResourceMenu> pageInfo = resourceMenuService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param resourceMenu
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<ResourceMenu>> findList(@RequestBody(required = false)  ResourceMenu resourceMenu){
        //调用ResourceMenuService实现条件查询ResourceMenu
        List<ResourceMenu> list = resourceMenuService.findList(resourceMenu);
        return new Result<List<ResourceMenu>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用ResourceMenuService实现根据主键删除
        resourceMenuService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改ResourceMenu数据
     * @param resourceMenu
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  ResourceMenu resourceMenu,@PathVariable Integer id){
        //设置主键值
        resourceMenu.setResourceId(id);
        //调用ResourceMenuService实现修改ResourceMenu
        resourceMenuService.update(resourceMenu);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增ResourceMenu数据
     * @param resourceMenu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   ResourceMenu resourceMenu){
        //调用ResourceMenuService实现添加ResourceMenu
        resourceMenuService.add(resourceMenu);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询ResourceMenu数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ResourceMenu> findById(@PathVariable Integer id){
        //调用ResourceMenuService实现根据主键查询ResourceMenu
        ResourceMenu resourceMenu = resourceMenuService.findById(id);
        return new Result<ResourceMenu>(true,StatusCode.OK,"查询成功",resourceMenu);
    }

    /***
     * 查询ResourceMenu全部数据
     * @return
     */
    @GetMapping
    public Result<List<ResourceMenu>> findAll(){
        //调用ResourceMenuService实现查询所有ResourceMenu
        List<ResourceMenu> list = resourceMenuService.findAll();
        return new Result<List<ResourceMenu>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
