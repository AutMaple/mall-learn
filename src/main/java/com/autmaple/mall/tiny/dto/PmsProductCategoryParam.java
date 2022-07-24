package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName PmsProductCategoryParam
 * @Description 商品分类管理传输对象
 * @Author AutMaple
 * @Date 2022/7/16 16:55
 * @Version 1.0
 **/
public class PmsProductCategoryParam {
    @Schema(description="父分类的编号")
    private Long parentId;

    @Schema(description= "商品分类名称", required = true)
    @NotEmpty
    private String name;

    @Schema(description="分类单位")
    private String productUnit;

    @Schema(description="是否显示在导航栏上")
    @FlagValidator(value = {"0", "1"}, message = "状态只能够是 0 或 1")
    private Integer navStatus;

    @Schema(description="是否进行显示")
    @FlagValidator(value = {"0", "1"}, message = "状态只能够是 0 或 1")
    private Integer showStatus;

    @Schema(description="排序")
    @Min(value = 0)
    private Integer sort;

    @Schema(description="图标")
    private String icon;

    @Schema(description="关键字")
    private String keyword;

    @Schema(description="描述")
    private String description;

    @Schema(description="产品相关筛选信息属性集合")
    private List<Long> productAttributeIdList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PmsProductCategoryParam that = (PmsProductCategoryParam) o;
        return Objects.equals(parentId, that.parentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(productUnit, that.productUnit) &&
                Objects.equals(navStatus, that.navStatus) &&
                Objects.equals(showStatus, that.showStatus) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(keyword, that.keyword) &&
                Objects.equals(description, that.description) &&
                Objects.equals(productAttributeIdList, that.productAttributeIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, name, productUnit, navStatus, showStatus, sort, icon, keyword, description, productAttributeIdList);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public void setProductAttributeIdList(List<Long> productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }
}
