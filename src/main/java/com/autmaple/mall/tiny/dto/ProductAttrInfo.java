package com.autmaple.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @ClassName ProductAttrInfo
 * @Description 商品分类对应的属性信息
 * @Author AutMaple
 * @Date 2022/7/16 12:55
 * @Version 1.0
 **/
public class ProductAttrInfo {
    @ApiModelProperty("商品属性 ID")
    private Long attributeId;

    @ApiModelProperty("商品属性分类 ID")
    private Long attributeCategoryId;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAttrInfo that = (ProductAttrInfo) o;
        return Objects.equals(attributeId, that.attributeId) && Objects.equals(attributeCategoryId, that.attributeCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeId, attributeCategoryId);
    }
}
