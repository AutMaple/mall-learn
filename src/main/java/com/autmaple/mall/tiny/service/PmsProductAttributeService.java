package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.PmsProductAttributeParam;
import com.autmaple.mall.tiny.dto.ProductAttrInfo;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttribute;

import java.util.List;

/**
 * @ClassName PmsProductAttributeService
 * @Description 商品属性管理 Service
 * @Author AutMaple
 * @Date 2022/7/16 12:50
 * @Version 1.0
 **/
public interface PmsProductAttributeService {
    /**
     * @param cid  分类ID
     * @param type 0->规格； 1->参数
     * @Author AutMaple
     * @Description 根据分类分页获取商品属性
     * @Date 2022/7/16 12:51
     **/
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer PageNum);

    /**
     * @Author AutMaple
     * @Description 创建商品属性
     * @Date 2022/7/16 12:52
     **/
    int create(PmsProductAttributeParam productAttribute);

    /**
     * @Author AutMaple
     * @Description 更新商品属性
     * @Date 2022/7/16 12:53
     **/
    int update(Long id, PmsProductAttributeParam productAttribute);

    /**
     * @Author AutMaple
     * @Description 获取指定的商品属性
     * @Date 2022/7/16 12:53
     **/
    PmsProductAttribute getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 批量删除商品属性
     * @Date 2022/7/16 12:54
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 获取商品分类对应的属性列表
     * @Date 2022/7/16 12:55
     **/
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);

}
