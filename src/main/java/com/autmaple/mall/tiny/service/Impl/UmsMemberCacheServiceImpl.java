package com.autmaple.mall.tiny.service.Impl;

import com.autmaple.mall.tiny.annotation.CacheException;
import com.autmaple.mall.tiny.mbg.mapper.UmsMemberMapper;
import com.autmaple.mall.tiny.mbg.model.UmsMember;
import com.autmaple.mall.tiny.service.RedisService;
import com.autmaple.mall.tiny.service.UmsMemberCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName UmsMemberCacheServiceImpl
 * @Description 用户信息缓存 Service 的实现类
 * @Author AutMaple
 * @Date 2022/7/23 12:09
 * @Version 1.0
 **/
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UmsMemberMapper memberMapper;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;

    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;


    @Override
    public void delMember(Long memberId) {
        UmsMember member = memberMapper.selectByPrimaryKey(memberId);
        if(member != null){
            String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public UmsMember getMember(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + username;
        return (UmsMember) redisService.get(key);
    }


    @Override
    public void setMember(UmsMember member) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
        redisService.set(key, member);
    }

    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @CacheException
    @Override
    public String getAutCOde(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String)redisService.get(key);
    }
}
