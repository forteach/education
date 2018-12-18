package com.forteach.education.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:09
 */
@Configuration
public class RedisConfig {
    /**
     * 当使用redisTemplate 时，对value的序列化使用GenericJackson2JsonRedisSerializer()
     * 有关使用此序列化器的详细信息请访问：https://github.com/spring-projects/spring-data-redis/pull/145
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setConnectionFactory(factory);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public HashOperations<?, ?, ?> hashOperations(RedisTemplate redisTemplate) {
        return redisTemplate.opsForHash();
    }

}
