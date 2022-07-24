package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyParam;
import com.autmaple.mall.tiny.mbg.mapper.OmsOrderReturnApplyMapper;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnApply;
import com.autmaple.mall.tiny.service.OmsPortalOrderReturnApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName OmsPortalReturnApplyServiceImpl
 * @Description 订单退货申请 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/24 10:47
 * @Version 1.0
 **/
@Service
public class OmsPortalReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public int create(OmsOrderReturnApplyParam returnApply) {
        OmsOrderReturnApply realApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(returnApply,realApply);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        return returnApplyMapper.insert(realApply);
    }
}
