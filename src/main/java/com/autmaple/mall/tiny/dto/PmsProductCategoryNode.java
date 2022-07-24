package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.PmsProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName PmsProductCategoryNode
 * @Description 包含子分类的商品分类
 * @Author AutMaple
 * @Date 2022/7/24 10:54
 * @Version 1.0
 **/
public class PmsProductCategoryNode extends PmsProductCategory {
    @Schema(description="子分类集合")
    private List<PmsProductCategoryNode> children;

    public List<PmsProductCategoryNode> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategoryNode> children) {
        this.children = children;
    }
}
