package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberBrandAttention;
import org.springframework.data.domain.Page;

/**
 * @ClassName MemberAttentionService
 * @Description 会员关注商品管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 11:40
 * @Version 1.0
 **/
public interface MemberAttentionService {
    /**
     * @Author AutMaple
     * @Description 添加关注
     * @Date 2022/7/23 11:42
     **/
    int add(MemberBrandAttention memberBrandAttention);

    /**
     * @Author AutMaple
     * @Description 取消关注
     * @Date 2022/7/23 11:46
     **/
    int delete(Long brandId);

    /**
     * @Author AutMaple
     * @Description 获取用户关注列表
     * @Date 2022/7/23 11:46
     **/
    Page<MemberBrandAttention> list(Integer pageSize, Integer pageNum);

    /**
     * @Author AutMaple
     * @Description 获取用户关注详情
     * @Date 2022/7/23 11:47
     **/
    MemberBrandAttention detail(Long brandId);

    /**
     * @Author AutMaple
     * @Description 清空关注列表
     * @Date 2022/7/23 11:47
     **/
    void clear();
}
