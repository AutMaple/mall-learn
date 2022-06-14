package com.autmaple.mall.tiny.service;

public interface RedisService {
    /**
     * 存储值
     */
    void set(String key, String value);

    /**
     * 根据 key 获取值
     */
    String get(String key);

    /**
     * 设置超时时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 步长
     */
    Long increment(String key, long delta);
}
