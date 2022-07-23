package com.autmaple.mall.tiny.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.autmaple.mall.tiny.dao.PortalProductDao;
import com.autmaple.mall.tiny.dto.CartProduct;
import com.autmaple.mall.tiny.dto.CartPromotionItem;
import com.autmaple.mall.tiny.mbg.mapper.OmsCartItemMapper;
import com.autmaple.mall.tiny.mbg.model.OmsCartItem;
import com.autmaple.mall.tiny.mbg.model.OmsCartItemExample;
import com.autmaple.mall.tiny.mbg.model.UmsMember;
import com.autmaple.mall.tiny.service.OmsCartItemService;
import com.autmaple.mall.tiny.service.OmsPromotionService;
import com.autmaple.mall.tiny.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OmsCatItemServiceImpl
 * @Description
 * @Author AutMaple
 * @Date 2022/7/23 16:24
 * @Version 1.0
 **/
@Service
public class OmsCatItemServiceImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper cartItemMapper;

    @Autowired
    private PortalProductDao productDao;

    @Autowired
    private OmsPromotionService promotionService;

    @Autowired
    private UmsMemberService memberService;

    @Override
    public int add(OmsCartItem cartItem) {
        int count;
        UmsMember member = memberService.getCurrentMember();
        cartItem.setMemberId(member.getId());
        cartItem.setMemberNickname(member.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if(existCartItem == null){
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        }else{
            cartItem.setCreateDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }
        return count;
    }

    /**
     * @Author AutMaple
     * @Description 根据会员 id，商品id和规格获取购物车中的商品
     * @Date 2022/7/23 18:49
     **/
    private OmsCartItem getCartItem(OmsCartItem cartItem){
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.or().andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId())
                .andDeleteStatusEqualTo(0);
        if(cartItem.getProductSkuId()!= null)
            criteria.andProductSkuIdEqualTo(cartItem.getProductSkuId());
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        return CollUtil.isNotEmpty(cartItemList) ? cartItemList.get(0) : null;
    }

    @Override
    public List<OmsCartItem> list(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.or().andDeleteStatusEqualTo(0).andMemberIdEqualTo(memberId);
        return cartItemMapper.selectByExample(example);
    }

    @Override
    public List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds) {
        List<OmsCartItem> cartItemList = list(memberId);
        if(CollUtil.isNotEmpty(cartIds)){
            cartItemList = cartItemList.stream().filter(item -> cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        if(CollUtil.isNotEmpty(cartItemList))
            cartPromotionItemList = promotionService.calcCartPromotion(cartItemList);
        return cartPromotionItemList;
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        OmsCartItemExample example = new OmsCartItemExample();
        example.or().andDeleteStatusEqualTo(0).andIdEqualTo(id).andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.or().andIdIn(ids).andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public CartProduct getCartProduct(Long productId) {
        return productDao.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateByPrimaryKeySelective(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.or().andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }
}
