package com.autmaple.mall.tiny.mbg.model;

import java.io.Serializable;

public class CmsPrefrenceAreaProductRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long prefrenceAreaId;
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrefrenceAreaId() {
        return prefrenceAreaId;
    }

    public void setPrefrenceAreaId(Long prefrenceAreaId) {
        this.prefrenceAreaId = prefrenceAreaId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", prefrenceAreaId=").append(prefrenceAreaId);
        sb.append(", productId=").append(productId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}