package com.forteach.education.service;

/**
 * @Description:
 * @version: V1.0
 * @author: liu zhenming
 * @Email: 1119264845@qq.com
 * @Date: 2018-07-27 11:35
 */
public interface ObjectRedisService {

    /**
     * 设置redis键值对
     *
     * @param key
     * @param obj
     */
    void set(String key, Object obj);

    /**
     * 删除redis键值对
     *
     * @param key
     */
    void remove(String key);

    /**
     * 获取redis键值对
     *
     * @param key
     * @return
     */
    Object get(String key);
}
