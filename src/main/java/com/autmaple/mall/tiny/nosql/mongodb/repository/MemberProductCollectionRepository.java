package com.autmaple.mall.tiny.nosql.mongodb.repository;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberProductCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName MemberProductCollectionRepository
 * @Description 会员商品收藏管理 Repository
 * @Author AutMaple
 * @Date 2022/7/23 15:35
 * @Version 1.0
 **/
public interface MemberProductCollectionRepository extends MongoRepository<MemberProductCollection, String> {
    /**
     * @Author AutMaple
     * @Description 根据会员 ID 和产品 ID 查找记录
     * @Date 2022/7/23 15:37
     **/
    MemberProductCollection findByMemberIdAndProductId(Long memberId, Long productId);

    /**
     * @Author AutMaple
     * @Description 根据会员 ID 和商品 ID 删除指定的记录
     * @Date 2022/7/23 15:39
     **/
    int deleteByMemberIdAndProductId(Long memberId, Long productId);
    
    /**
     * @Author AutMaple
     * @Description 分页查询会员的收藏记录
     * @Date 2022/7/23 15:40
     **/
    Page<MemberProductCollection> findByMemberId(Long MemberId, Pageable pageable);

    /**
     * @Author AutMaple
     * @Description 删除会员所有的收藏记录
     * @Date 2022/7/23 15:42
     **/
    void deleteAllByMemberId(Long memberId);
}
