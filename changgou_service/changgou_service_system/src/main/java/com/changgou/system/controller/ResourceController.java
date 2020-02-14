package com.changgou.system.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.system.pojo.Resource;
import com.changgou.system.service.ResourceService;
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
@RequestMapping("/resource")
@CrossOrigin
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /***
     * Resource分页条件搜索实现
     * @param resource
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Resource resource, @PathVariable  int page, @PathVariable  int size){
        //调用ResourceService实现分页条件查询Resource
        PageInfo<Resource> pageInfo = resourceService.findPage(resource, page, size);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Resource分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ResourceService实现分页查询Resource
        PageInfo<Resource> pageInfo = resourceService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param resource
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Resource>> findList(@RequestBody(required = false)  Resource resource){
        //调用ResourceService实现条件查询Resource
        List<Resource> list = resourceService.findList(resource);
        return new Result<List<Resource>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用ResourceService实现根据主键删除
        resourceService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Resource数据
     * @param resource
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Resource resource,@PathVariable Integer id){
        //设置主键值
        resource.setId(id);
        //调用ResourceService实现修改Resource
        resourceService.update(resource);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Resource数据
     * @param resource
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Resource resource){
        //调用ResourceService实现添加Resource
        resourceService.add(resource);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Resource数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Resource> findById(@PathVariable Integer id){
        //调用ResourceService实现根据主键查询Resource
        Resource resource = resourceService.findById(id);
        return new Result<Resource>(true,StatusCode.OK,"查询成功",resource);
    }

    /***
     * 查询Resource全部数据
     * @return
     */
    @GetMapping
    public Result<List<Resource>> findAll(){
        //调用ResourceService实现查询所有Resource
        List<Resource> list = resourceService.findAll();
        return new Result<List<Resource>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
