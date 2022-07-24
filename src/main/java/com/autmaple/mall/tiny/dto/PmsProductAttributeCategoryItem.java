package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProductAttribute;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName PmsProductAttributeCategoryItem
 * @Description 带有属性的商品属性分类
 * @Author AutMaple
 * @Date 2022/7/16 12:18
 * @Version 1.0
 **/
public class PmsProductAttributeCategoryItem {
    @Schema(description="商品属性列表")
    private List<PmsProductAttribute> productAttributeList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}
