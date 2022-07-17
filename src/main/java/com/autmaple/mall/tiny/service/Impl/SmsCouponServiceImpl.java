package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.dao.SmsCouponDao;
import com.autmaple.mall.tiny.dao.SmsCouponProductCategoryRelationDao;
import com.autmaple.mall.tiny.dao.SmsCouponProductRelationDao;
import com.autmaple.mall.tiny.dto.SmsCouponParam;
import com.autmaple.mall.tiny.mbg.mapper.SmsCouponMapper;
import com.autmaple.mall.tiny.mbg.mapper.SmsCouponProductCategoryRelationMapper;
import com.autmaple.mall.tiny.mbg.mapper.SmsCouponProductRelationMapper;
import com.autmaple.mall.tiny.mbg.model.*;
import com.autmaple.mall.tiny.service.SmsCouponService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsCouponServiceImpl
 * @Description 优惠券管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/17 10:05
 * @Version 1.0
 **/
@Service
public class SmsCouponServiceImpl implements SmsCouponService {

    @Autowired
    private SmsCouponMapper couponMapper;
    @Autowired
    private SmsCouponDao couponDao;

    @Autowired
    private SmsCouponProductRelationMapper couponProductRelationMapper;
    @Autowired
    private SmsCouponProductRelationDao couponProductRelationDao;


    @Autowired
    private SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;
    @Autowired
    private SmsCouponProductCategoryRelationDao couponProductCategoryRelationDao;


    @Override
    public int create(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);

        // 插入优惠券
        int count = couponMapper.insert(couponParam);
        // 插入优惠券和商品关系
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            couponProductRelationDao.insertList(couponParam.getProductRelationList());
        }
        // 插入优惠券于商品分类关系
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation categoryRelation : couponParam.getProductCategoryRelationList()) {
                categoryRelation.setCouponId(couponParam.getId());
            }
            couponProductCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = couponMapper.deleteByPrimaryKey(id);
        // 删除商品关联
        deleteProductRelation(id);
        // 删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }

    private void deleteProductCategoryRelation(Long id) {
        SmsCouponProductCategoryRelationExample example = new SmsCouponProductCategoryRelationExample();
        example.or().andCouponIdEqualTo(id);
        couponProductCategoryRelationMapper.deleteByExample(example);
    }

    private void deleteProductRelation(Long id) {
        SmsCouponProductRelationExample example = new SmsCouponProductRelationExample();
        example.or().andCouponIdEqualTo(id);
        couponProductRelationMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        couponParam.setId(id);
        int count = couponMapper.updateByPrimaryKey(couponParam);
        // 删除后插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            couponProductRelationDao.insertList(couponParam.getProductRelationList());
        }
        // 删除后插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation categoryRelation : couponParam.getProductCategoryRelationList()) {
                categoryRelation.setCouponId(id);
            }
            deleteProductCategoryRelation(id);
            couponProductCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        SmsCouponExample example = new SmsCouponExample();
        SmsCouponExample.Criteria criteria = example.or();
        if (StrUtil.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (type != null)
            criteria.andTypeEqualTo(type);
        PageHelper.startPage(pageNum, pageSize);
        return couponMapper.selectByExample(example);
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        return couponDao.getItem(id);
    }
}
