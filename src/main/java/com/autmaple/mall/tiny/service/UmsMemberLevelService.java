package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.UmsMemberLevel;

import java.util.List;

/**
 * @ClassName UmsMemberLevelService
 * @Description 会员等级管理 Service
 * @Author AutMaple
 * @Date 2022/7/20 20:29
 * @Version 1.0
 **/
public interface UmsMemberLevelService {
    /**
     * @param defaultStatus 是否默认是会员
     * @Author AutMaple
     * @Description 获取所有会员等级
     * @Date 2022/7/20 20:31
     **/
    List<UmsMemberLevel> list(Integer defaultStatus);
}
