package com.autmaple.mall.tiny.nosql.elasticsearch.document;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @ClassName EsProductAttributeValue
 * @Description 搜索中的商品属性信息
 * @Author AutMaple
 * @Date 2022/6/19 20:11
 * @Version 1.0
 **/
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long productAttributeId;
    @Field(type = FieldType.Keyword)
    private String value; // 属性值
    private Integer type; // 属性参数: 0-> 规格， 1 -> 参数
    @Field(type = FieldType.Keyword)
    private String name; // 属性名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
