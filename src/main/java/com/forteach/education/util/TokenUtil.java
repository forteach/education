package com.forteach.education.util;

import com.forteach.education.service.ObjectRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description: 检查传入的token
 * @version: V1.0
 * @author: liu zhenming
 * @Email: 1119264845@qq.com
 * @Date: 2018-07-05 11:43
 */
@Slf4j
@Configuration
public class TokenUtil {

    @Resource
    private ObjectRedisService objectRedisService;

    /**
     * 检查传入的Token值Radio中是否存入，如果不为空是正确
     *
     * @param token token
     * @return return
     */
    public boolean checkToken(String token) {
        //从缓存中获取Token信息
        Object object = objectRedisService.get(token);
        //判断获取值是否为空 为空返回false
        return !Objects.equals(null, object);
    }

    /**
     * 根据Token获取Cached的中存放的对象
     *
     * @param token key
     * @return return
     */
    public Object getTokenObject(String token) {
        return objectRedisService.get(token);
    }

    /**
     * 设置Token对象放入Cached中
     *
     * @param token  key
     * @param object value
     */
    public void setTokenObject(String token, Object object) {
        objectRedisService.set(token, object);
    }

    /**
     * 根据传入值删除指定对象
     *
     * @param key key
     */
    public void delObjectBykey(String key) {
        objectRedisService.remove(key);
    }

}
