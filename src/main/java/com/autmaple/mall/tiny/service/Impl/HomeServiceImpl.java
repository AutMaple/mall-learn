package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.autmaple.mall.tiny.common.utils.DateUtil;
import com.autmaple.mall.tiny.dao.HomeDao;
import com.autmaple.mall.tiny.dto.FlashPromotionProduct;
import com.autmaple.mall.tiny.dto.HomeContentResult;
import com.autmaple.mall.tiny.dto.HomeFlashPromotion;
import com.autmaple.mall.tiny.mbg.mapper.*;
import com.autmaple.mall.tiny.mbg.model.*;
import com.autmaple.mall.tiny.service.HomeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName HomeServiceImpl
 * @Description 首页内容管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/23 10:17
 * @Version 1.0
 **/
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;

    @Autowired
    private HomeDao homeDao;

    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;

    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Autowired
    private CmsSubjectMapper subjectMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        result.setAdvertiseList(getHomeAdvertiseList());
        result.setBrandList(homeDao.getRecommendBrandList(0, 6));
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        result.setNewProductList(homeDao.getNewProductList(0, 4));
        result.setHotProductList(homeDao.getHotProductList(0, 4));
        result.setSubjectLis(homeDao.getRecommendSubjectList(0, 4));
        return result;
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.or().andTypeEqualTo(1).andStatusEqualTo(1);
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion promotion = new HomeFlashPromotion();
        // 获取当前秒杀活动
        Date now = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                promotion.setStartTime(flashPromotionSession.getStartTime());
                promotion.setEndTime(flashPromotionSession.getEndTime());

                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(promotion.getNextStartTime());
                if (nextSession != null) {
                    promotion.setNextStartTime(nextSession.getStartTime());
                    promotion.setNextEndTime(nextSession.getEndTime());
                }
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                promotion.setProductList(flashProductList);
            }
        }
        return promotion;
    }

    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.or().andStartTimeGreaterThan(date);
        example.setOrderByClause("start_time asc");
        List<SmsFlashPromotionSession> sessionList = flashPromotionSessionMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(sessionList))
            return sessionList.get(0);
        return null;
    }

    private SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        example.or().andStatusEqualTo(1)
                .andStartDateLessThanOrEqualTo(currDate)
                .andEndDateGreaterThanOrEqualTo(currDate);
        List<SmsFlashPromotion> promotionList = flashPromotionMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(promotionList))
            return promotionList.get(0);
        return null;
    }

    private SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.or()
                .andStatusEqualTo(1)
                .andStartTimeLessThanOrEqualTo(currTime)
                .andEndTimeGreaterThanOrEqualTo(currTime);
        List<SmsFlashPromotionSession> sessionList = flashPromotionSessionMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(sessionList))
            return sessionList.get(0);
        return null;
    }

    @Override
    public List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        example.or().andDeleteStatusEqualTo(0)
                .andPublishStatusEqualTo(1);
        return productMapper.selectByExample(example);

    }

    @Override
    public List<PmsProductCategory> getProductCategoryList(Long parentId) {
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.or().andShowStatusEqualTo(1)
                .andParentIdEqualTo(parentId);
        example.setOrderByClause("sort desc");
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CmsSubject> getSubjectList(Long categoryId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.or();
        criteria.andShowStatusEqualTo(1);
        if (categoryId != null)
            criteria.andCategoryIdEqualTo(categoryId);
        return subjectMapper.selectByExample(example);
    }

    @Override
    public List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getHotProductList(offset, pageSize);
    }

    @Override
    public List<PmsProduct> newProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getNewProductList(offset, pageSize);
    }
}
