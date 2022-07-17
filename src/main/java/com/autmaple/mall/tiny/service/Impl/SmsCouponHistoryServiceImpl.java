package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.SmsCouponHistoryMapper;
import com.autmaple.mall.tiny.mbg.model.SmsCouponHistory;
import com.autmaple.mall.tiny.mbg.model.SmsCouponHistoryExample;
import com.autmaple.mall.tiny.service.SmsCouponHistoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SmsCouponHistoryServiceImpl
 * @Description 优惠券领取记录管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/17 13:38
 * @Version 1.0
 **/
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;

    @Override
    public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.or();
        if (couponId != null)
            criteria.andCouponIdEqualTo(couponId);
        if (useStatus != null)
            criteria.andUseStatusEqualTo(useStatus);
        if (StrUtil.isNotEmpty(orderSn))
            criteria.andOrderSnEqualTo(orderSn);
        return couponHistoryMapper.selectByExample(example);
    }
}
