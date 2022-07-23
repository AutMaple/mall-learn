package com.autmaple.mall.tiny.nosql.mongodb.repository;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName MemberReadHistoryRepository
 * @Description 会员商品浏览历史 Repository
 * @Author AutMaple
 * @Date 2022/6/23 20:52
 * @Version 1.0
 **/
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
    /**
     * @Author AutMaple
     * @Description 根据会员 ID 分页查找浏览记录
     * @Date 2022/7/23 15:57
     **/
    Page<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId, Pageable pageable);

    /**
     * @Author AutMaple
     * @Description 清空指定用于的浏览记录
     * @Date 2022/7/23 15:57
     **/
    void deleteAllByMemberId(Long memberId);
}
