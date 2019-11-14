package com.forteach.education.filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.forteach.education.common.keyword.Dic.USER_TOKEN_PREFIX;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:27
 */
@Slf4j
@NoArgsConstructor
public class SysUserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有 passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
        if (userLoginToken.required()) {
            String token = httpServletRequest.getHeader("token");
            // 执行认证
            MyAssert.isTrue(StrUtil.isBlank(token), DefineCode.ERR0004, "token is null");
            // 获取 token 中的 openId
            String openId = null;
            try {
                openId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                if (log.isErrorEnabled()) {
                    log.error("token 校验非法 401");
                }
                MyAssert.fail(DefineCode.ERR0004, j, "401");
            }
            if (!stringRedisTemplate.hasKey(USER_TOKEN_PREFIX.concat(openId))) {
                if (log.isErrorEnabled()) {
                    log.error("token 已经过期，请重新登录");
                }
                MyAssert.fail(DefineCode.ERR0004, new TokenExpiredException("token 已经过期，请重新登录"), "token 已经过期，请重新登录");
            }
            // 验证 token
            try {
                tokenService.verifier(openId).verify(token);
            } catch (JWTVerificationException e) {
                if (log.isErrorEnabled()) {
                    log.error("token 非法无效 401");
                }
                MyAssert.fail(DefineCode.ERR0004, e, "非法 token");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
