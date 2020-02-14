package com.changgou.system.service;
import com.changgou.system.pojo.ResourceMenu;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:ResourceMenu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface ResourceMenuService {

    /***
     * ResourceMenu多条件分页查询
     * @param resourceMenu
     * @param page
     * @param size
     * @return
     */
    PageInfo<ResourceMenu> findPage(ResourceMenu resourceMenu, int page, int size);

    /***
     * ResourceMenu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<ResourceMenu> findPage(int page, int size);

    /***
     * ResourceMenu多条件搜索方法
     * @param resourceMenu
     * @return
     */
    List<ResourceMenu> findList(ResourceMenu resourceMenu);

    /***
     * 删除ResourceMenu
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改ResourceMenu数据
     * @param resourceMenu
     */
    void update(ResourceMenu resourceMenu);

    /***
     * 新增ResourceMenu
     * @param resourceMenu
     */
    void add(ResourceMenu resourceMenu);

    /**
     * 根据ID查询ResourceMenu
     * @param id
     * @return
     */
     ResourceMenu findById(Integer id);

    /***
     * 查询所有ResourceMenu
     * @return
     */
    List<ResourceMenu> findAll();
}
