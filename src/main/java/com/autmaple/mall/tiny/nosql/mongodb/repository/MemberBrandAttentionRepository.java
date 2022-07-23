package com.autmaple.mall.tiny.nosql.mongodb.repository;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberBrandAttention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName MemberBrandAttentionRepository
 * @Description 会员关注 Repository
 * @Author AutMaple
 * @Date 2022/7/23 11:50
 * @Version 1.0
 **/
public interface MemberBrandAttentionRepository extends MongoRepository<MemberBrandAttention, String> {
    /**
     * @Author AutMaple
     * @Description 根据会员 ID 和品牌 ID 查找记录
     * @Date 2022/7/23 11:53
     **/
    MemberBrandAttention findByMemberIdAndBrandId(Long memberId, Long brandId);

    /**
     * @Author AutMaple
     * @Description 根据会员 ID 和品牌 ID 删除记录
     * @Date 2022/7/23 11:53
     **/
    int deleteByMemberIdAndBrandId(Long memberId, Long brandId);

    /**
     * @Author AutMaple
     * @Description 根据会员 ID 分页查找记录
     * @Date 2022/7/23 11:54
     **/
    Page<MemberBrandAttention> findByMemberId(Long memberId, Pageable pageable);

    /**
     * @Author AutMaple
     * @Description 根据会员 ID 删除记录
     * @Date 2022/7/23 11:55
     **/
    void deleteAllByMemberId(Long memberId);
}
