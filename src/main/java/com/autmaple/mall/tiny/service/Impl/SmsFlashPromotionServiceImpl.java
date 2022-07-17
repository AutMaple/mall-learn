package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.SmsFlashPromotionMapper;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotion;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionExample;
import com.autmaple.mall.tiny.service.SmsFlashPromotionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SmsFlashPromotionServiceImpl
 * @Description 限时购活动管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/17 13:55
 * @Version 1.0
 **/
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {

    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        return flashPromotionMapper.insert(flashPromotion);
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return flashPromotionMapper.updateByPrimaryKeySelective(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setId(id);
        promotion.setStatus(status);
        return flashPromotionMapper.updateByPrimaryKey(promotion);

    }

    @Override
    public SmsFlashPromotion getItem(Long id) {
        return flashPromotionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        if (StrUtil.isNotEmpty(keyword))
            example.or().andTitleLike("%" + keyword + "%");
        return flashPromotionMapper.selectByExample(example);
    }
}
