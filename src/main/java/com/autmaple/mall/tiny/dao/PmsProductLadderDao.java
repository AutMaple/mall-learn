package com.autmaple.mall.tiny.dao;

import com.autmaple.mall.tiny.mbg.model.PmsProductLadder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PmsProductLadderDao
 * @Description 会员阶梯价格自定义
 * @Author AutMaple
 * @Date 2022/7/16 19:49
 * @Version 1.0
 **/
@Mapper
public interface PmsProductLadderDao {
    /**
     * @Author AutMaple
     * @Description 批量创建
     * @Date 2022/7/16 19:51
     **/
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
