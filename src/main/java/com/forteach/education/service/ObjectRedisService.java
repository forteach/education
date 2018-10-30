package com.forteach.education.service;

/**
 * @Description:
 * @version: V1.0
 * @author: liu zhenming
 * @Email: 1119264845@qq.com
 * @Date: 2018-07-27 11:35
 */
public interface ObjectRedisService {

    void set(String key, Object obj);

    void remove(String key);

    Object get(String key);
}
