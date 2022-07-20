package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendSubject;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendSubjectService
 * @Description 首页推荐主题管理 Service
 * @Author AutMaple
 * @Date 2022/7/20 20:01
 * @Version 1.0
 **/
public interface SmsHomeRecommendSubjectService {
    /**
     * @Author AutMaple
     * @Description 添加首页推荐
     * @Date 2022/7/20 20:02
     **/
    int create(List<SmsHomeRecommendSubject> recommendSubjectList);

    /**
     * @Author AutMaple
     * @Description 修改排序
     * @Date 2022/7/20 20:02
     **/
    int updateSort(Long id, Integer sort);

    /**
     * @Author AutMaple
     * @Description 删除主题推荐
     * @Date 2022/7/20 20:03
     **/
    int delete(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 批量修改推荐状态
     * @Date 2022/7/20 20:03
     **/
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * @Author AutMaple
     * @Description 分页查询推荐
     * @Date 2022/7/20 20:04
     **/
    List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
