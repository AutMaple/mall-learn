package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * @ClassName PmsProductQueryParam
 * @Description 商品查询参数
 * @Author AutMaple
 * @Date 2022/7/16 22:31
 * @Version 1.0
 **/
public class PmsProductQueryParam {

    @Schema(description="上架状态")
    private Integer publicStatus;

    @Schema(description="审核状态")
    private Integer verifyStatus;

    @Schema(description="商品名称模糊搜索关键字")
    private String keyword;

    @Schema(description="商品货号")
    private String productSn;

    @Schema(description="商品分类编号")
    private Long productCategoryId;

    @Schema(description="商品品牌编号")
    private Long brandId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PmsProductQueryParam that = (PmsProductQueryParam) o;
        return Objects.equals(publicStatus, that.publicStatus)
                && Objects.equals(verifyStatus, that.verifyStatus)
                && Objects.equals(keyword, that.keyword)
                && Objects.equals(productSn, that.productSn)
                && Objects.equals(productCategoryId, that.productCategoryId)
                && Objects.equals(brandId, that.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicStatus, verifyStatus, keyword, productSn, productCategoryId, brandId);
    }

    public Integer getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Integer publicStatus) {
        this.publicStatus = publicStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
