package com.forteach.education.authority.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.forteach.education.authority.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.forteach.education.common.keyword.Dic.TokenValidityTime;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2019/2/20 21:59
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service(value = "TokenService")
public class TokenImpl implements TokenService {

    @Value("${token.salt}")
    private String salt;

    /**
     * 生成一个token
     * @param userId
     * @return
     */
    @Override
    public String createToken(String userId) {
        return JWT.create()
                .withAudience(userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TokenValidityTime * 1000))
                .sign(Algorithm.HMAC256(salt.concat(userId)));
    }

    /**
     * 校验token
     * @param userId
     * @return
     */
    @Override
    public JWTVerifier verifier(String userId) {
        return JWT.require(Algorithm.HMAC256(salt.concat(userId))).build();
    }

    /**
     * 获取token携带的用户信息
     * @param request
     * @return
     */
    @Override
    public String getUserId(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JWT.decode(token).getAudience().get(0);
    }
}
