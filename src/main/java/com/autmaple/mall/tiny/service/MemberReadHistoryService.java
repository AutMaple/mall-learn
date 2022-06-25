package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * @ClassName MemberReadHistoryService
 * @Description 会员浏览记录管理 Service
 * @Author AutMaple
 * @Date 2022/6/23 20:59
 * @Version 1.0
 **/
public interface MemberReadHistoryService {
    /**
     * @Author AutMaple
     * @Description 生成浏览记录
     * @Date 2022/6/23 21:00
     **/
    int create(MemberReadHistory memberReadHistory);

    /**
     * @Author AutMaple
     * @Description 批量的删除浏览纪律
     * @Date 2022/6/23 21:01
     **/
    int delete(List<String> ids);

    /**
     * @Author AutMaple
     * @Description 获取用户的浏览记录
     * @Date 2022/6/23 21:02
     **/
    List<MemberReadHistory> list(Long memberId);
}
