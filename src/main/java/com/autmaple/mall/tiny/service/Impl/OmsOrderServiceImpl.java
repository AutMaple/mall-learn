package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.dao.OmsOrderDao;
import com.autmaple.mall.tiny.dao.OmsOrderOperateHistoryDao;
import com.autmaple.mall.tiny.dto.*;
import com.autmaple.mall.tiny.mbg.mapper.OmsOrderMapper;
import com.autmaple.mall.tiny.mbg.mapper.OmsOrderOperateHistoryMapper;
import com.autmaple.mall.tiny.mbg.model.OmsOrder;
import com.autmaple.mall.tiny.mbg.model.OmsOrderExample;
import com.autmaple.mall.tiny.mbg.model.OmsOrderOperateHistory;
import com.autmaple.mall.tiny.service.OmsOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OmsOrderServiceImpl
 * @Description 订单管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/13 20:46
 * @Version 1.0
 **/
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    @Autowired
    private OmsOrderMapper orderMapper;

    @Autowired
    private OmsOrderDao orderDao;

    @Autowired
    private OmsOrderOperateHistoryDao orderOperateHistoryDao;

    @Autowired
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;


    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderDao.getList(queryParam);
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = orderDao.delivery(deliveryParamList);
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream().map(omsOrderDeliveryParam -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(omsOrderDeliveryParam.getOrderId());
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(2);
            history.setNote("发货完成");
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.insertLit(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder order = new OmsOrder();
        order.setStatus(4);
        OmsOrderExample example = new OmsOrderExample();
        example.or().andDeleteStatusEqualTo(0).andIdIn(ids);
        int count = orderMapper.updateByExampleSelective(order, example);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭: " + note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryDao.insertLit(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder order = new OmsOrder();
        order.setDeleteStatus(1);
        OmsOrderExample example = new OmsOrderExample();
        example.or().andDeleteStatusEqualTo(0).andIdIn(ids);
        return orderMapper.updateByExampleSelective(order, example);
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        return orderDao.getDetail(id);
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {

        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息: " + note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }
}
