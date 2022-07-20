package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.SmsHomeRecommendSubjectMapper;
import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendSubject;
import com.autmaple.mall.tiny.mbg.model.SmsHomeRecommendSubjectExample;
import com.autmaple.mall.tiny.service.SmsHomeRecommendSubjectService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsHomeRecommendSubjectServiceImpl
 * @Description 首页主题推荐管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/20 20:05
 * @Version 1.0
 **/
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Autowired
    private SmsHomeRecommendSubjectMapper subjectMapper;

    @Override
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        for (SmsHomeRecommendSubject subject : recommendSubjectList) {
            subject.setRecommendStatus(1);
            subject.setSort(0);
            subjectMapper.insert(subject);
        }
        return recommendSubjectList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setId(id);
        subject.setSort(sort);
        return subjectMapper.updateByPrimaryKey(subject);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.or().andIdIn(ids);
        return subjectMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendSubject subject = new SmsHomeRecommendSubject();
        subject.setRecommendStatus(recommendStatus);
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        example.or().andIdIn(ids);
        return subjectMapper.updateByExampleSelective(subject, example);
    }

    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
        SmsHomeRecommendSubjectExample.Criteria criteria = example.or();
        if(StrUtil.isNotEmpty(subjectName))
            criteria.andSubjectNameLike("%" + subjectName + "%");
        if(recommendStatus != null)
            criteria.andRecommendStatusEqualTo(recommendStatus);
        example.setOrderByClause("sort desc");
        return subjectMapper.selectByExample(example);
    }
}
