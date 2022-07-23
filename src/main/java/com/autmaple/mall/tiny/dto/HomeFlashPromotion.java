package com.autmaple.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @ClassName HomeFlashPromotion
 * @Description 首页秒杀场次信息的封装
 * @Author AutMaple
 * @Date 2022/7/23 10:02
 * @Version 1.0
 **/
public class HomeFlashPromotion {
    @ApiModelProperty("本场秒杀的开始时间")
    private Date startTime;

    @ApiModelProperty("本场秒杀的结束时间")
    private Date endTime;

    @ApiModelProperty("下场开始的时间")
    private Date nextStartTime;

    @ApiModelProperty("下场结束时间")
    private Date nextEndTime;

    @ApiModelProperty("属于该秒杀活动的商品")
    private List<FlashPromotionProduct> productList;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(Date nextStartTime) {
        this.nextStartTime = nextStartTime;
    }

    public Date getNextEndTime() {
        return nextEndTime;
    }

    public void setNextEndTime(Date nextEndTime) {
        this.nextEndTime = nextEndTime;
    }

    public List<FlashPromotionProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<FlashPromotionProduct> productList) {
        this.productList = productList;
    }
}
