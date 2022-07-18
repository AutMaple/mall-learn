package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dto.SmsFlashPromotionSessionDetail;
import com.autmaple.mall.tiny.mbg.mapper.SmsFlashPromotionSessionMapper;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionSession;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionSessionExample;
import com.autmaple.mall.tiny.service.SmsFlashPromotionProductRelationService;
import com.autmaple.mall.tiny.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SmsFlashPromotionSessionServiceImpl
 * @Description 限时购场次管理 Service
 * @Author AutMaple
 * @Date 2022/7/18 21:34
 * @Version 1.0
 **/
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {

    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;

    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @Override
    public int create(SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(new Date());
        return flashPromotionSessionMapper.insert(promotionSession);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        return flashPromotionSessionMapper.updateByPrimaryKey(promotionSession);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        return flashPromotionSessionMapper.updateByPrimaryKeySelective(promotionSession);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionSessionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionSession getItem(Long id) {
        return flashPromotionSessionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        return flashPromotionSessionMapper.selectByExample(example);
    }

    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.or().andStatusEqualTo(1);
        List<SmsFlashPromotionSession> sessionList = flashPromotionSessionMapper.selectByExample(example);
        for (SmsFlashPromotionSession session : sessionList) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(session, detail);
            Long count = relationService.getCount(flashPromotionId, session.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }
}
