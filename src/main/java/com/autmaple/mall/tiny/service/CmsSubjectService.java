package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.CmsSubject;

import java.util.List;

/**
 * @ClassName CmsSubjectService
 * @Description 商品专题管理 Service
 * @Author AutMaple
 * @Date 2022/7/11 21:19
 * @Version 1.0
 **/
public interface CmsSubjectService {
    /**
     * @Author AutMaple
     * @Description 查询所有的专题
     * @Date 2022/7/11 21:20
     **/
    List<CmsSubject> listAll();

    /**
     * @Author AutMaple
     * @Description 分页查询专题
     * @Date 2022/7/11 21:21
     **/
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}
