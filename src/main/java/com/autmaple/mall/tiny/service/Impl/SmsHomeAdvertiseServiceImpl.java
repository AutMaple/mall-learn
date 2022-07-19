package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.autmaple.mall.tiny.mbg.mapper.SmsHomeAdvertiseMapper;
import com.autmaple.mall.tiny.mbg.model.SmsHomeAdvertise;
import com.autmaple.mall.tiny.mbg.model.SmsHomeAdvertiseExample;
import com.autmaple.mall.tiny.service.SmsHomeAdvertiseService;
import com.github.pagehelper.PageHelper;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SmsHomeAdvertiseServiceImpl
 * @Description 首页轮播图广告管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/19 20:02
 * @Version 1.0
 **/
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {

    @AutoConfigureOrder
    private SmsHomeAdvertiseMapper advertiseMapper;


    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return advertiseMapper.insert(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.or().andIdIn(ids);
        return advertiseMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setId(id);
        advertise.setStatus(status);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return advertiseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.or();
        if (StrUtil.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (type != null)
            criteria.andTypeEqualTo(type);
        if (StrUtil.isNotEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + "23:59:59";
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            Date end = null;
            try {
                start = sf.parse(startStr);
                end = sf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null)
                criteria.andEndTimeBetween(start, end);
        }
        example.setOrderByClause("sort desc");
        return advertiseMapper.selectByExample(example);
    }
}
