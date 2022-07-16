package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.mapper.OmsOrderReturnReasonMapper;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnReason;
import com.autmaple.mall.tiny.mbg.model.OmsOrderReturnReasonExample;
import com.autmaple.mall.tiny.service.OmsOrderReturnReasonService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName OmsOrderReturnReasonServiceImpl
 * @Description 退货原因管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/16 08:27
 * @Version 1.0
 **/
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Autowired
    private OmsOrderReturnReasonMapper returnReasonMapper;

    @Override
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        return returnReasonMapper.insert(returnReason);
    }

    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return returnReasonMapper.updateByPrimaryKey(returnReason);
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.or().andIdIn(ids);
        return returnReasonMapper.deleteByExample(example);
    }

    @Override
    public List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.setOrderByClause("sort desc");
        return returnReasonMapper.selectByExample(example);
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if (!status.equals(0) && !status.equals(1)) {
            return 0;
        }
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setStatus(status);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.or().andIdIn(ids);
        return returnReasonMapper.updateByExampleSelective(returnReason, example);
    }

    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return returnReasonMapper.selectByPrimaryKey(id);
    }
}
