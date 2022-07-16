package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.PmsProductVertifyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsProductVerifyRecordDao
 * @Description 商品审核日志管理自定义Dao
 * @Author AutMaple
 * @Date 2022/7/16 21:11
 * @Version 1.0
 **/
@Mapper
public interface PmsProductVerifyRecordDao {
    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 21:12
     **/
    int insertList(@Param("list") List<PmsProductVertifyRecord> list);

}
