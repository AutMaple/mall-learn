package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OmsOrderOperateHistoryDao
 * @Description 订单操作记录自定 Dao
 * @Author AutMaple
 * @Date 2022/7/13 21:27
 * @Version 1.0
 **/
public interface OmsOrderOperateHistoryDao {

    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/13 21:28
     **/
    int insertLit(@Param("list") List<OmsOrderOperateHistory> orderOperateHistoryList);
}
