package com.forteach.education.authority.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.forteach.education.common.keyword.Dic.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2019/2/20 21:59
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.salt}")
    private String salt;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成一个token
     *
     * @param userId
     * @return
     */
    @Override
    public String createToken(String userId, String userType) {
        return JWT.create()
                .withAudience(userId, userType)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_TIME * 1000))
                .sign(Algorithm.HMAC256(salt.concat(userId)));
    }

    /**
     * 校验token
     *
     * @param userId
     * @return
     */
    @Override
    public JWTVerifier verifier(String userId) {
        return JWT.require(Algorithm.HMAC256(salt.concat(userId))).build();
    }

    /**
     * 获取token携带的用户信息
     *
     * @param request
     * @return
     */
    @Override
    public String getUserId(HttpServletRequest request) {
        return getValue(request.getHeader("token"), 0);
    }

    @Override
    public String getStudentId(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (TOKEN_STUDENT.equals(getValue(token, 1))) {
            return hashOperations.get(getKey(getValue(token, 0)), "studentId");
        }
        return null;
    }

    @Override
    public String getTeacherId(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (TOKEN_TEACHER.equals(getValue(token, 1))) {
            return getValue(token, 0);
        }
        return null;
    }

    @Override
    public String getClassId(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (TOKEN_STUDENT.equals(getValue(token, 1))) {
            return hashOperations.get(getKey(getValue(token, 0)), "classId");
        }
        return null;
    }

    @Override
    public void saveRedis(String token, SysUsers users) {
        String key = this.getKey(users.getId());
        Map<String, String> map = new HashMap<>(4);
        map.put("token", token);
        map.put("userId", users.getId());
        map.put("userName", users.getUserName());
        map.put("role", users.getRole());
        stringRedisTemplate.opsForHash().putAll(key, map);
        //设置有效期7天
        stringRedisTemplate.expire(key, TOKEN_VALIDITY_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void removeToken(String userId) {
        stringRedisTemplate.delete(this.getKey(userId));
    }

    private String getValue(String token, int index) {
        return JWT.decode(token).getAudience().get(index);
    }

    private String getKey(String userId) {
        return USER_TOKEN_PREFIX.concat(userId);
    }

    @Override
    public String getOpenId(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JWT.decode(token).getAudience().get(0);
    }

    @Override
    public String getSessionKey(String openId) {
        return hashOperations.get(USER_PREFIX.concat(openId), "sessionKey");
    }
}