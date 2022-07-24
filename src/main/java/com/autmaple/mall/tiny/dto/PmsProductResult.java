package com.autmaple.mall.tiny.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName PmsProductResult
 * @Description 查询单个商品修改后返回的结果
 * @Author AutMaple
 * @Date 2022/7/16 19:25
 * @Version 1.0
 **/
public class PmsProductResult extends PmsProductParam {
    @Schema(description="商品所选分类的父 id")
    private Long cateParentId;

    public Long getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(Long cateParentId) {
        this.cateParentId = cateParentId;
    }
}
