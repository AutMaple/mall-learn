package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dao.SmsFlashPromotionProductRelationDao;
import com.autmaple.mall.tiny.dto.SmsFlashPromotionProduct;
import com.autmaple.mall.tiny.mbg.mapper.SmsFlashPromotionProductRelationMapper;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionProductRelation;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionProductRelationExample;
import com.autmaple.mall.tiny.service.SmsFlashPromotionProductRelationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionProductRelationServiceImpl
 * @Description 限时购商品关联管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/18 20:51
 * @Version 1.0
 **/
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {

    @Autowired
    private SmsFlashPromotionProductRelationMapper flashPromotionProductRelationMapper;

    @Autowired
    private SmsFlashPromotionProductRelationDao flashPromotionProductRelationDao;

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation relation : relationList) {
            flashPromotionProductRelationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return flashPromotionProductRelationMapper.updateByPrimaryKey(relation);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionProductRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return flashPromotionProductRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return flashPromotionProductRelationDao.getList(flashPromotionId, flashPromotionSessionId);
    }

    @Override
    public Long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        example.or()
                .andFlashPromotionIdEqualTo(flashPromotionId)
                .andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
        return flashPromotionProductRelationMapper.countByExample(example);
    }
}
