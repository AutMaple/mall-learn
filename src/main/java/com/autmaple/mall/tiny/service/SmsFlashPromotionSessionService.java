package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.SmsFlashPromotionSessionDetail;
import com.autmaple.mall.tiny.mbg.model.SmsFlashPromotionSession;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionSessionService
 * @Description 限时购活动场次管理 Service
 * @Author AutMaple
 * @Date 2022/7/18 21:25
 * @Version 1.0
 **/
public interface SmsFlashPromotionSessionService {

    /**
     * @Author AutMaple
     * @Description 添加场次
     * @Date 2022/7/18 21:26
     **/
    int create(SmsFlashPromotionSession promotionSession);

    /**
     * @Author AutMaple
     * @Description 修改场次
     * @Date 2022/7/18 21:26
     **/
    int update(Long id, SmsFlashPromotionSession promotionSession);

    /**
     * @Author AutMaple
     * @Description 修改场次启用状态
     * @Date 2022/7/18 21:27
     **/
    int updateStatus(Long id, Integer status);

    /**
     * @Author AutMaple
     * @Description 删除场次
     * @Date 2022/7/18 21:28
     **/
    int delete(Long id);

    /**
     * @Author AutMaple
     * @Description 获取详细情况
     * @Date 2022/7/18 21:29
     **/
    SmsFlashPromotionSession getItem(Long id);

    /**
     * @Author AutMaple
     * @Description 根据启用状态获取场次信息
     * @Date 2022/7/18 21:30
     **/
    List<SmsFlashPromotionSession> list();

    /**
     * @Author AutMaple
     * @Description 获取全部可选场次及数量
     * @Date 2022/7/18 21:33
     **/
    List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId);
}
