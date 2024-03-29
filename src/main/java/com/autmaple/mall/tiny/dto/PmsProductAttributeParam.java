package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @ClassName PmsProductAttributeParam
 * @Description 商品属性参数
 * @Author AutMaple
 * @Date 2022/7/16 13:11
 * @Version 1.0
 **/
public class PmsProductAttributeParam {

    @Schema(description="属性分类 ID")
    @NotEmpty
    private Long productAttributeCategoryId;

    @Schema(description="属性名称")
    @NotEmpty
    private String name;

    @Schema(description="属性选择类型: 0->唯一；1->单选; 2->多选")
    @FlagValidator({"0", "1", "2"})
    private Integer selectType;

    @Schema(description="属性录入方式: 0->手工录入；1->从列表录入")
    @FlagValidator({"0", "1"})
    private Integer inputType;

    @Schema(description="可选值列表，以逗号隔开")
    private String inputList;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description="分类筛选样式: 0->普通;1->颜色")
    @FlagValidator({"0", "1"})
    private Integer filterType;

    @Schema(description="检索类型: 0->不需要进行检索; 1->关键字检索; 2->范围检索")
    @FlagValidator({"0", "1"})
    private Integer searchType;

    @Schema(description="相同属性产品是否关联: 0->不关联； 1->关联")
    @FlagValidator({"0", "1", "2"})
    private Integer relateStatus;

    @Schema(description="是否支持手动新增: 0->不支持; 1->支持")
    @FlagValidator({"0", "1"})
    private Integer handAddStatus;

    @Schema(description="属性的类型: 0->规格，1->参数")
    @FlagValidator({"0", "1"})
    private Integer type;

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getRelateStatus() {
        return relateStatus;
    }

    public void setRelateStatus(Integer relateStatus) {
        this.relateStatus = relateStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PmsProductAttributeParam that = (PmsProductAttributeParam) o;
        return Objects.equals(productAttributeCategoryId, that.productAttributeCategoryId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(selectType, that.selectType) &&
                Objects.equals(inputType, that.inputType) &&
                Objects.equals(inputList, that.inputList) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(filterType, that.filterType) &&
                Objects.equals(searchType, that.searchType) &&
                Objects.equals(relateStatus, that.relateStatus) &&
                Objects.equals(handAddStatus, that.handAddStatus) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productAttributeCategoryId, name, selectType, inputType, inputList, sort, filterType, searchType, relateStatus, handAddStatus, type);
    }
}
