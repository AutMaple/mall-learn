package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberProductCollection;
import org.springframework.data.domain.Page;

/**
 * @ClassName MemberProductCollectionService
 * @Description 会员商品收藏管理 Service
 * @Author AutMaple
 * @Date 2022/7/23 15:27
 * @Version 1.0
 **/
public interface MemberProductCollectionService {
    /**
     * @Author AutMaple
     * @Description 添加收藏
     * @Date 2022/7/23 15:31
     **/
    int add(MemberProductCollection productCollection);

    /**
     * @Author AutMaple
     * @Description 删除收藏
     * @Date 2022/7/23 15:31
     **/
    int delete(Long productId);

    /**
     * @Author AutMaple
     * @Description 分页查询收藏
     * @Date 2022/7/23 15:32
     **/
    Page<MemberProductCollection> list(Integer pageNum, Integer pageSize);

    /**
     * @Author AutMaple
     * @Description 查询收藏的详细信息
     * @Date 2022/7/23 15:32
     **/
    MemberProductCollection detail(Long productId);

    /**
     * @Author AutMaple
     * @Description 清空收藏
     * @Date 2022/7/23 15:32
     **/
    void clear();
}
