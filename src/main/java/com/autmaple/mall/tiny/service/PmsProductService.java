package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.PmsProductParam;
import com.autmaple.mall.tiny.dto.PmsProductQueryParam;
import com.autmaple.mall.tiny.dto.PmsProductResult;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;

import java.util.List;

/**
 * @ClassName PmsProductService
 * @Description 商品管理 Service
 * @Author AutMaple
 * @Date 2022/7/16 19:14
 * @Version 1.0
 **/
public interface PmsProductService {
    /**
     * @Author AutMaple
     * @Description 创建商品
     * @Date 2022/7/16 19:15
     **/
    int create(PmsProductParam productParam);

    /**
     * @Author AutMaple
     * @Description 根据商品编号获取更新信息
     * @Date 2022/7/16 19:25
     **/
    PmsProductResult getUpdateInfo(Long id);

    /**
     * @Author AutMaple
     * @Description 更新商品
     * @Date 2022/7/16 19:27
     **/
    int update(Long id, PmsProductParam productParam);

    /**
     * @Author AutMaple
     * @Description 分页查询商品信息
     * @Date 2022/7/16 19:27
     **/
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * @param ids          产品 ID
     * @param verifyStatus 审核状态
     * @param detail       审核详情
     * @Author AutMaple
     * @Description 批量修改审核状态
     * @Date 2022/7/16 19:29
     **/
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * @Author AutMaple
     * @Description 批量修改商品上架信息
     * @Date 2022/7/16 19:30
     **/
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * @Author AutMaple
     * @Description 批量修改商品推荐状态
     * @Date 2022/7/16 19:32
     **/
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * @Author AutMaple
     * @Description 批量修改新品状态
     * @Date 2022/7/16 19:32
     **/
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * @Author AutMaple
     * @Description 批量删除商品
     * @Date 2022/7/16 19:33
     **/
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * @Author AutMaple
     * @Description 根据商品名称或者货号模糊查询
     * @Date 2022/7/16 19:34
     **/
    List<PmsProduct> list(String keyword);
}
