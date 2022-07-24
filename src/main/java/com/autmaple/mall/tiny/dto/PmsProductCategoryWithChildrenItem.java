package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName PmsProductCategoryWithChildrenItem
 * @Description 包含子级分类的商品分类
 * @Author AutMaple
 * @Date 2022/7/16 17:08
 * @Version 1.0
 **/
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @Schema(description="子级分类")
    private List<PmsProductCategory> children;

    public List<PmsProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategory> children) {
        this.children = children;
    }
}
