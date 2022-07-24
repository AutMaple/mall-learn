package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.mbg.model.PmsProductAttribute;
import com.autmaple.mall.tiny.mbg.model.PmsSkuStock;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName CartProduct
 * @Description 购物车中带规格和 SKU 的商品的信息
 * @Author AutMaple
 * @Date 2022/7/23 16:19
 * @Version 1.0
 **/
public class CartProduct extends PmsProduct {
    @Schema(description = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
    @Schema(description="商品 SKU 库存列表")
    private List<PmsSkuStock> skuStockList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }
}
