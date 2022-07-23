package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.mbg.model.PmsProductFullReduction;
import com.autmaple.mall.tiny.mbg.model.PmsProductLadder;
import com.autmaple.mall.tiny.mbg.model.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName PromotionProduct
 * @Description 促销商品信息，包括 Sku，打折优惠，満减优惠
 * @Author AutMaple
 * @Date 2022/7/23 16:28
 * @Version 1.0
 **/
public class PromotionProduct extends PmsProduct {
    @ApiModelProperty("商品库存信息")
    private List<PmsSkuStock> skuStockList;
    @ApiModelProperty("商品打折信息")
    private List<PmsProductLadder> productLadderList;
    @ApiModelProperty("商品満减信息")
    private List<PmsProductFullReduction> productFullReductionList;

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<PmsProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }
}
