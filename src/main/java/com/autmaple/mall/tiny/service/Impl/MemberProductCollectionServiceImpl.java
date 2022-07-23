package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.model.UmsMember;
import com.autmaple.mall.tiny.nosql.mongodb.document.MemberProductCollection;
import com.autmaple.mall.tiny.nosql.mongodb.repository.MemberProductCollectionRepository;
import com.autmaple.mall.tiny.service.MemberProductCollectionService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberProductCollectionServiceImpl
 * @Description 会员商品收藏管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/23 15:34
 * @Version 1.0
 **/
@Service
public class MemberProductCollectionServiceImpl implements MemberProductCollectionService {
    @Autowired
    private MemberProductCollectionRepository productCollectionRepository;

    @Autowired
    private UmsMemberService memberService;

    @Override
    public int add(MemberProductCollection productCollection) {
        int count = 0;
        UmsMember member = memberService.getCurrentMember();
        productCollection.setMemberId(member.getId());
        productCollection.setMemberNickName(member.getNickname());
        productCollection.setMemberIcon(member.getIcon());
        MemberProductCollection findCollection = productCollectionRepository.findByMemberIdAndProductId(productCollection.getMemberId(), productCollection.getProductId());
        if(findCollection == null){
            productCollectionRepository.save(productCollection);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long productId) {
        UmsMember member = memberService.getCurrentMember();
        return productCollectionRepository.deleteByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public Page<MemberProductCollection> list(Integer pageNum, Integer pageSize) {
        UmsMember member = memberService.getCurrentMember();
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        return productCollectionRepository.findByMemberId(member.getId(), pageable);
    }

    @Override
    public MemberProductCollection detail(Long productId) {
        UmsMember member = memberService.getCurrentMember();
        return productCollectionRepository.findByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public void clear() {
        UmsMember member = memberService.getCurrentMember();
        productCollectionRepository.deleteAllByMemberId(member.getId());
    }
}
