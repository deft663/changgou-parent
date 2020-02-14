package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.BrandService;
import com.changgou.goods.service.CategoryService;
import com.changgou.goods.service.SpuService;
import com.changgou.goods.vo.Goods;
import com.changgou.util.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:传智播客
 * @Description:Spu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private IdWorker idWorker;
    /**
     * Spu条件+分页查询
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(spu);
        //执行搜索
        return new PageInfo<Spu>(spuMapper.selectByExample(example));
    }

    /**
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Spu>(spuMapper.selectAll());
    }

    /**
     * Spu条件查询
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu){
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     * @param spu
     * @return
     */
    public Example createExample(Spu spu){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(spu!=null){
            // 主键
            if(!StringUtils.isEmpty(spu.getId())){
                    criteria.andEqualTo("id",spu.getId());
            }
            // 货号
            if(!StringUtils.isEmpty(spu.getSn())){
                    criteria.andEqualTo("sn",spu.getSn());
            }
            // SPU名
            if(!StringUtils.isEmpty(spu.getName())){
                    criteria.andLike("name","%"+spu.getName()+"%");
            }
            // 副标题
            if(!StringUtils.isEmpty(spu.getCaption())){
                    criteria.andEqualTo("caption",spu.getCaption());
            }
            // 品牌ID
            if(!StringUtils.isEmpty(spu.getBrandId()+"")){
                    criteria.andEqualTo("brandId",spu.getBrandId());
            }
            // 一级分类
            if(!StringUtils.isNotBlank(spu.getCategory1Id()+"")){
                    criteria.andEqualTo("category1Id",spu.getCategory1Id());
            }
            // 二级分类
            if(!StringUtils.isEmpty(spu.getCategory2Id()+"")){
                    criteria.andEqualTo("category2Id",spu.getCategory2Id());
            }
            // 三级分类
            if(!StringUtils.isEmpty(spu.getCategory3Id()+"")){
                    criteria.andEqualTo("category3Id",spu.getCategory3Id());
            }
            // 模板ID
            if(!StringUtils.isEmpty(spu.getTemplateId()+"")){
                    criteria.andEqualTo("templateId",spu.getTemplateId());
            }
            // 运费模板id
            if(!StringUtils.isEmpty(spu.getFreightId()+"")){
                    criteria.andEqualTo("freightId",spu.getFreightId());
            }
            // 图片
            if(!StringUtils.isEmpty(spu.getImage())){
                    criteria.andEqualTo("image",spu.getImage());
            }
            // 图片列表
            if(!StringUtils.isEmpty(spu.getImages())){
                    criteria.andEqualTo("images",spu.getImages());
            }
            // 售后服务
            if(!StringUtils.isEmpty(spu.getSaleService())){
                    criteria.andEqualTo("saleService",spu.getSaleService());
            }
            // 介绍
            if(!StringUtils.isEmpty(spu.getIntroduction())){
                    criteria.andEqualTo("introduction",spu.getIntroduction());
            }
            // 规格列表
            if(!StringUtils.isEmpty(spu.getSpecItems())){
                    criteria.andEqualTo("specItems",spu.getSpecItems());
            }
            // 参数列表
            if(!StringUtils.isEmpty(spu.getParaItems())){
                    criteria.andEqualTo("paraItems",spu.getParaItems());
            }
            // 销量
            if(!StringUtils.isEmpty(spu.getSaleNum()+"")){
                    criteria.andEqualTo("saleNum",spu.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(spu.getCommentNum()+"")){
                    criteria.andEqualTo("commentNum",spu.getCommentNum());
            }
            // 是否上架
            if(!StringUtils.isEmpty(spu.getIsMarketable())){
                    criteria.andEqualTo("isMarketable",spu.getIsMarketable());
            }
            // 是否启用规格
            if(!StringUtils.isEmpty(spu.getIsEnableSpec())){
                    criteria.andEqualTo("isEnableSpec",spu.getIsEnableSpec());
            }
            // 是否删除
            if(!StringUtils.isEmpty(spu.getIsDelete())){
                    criteria.andEqualTo("isDelete",spu.getIsDelete());
            }
            // 审核状态
            if(!StringUtils.isEmpty(spu.getStatus())){
                    criteria.andEqualTo("status",spu.getStatus());
            }
            example.orderBy("updateTime").desc();
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        spuMapper.deleteByPrimaryKey(id);
        Sku sku=new Sku();
        sku.setSpuId(id);
        skuMapper.delete(sku);
    }

    /**
     * 修改Spu
     * @param spu
     */
    @Override
    public void update(Spu spu){
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 增加Spu
     * @param spu
     */
    @Override
    public void add(Spu spu){
        spuMapper.insert(spu);
    }

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id){
        return  spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spu全部数据
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    @Override
    public void add(Goods goods) {
        long spuId = idWorker.nextId();
        Date date = new Date();
        Spu spu = goods.getSpu();
        spu.setId(String.valueOf(spuId));
        spu.setInsertTime(date);
        spu.setUpdateTime(date);
        spuMapper.insertSelective(spu);

        List<Sku> skus = goods.getSkus();
        Category category = categoryService.findById(spu.getCategory3Id());
        Brand brand = brandService.findById(spu.getBrandId());
        for (Sku sku : skus) {
            sku.setId(String.valueOf(idWorker.nextId()));
            sku.setSn(spu.getSn());
            String skuName=spu.getName();
            String spec = sku.getSpec();
            if(StringUtils.isNotBlank(spec)){
                Map<String,String> map = JSON.parseObject(spec, Map.class);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    skuName+=" "+entry.getValue();
                }
            }
            sku.setName(skuName);
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());
            skuMapper.insertSelective(sku);
        }
    }

    @Override
    public Goods findSpuById(String id) {
        Goods goods=new Goods();
        //调用SpuService实现根据主键查询Spu
        Spu spu = spuMapper.selectByPrimaryKey(id);
        goods.setSpu(spu);
        Sku sku=new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> skus = skuMapper.select(sku);
        goods.setSkus(skus);
        return goods;
    }

    @Override
    public void updateGoods(Goods goods) {
        Date date = new Date();
        Spu spu=goods.getSpu();
        goods.getSpu().setUpdateTime(date);
        spuMapper.updateByPrimaryKey(goods.getSpu());
        Sku sku1=new Sku();
        sku1.setSpuId(spu.getId());
        skuMapper.delete(sku1);

        List<Sku> skus = goods.getSkus();
        Category category = categoryService.findById(spu.getCategory3Id());
        Brand brand = brandService.findById(spu.getBrandId());

        for (Sku sku : skus) {
            sku.setId(String.valueOf(idWorker.nextId()));
            String skuName=spu.getName();
            String spec = sku.getSpec();
            if(StringUtils.isNotBlank(spec)){
                Map<String,String> map = JSON.parseObject(spec, Map.class);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    skuName+=" "+entry.getValue();
                }
            }
            sku.setName(skuName);
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());
            skuMapper.insertSelective(sku);
        }
    }

    @Override
    public void auditGoods(Long[] ids) {

        Arrays.asList(ids).forEach(id->{
            //查询spu对象
            Spu spu = spuMapper.selectByPrimaryKey(id);
            if (spu == null){
                throw new RuntimeException("当前商品不存在");
            }
            //判断当前spu是否处于删除状态
            if ("1".equals(spu.getIsDelete())){
                throw new RuntimeException("当前商品处于删除状态");
            }
            //不处于删除状态,修改审核状态为1,上下架状态为1
            spu.setStatus("1");
            spu.setIsMarketable("1");
            //执行修改操作
            spuMapper.updateByPrimaryKeySelective(spu);
        });
    }
}
