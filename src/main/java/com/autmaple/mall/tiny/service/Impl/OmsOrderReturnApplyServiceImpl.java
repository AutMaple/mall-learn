package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.date.DateTime;
import com.autmaple.mall.tiny.dao.OmsOrderReturnApplyDao;
import com.autmaple.mall.tiny.dto.OmsOrderReturnApplyResult;
import com.autmaple.mall.tiny.dto.OmsReturnApplyQueryParam;
import com.autmaple.mall.tiny.dto.OmsUpdateStatusParam;
import com.autmaple.mall.tiny.mbg.mapper.OmsOrderReturnApplyMapper;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnApply;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnApplyExample;
import com.autmaple.mall.tiny.service.OmsOrderReturnApplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OmsOrderReturnApplyServiceImpl
 * @Description
 * @Author AutMaple
 * @Date 2022/7/15 21:33
 * @Version 1.0
 **/
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Autowired
    private OmsOrderReturnApplyDao returnApplyDao;

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return returnApplyDao.getList(queryParam);
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnApplyExample example = new OmsOrderReturnApplyExample();
        example.or().andIdIn(ids).andStatusEqualTo(3);
        return returnApplyMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if (status.equals(1)) {
            // 确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        } else if (status.equals(2)) {
            // 完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new DateTime());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        } else if (status.equals(3)) {
            // 拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleNote(statusParam.getHandleNote());
        } else {
            return 0;
        }
        return returnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return returnApplyDao.getDetail(id);
    }
}
