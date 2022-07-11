package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.CmsSubjectMapper;
import com.autmaple.mall.tiny.mbg.model.CmsSubject;
import com.autmaple.mall.tiny.mbg.model.CmsSubjectExample;
import com.autmaple.mall.tiny.service.CmsSubjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CmsSubjectServiceImpl
 * @Description 商品专题管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/11 21:21
 * @Version 1.0
 **/
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Autowired
    CmsSubjectMapper subjectMapper;

    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectByExample(new CmsSubjectExample());
    }

    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        if (StringUtil.isNotEmpty(keyword)) {
            example.or().andTitleLike("%" + keyword + "%");
        }
        return subjectMapper.selectByExample(example);
    }
}
