package com.autmaple.mall.tiny.dto;

import com.autmaple.mall.tiny.mbg.model.CmsSubject;
import com.autmaple.mall.tiny.mbg.model.PmsBrand;
import com.autmaple.mall.tiny.mbg.model.PmsProduct;
import com.autmaple.mall.tiny.mbg.model.SmsHomeAdvertise;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName HomeContentResult
 * @Description 首页内容返回信息封装
 * @Author AutMaple
 * @Date 2022/7/23 10:00
 * @Version 1.0
 **/
public class HomeContentResult {
    @Schema(description="轮播广告")
    private List<SmsHomeAdvertise> advertiseList;

    @Schema(description="推荐品牌")
    private List<PmsBrand> brandList;

    @Schema(description="当前秒杀场次")
    private HomeFlashPromotion homeFlashPromotion;

    @Schema(description="新品推荐")
    private List<PmsProduct> newProductList;

    @Schema(description="人气推荐")
    private List<PmsProduct> hotProductList;

    @Schema(description="推荐主题")
    private List<CmsSubject> subjectLis;

    public List<SmsHomeAdvertise> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(List<SmsHomeAdvertise> advertiseList) {
        this.advertiseList = advertiseList;
    }

    public List<PmsBrand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<PmsBrand> brandList) {
        this.brandList = brandList;
    }

    public HomeFlashPromotion getHomeFlashPromotion() {
        return homeFlashPromotion;
    }

    public void setHomeFlashPromotion(HomeFlashPromotion homeFlashPromotion) {
        this.homeFlashPromotion = homeFlashPromotion;
    }

    public List<PmsProduct> getNewProductList() {
        return newProductList;
    }

    public void setNewProductList(List<PmsProduct> newProductList) {
        this.newProductList = newProductList;
    }

    public List<PmsProduct> getHotProductList() {
        return hotProductList;
    }

    public void setHotProductList(List<PmsProduct> hotProductList) {
        this.hotProductList = hotProductList;
    }

    public List<CmsSubject> getSubjectLis() {
        return subjectLis;
    }

    public void setSubjectLis(List<CmsSubject> subjectLis) {
        this.subjectLis = subjectLis;
    }
}
