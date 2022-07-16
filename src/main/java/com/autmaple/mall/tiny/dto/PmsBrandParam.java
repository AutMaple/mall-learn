package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @ClassName PmsBrandParam
 * @Description 品牌请求参数
 * @Author AutMaple
 * @Date 2022/7/16 10:20
 * @Version 1.0
 **/
public class PmsBrandParam {
    @ApiModelProperty(value = "品牌名称", required = true)
    @NotEmpty
    private String name;

    @ApiModelProperty("品牌首字母")
    private String firstLetter;

    @ApiModelProperty("排序字段")
    @Min(value = 0)
    private Integer sort;

    @ApiModelProperty("是否是厂家制造商")
    @FlagValidator(value = {"0", "1"}, message = "厂家设置的状态不正确")
    private Integer factoryStatus;

    @ApiModelProperty("是否进行显示")
    @FlagValidator(value = {"0", "1"}, message = "显示状态不正确")
    private Integer showStatus;

    @ApiModelProperty(value = "品牌 logo", required = true)
    @NotEmpty
    private String logo;

    @ApiModelProperty(value = "品牌大图")
    private String bigPic;

    @ApiModelProperty("品牌故事")
    private String brandStory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PmsBrandParam that = (PmsBrandParam) o;
        return Objects.equals(name, that.name) && Objects.equals(firstLetter, that.firstLetter) && Objects.equals(sort, that.sort) && Objects.equals(factoryStatus, that.factoryStatus) && Objects.equals(showStatus, that.showStatus) && Objects.equals(logo, that.logo) && Objects.equals(bigPic, that.bigPic) && Objects.equals(brandStory, that.brandStory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstLetter, sort, factoryStatus, showStatus, logo, bigPic, brandStory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getBrandStory() {
        return brandStory;
    }

    public void setBrandStory(String brandStory) {
        this.brandStory = brandStory;
    }
}
