package com.autmaple.mall.tiny.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     * @Author AutMaple
     * @Description 保存属性
     * @Date 2022/7/10 12:18
     **/
    void set(String key, Object value, long time);

    void set(String key, Object value);

    /**
     * @Author AutMaple
     * @Description 从缓存中获取值
     * @Date 2022/7/10 12:20
     **/
    Object get(String key);

    /**
     * @Author AutMaple
     * @Description 从缓存中删除值
     * @Date 2022/7/10 12:20
     **/
    Boolean del(String key);

    /**
     * @Author AutMaple
     * @Description 从缓存中批量删除数据
     * @Date 2022/7/10 12:21
     **/
    Long del(List<String> keys);

    /**
     * @Author AutMaple
     * @Description 设置数据的过期时间
     * @Date 2022/7/10 12:21
     **/
    Boolean expire(String key, long time);

    /**
     * @Author AutMaple
     * @Description 获取过期时间
     * @Date 2022/7/10 12:22
     **/
    Long getExpire(String key);

    /**
     * @Author AutMaple
     * @Description 判断缓存中是否有指定的 key
     * @Date 2022/7/10 12:22
     **/
    Boolean hasKey(String key);

    /**
     * @Author AutMaple
     * @Description Key 对应的值按照步长为 delta 进行增长
     * @Date 2022/7/10 12:23
     **/
    Long incr(String key, long delta);

    /**
     * @Author AutMaple
     * @Description Key 对应的值按照步长为 delta 进行减少
     * @Date 2022/7/10 12:24
     **/
    Long decr(String key, long delta);

    /**
     * @Author AutMaple
     * @Description 获取 Hash 结构中的属性
     * @Date 2022/7/10 12:25
     **/
    Object hGet(String key, String hashKey);

    /**
     * @Author AutMaple
     * @Description 设置 Hash 结构中的属性
     * @Date 2022/7/10 12:27
     **/
    Boolean hSet(String key, String hashKey, Object value, long time);

    void hSet(String key, String hashKey, Object value);

    /**
     * @Author AutMaple
     * @Description 直接获取整个 Hash 结构
     * @Date 2022/7/10 12:28
     **/
    Map<Object, Object> hGetAll(String key);

    /**
     * @Author AutMaple
     * @Description 直接设置整个 Hash 结构
     * @Date 2022/7/10 12:29
     **/
    Boolean hSetAll(String key, Map<String, ?> map, long time);

    void hSetAll(String key, Map<String, ?> map);

    /**
     * @Author AutMaple
     * @Description 删除  Hash 结构中的属性
     * @Date 2022/7/10 12:30
     **/
    void hDel(String key, Object... hashKey);

    /**
     * @Author AutMaple
     * @Description 判断 Hash 结构中是否有指定的属性
     * @Date 2022/7/10 12:31
     **/
    Boolean hHashKey(String key, String hashKey);

    /**
     * @Author AutMaple
     * @Description hash 结构中的数据递增
     * @Date 2022/7/10 12:32
     **/
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * @Author AutMaple
     * @Description Hash 结构中的数据递减
     * @Date 2022/7/10 12:32
     **/
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * @Author AutMaple
     * @Description 获取 Set 结构中的值
     * @Date 2022/7/10 12:33
     **/
    Set<Object> sMembers(String key);

    /**
     * @Author AutMaple
     * @Description 往 Set 结构中添加值
     * @Date 2022/7/10 12:34
     **/
    Long sAdd(String key, Object... values);

    Long sAdd(String key, long time, Object... values);

    /**
     * @Author AutMaple
     * @Description 判断是否是 Set 中的属性
     * @Date 2022/7/10 12:35
     **/
    Boolean sIsMember(String key, Object value);

    /**
     * @Author AutMaple
     * @Description 获取 Set 结构的长度
     * @Date 2022/7/10 12:36
     **/
    Long sSize(String key);

    /**
     * @Author AutMaple
     * @Description 异常 Set 结构中的一些属性
     * @Date 2022/7/10 12:36
     **/
    Long sRemove(String key, Object... values);

    /**
     * @Author AutMaple
     * @Description 获取 List 指定区间中的元素
     * @Date 2022/7/10 12:37
     **/
    List<Object> lRange(String key, long start, long end);

    /**
     * @Author AutMaple
     * @Description 获取 List 结构的长度
     * @Date 2022/7/10 12:38
     **/
    Long lSize(String key);

    /**
     * @Author AutMaple
     * @Description 获取 List 中指定位置的元素
     * @Date 2022/7/10 12:38
     **/
    Object lIndex(String key, long index);

    /**
     * @Author AutMaple
     * @Description 向 List 结构中添加属性
     * @Date 2022/7/10 12:39
     **/
    Long lPush(String key, Object value);

    Long lPush(String key, Object value, long time);

    /**
     * @Author AutMaple
     * @Description 一次性向 List 结构中添加多个元素
     * @Date 2022/7/10 12:40
     **/
    Long lPushAll(String key, Object... values);

    Long lPushAll(String key, long time, Object... values);

    /**
     * @Author AutMaple
     * @Description 从 List 结构中移除属性
     * @Date 2022/7/10 12:42
     **/
    Long lRemove(String key, long count, Object value);
}
