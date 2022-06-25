package com.autmaple.mall.tiny.nosql.mongodb.repository;

import com.autmaple.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

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
     * @Description 根据会员 id 按照时间倒序的顺序获取浏览记录
     * @Date 2022/6/23 20:57
     **/
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
