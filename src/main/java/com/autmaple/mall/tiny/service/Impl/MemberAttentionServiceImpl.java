package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.mbg.model.UmsMember;
import com.autmaple.mall.tiny.nosql.mongodb.document.MemberBrandAttention;
import com.autmaple.mall.tiny.nosql.mongodb.repository.MemberBrandAttentionRepository;
import com.autmaple.mall.tiny.service.MemberAttentionService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName MemberAttentionServiceImpl
 * @Description 会员关注管理 Service 实现类
 * @Author AutMaple
 * @Date 2022/7/23 11:48
 * @Version 1.0
 **/
@Service
public class MemberAttentionServiceImpl implements MemberAttentionService {
    @Autowired
    private MemberBrandAttentionRepository memberBrandAttentionRepository;

    @Autowired
    private UmsMemberService memberService;

    @Override
    public int add(MemberBrandAttention memberBrandAttention) {
        int count = 0;
        UmsMember member = memberService.getCurrentMember();
        memberBrandAttention.setMemberId(member.getId());
        memberBrandAttention.setMemberNickName(member.getNickname());
        memberBrandAttention.setMemberIcon(member.getIcon());
        memberBrandAttention.setCreateTime(new Date());
        MemberBrandAttention findAttention = memberBrandAttentionRepository.findByMemberIdAndBrandId(memberBrandAttention.getMemberId(), memberBrandAttention.getBrandId());
        if(findAttention == null){
            memberBrandAttentionRepository.save(memberBrandAttention);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long brandId) {
        UmsMember member = memberService.getCurrentMember();
        return memberBrandAttentionRepository.deleteByMemberIdAndBrandId(member.getId(), brandId);
    }

    @Override
    public Page<MemberBrandAttention> list(Integer pageSize, Integer pageNum) {
        UmsMember member = memberService.getCurrentMember();
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        return memberBrandAttentionRepository.findByMemberId(member.getId(),pageable);
    }

    @Override
    public MemberBrandAttention detail(Long brandId) {
        UmsMember member = memberService.getCurrentMember();
        return memberBrandAttentionRepository.findByMemberIdAndBrandId(member.getId(), brandId);
    }

    @Override
    public void clear() {
        UmsMember member = memberService.getCurrentMember();
        memberBrandAttentionRepository.deleteAllByMemberId(member.getId());
    }
}
