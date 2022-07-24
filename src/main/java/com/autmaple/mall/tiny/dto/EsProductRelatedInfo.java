package com.autmaple.mall.tiny.dto;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName EsProductRelatedInfo
 * @Description 搜索商品的关联信息，包括品牌名称，分类名称及属性
 * @Author AutMaple
 * @Date 2022/7/24 11:29
 * @Version 1.0
 **/
public class EsProductRelatedInfo {
    private List<String> brandNames;
    private List<String> productCategoryNames;
    private List<ProductAttr> productAttrs;

    public List<String> getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(List<String> brandNames) {
        this.brandNames = brandNames;
    }

    public List<String> getProductCategoryNames() {
        return productCategoryNames;
    }

    public void setProductCategoryNames(List<String> productCategoryNames) {
        this.productCategoryNames = productCategoryNames;
    }

    public List<ProductAttr> getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(List<ProductAttr> productAttrs) {
        this.productAttrs = productAttrs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsProductRelatedInfo that = (EsProductRelatedInfo) o;
        return Objects.equals(brandNames, that.brandNames) && Objects.equals(productCategoryNames, that.productCategoryNames) && Objects.equals(productAttrs, that.productAttrs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandNames, productCategoryNames, productAttrs);
    }

    public static class ProductAttr {
        private Long attrId;
        private String attrName;
        private List<String> attrValues;

        public Long getAttrId() {
            return attrId;
        }

        public void setAttrId(Long attrId) {
            this.attrId = attrId;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public List<String> getAttrValues() {
            return attrValues;
        }

        public void setAttrValues(List<String> attrValues) {
            this.attrValues = attrValues;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProductAttr that = (ProductAttr) o;
            return Objects.equals(attrId, that.attrId) && Objects.equals(attrName, that.attrName) && Objects.equals(attrValues, that.attrValues);
        }

        @Override
        public int hashCode() {
            return Objects.hash(attrId, attrName, attrValues);
        }
    }
}
