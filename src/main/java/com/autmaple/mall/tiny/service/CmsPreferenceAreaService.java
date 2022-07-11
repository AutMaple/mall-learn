package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.mbg.model.CmsPrefrenceArea;

import java.util.List;

/**
 * @ClassName CmsPreferenceAreaService
 * @Description 商品优选管理 Service
 * @Author AutMaple
 * @Date 2022/7/11 21:05
 * @Version 1.0
 **/
public interface CmsPreferenceAreaService {
    /**
     * @Author AutMaple
     * @Description 获取所有优选专区
     * @Date 2022/7/11 21:07
     **/
    List<CmsPrefrenceArea> listAll();
}
