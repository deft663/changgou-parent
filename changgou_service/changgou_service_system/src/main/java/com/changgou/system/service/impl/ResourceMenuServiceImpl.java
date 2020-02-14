package com.changgou.system.service.impl;
import com.changgou.system.dao.ResourceMenuMapper;
import com.changgou.system.pojo.ResourceMenu;
import com.changgou.system.service.ResourceMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:ResourceMenu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ResourceMenuServiceImpl implements ResourceMenuService {

    @Autowired
    private ResourceMenuMapper resourceMenuMapper;


    /**
     * ResourceMenu条件+分页查询
     * @param resourceMenu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<ResourceMenu> findPage(ResourceMenu resourceMenu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(resourceMenu);
        //执行搜索
        return new PageInfo<ResourceMenu>(resourceMenuMapper.selectByExample(example));
    }

    /**
     * ResourceMenu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<ResourceMenu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<ResourceMenu>(resourceMenuMapper.selectAll());
    }

    /**
     * ResourceMenu条件查询
     * @param resourceMenu
     * @return
     */
    @Override
    public List<ResourceMenu> findList(ResourceMenu resourceMenu){
        //构建查询条件
        Example example = createExample(resourceMenu);
        //根据构建的条件查询数据
        return resourceMenuMapper.selectByExample(example);
    }


    /**
     * ResourceMenu构建查询对象
     * @param resourceMenu
     * @return
     */
    public Example createExample(ResourceMenu resourceMenu){
        Example example=new Example(ResourceMenu.class);
        Example.Criteria criteria = example.createCriteria();
        if(resourceMenu!=null){
            // 
            if(!StringUtils.isEmpty(resourceMenu.getResourceId())){
                    criteria.andEqualTo("resourceId",resourceMenu.getResourceId());
            }
            // 
            if(!StringUtils.isEmpty(resourceMenu.getMenuId())){
                    criteria.andEqualTo("menuId",resourceMenu.getMenuId());
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
        resourceMenuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改ResourceMenu
     * @param resourceMenu
     */
    @Override
    public void update(ResourceMenu resourceMenu){
        resourceMenuMapper.updateByPrimaryKey(resourceMenu);
    }

    /**
     * 增加ResourceMenu
     * @param resourceMenu
     */
    @Override
    public void add(ResourceMenu resourceMenu){
        resourceMenuMapper.insert(resourceMenu);
    }

    /**
     * 根据ID查询ResourceMenu
     * @param id
     * @return
     */
    @Override
    public ResourceMenu findById(Integer id){
        return  resourceMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询ResourceMenu全部数据
     * @return
     */
    @Override
    public List<ResourceMenu> findAll() {
        return resourceMenuMapper.selectAll();
    }
}
