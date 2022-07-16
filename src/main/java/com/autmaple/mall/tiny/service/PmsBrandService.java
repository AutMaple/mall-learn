package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.dto.PmsBrandParam;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    /**
     * @Author AutMaple
     * @Description 显示所有的品牌
     * @Date 2022/7/16 09:14
     **/
    List<PmsBrand> listAllBrand();

    /**
     * @Author AutMaple
     * @Description 创建品牌
     * @Date 2022/7/16 09:14
     **/
    int createBrand(PmsBrandParam brandParam);

    /**
     * @Author AutMaple
     * @Description 更新品牌信息
     * @Date 2022/7/16 09:15
     **/
    int updateBrand(Long id, PmsBrandParam brandParam);

    /**
     * @Author AutMaple
     * @Description 删除指定的品牌
     * @Date 2022/7/16 09:15
     **/
    int deleteBrand(Long id);

    /**
     * @Author AutMaple
     * @Description 批量删除品牌信息
     * @Date 2022/7/16 09:16
     **/
    int deleteBrand(List<Long> ids);

    /**
     * @Author AutMaple
     * @Description 分页搜索查询指定关键的品牌信息
     * @Date 2022/7/16 09:16
     **/
    List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * @Author AutMaple
     * @Description 获取指定品牌的详细信息
     * @Date 2022/7/16 09:17
     **/
    PmsBrand getBrand(Long id);

    /**
     * @Author AutMaple
     * @Description 修改显示状态
     * @Date 2022/7/16 09:17
     **/
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * @Author AutMaple
     * @Description 修改厂家制造商状态
     * @Date 2022/7/16 09:19
     **/
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
