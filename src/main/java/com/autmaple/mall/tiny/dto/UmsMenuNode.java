package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.UmsMenu;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName UmsMenuNode
 * @Description
 * @Author AutMaple
 * @Date 2022/7/20 20:46
 * @Version 1.0
 **/
public class UmsMenuNode extends UmsMenu {
    @Schema(description="子级菜单")
    private List<UmsMenuNode> children;

    public List<UmsMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsMenuNode> children) {
        this.children = children;
    }
}
