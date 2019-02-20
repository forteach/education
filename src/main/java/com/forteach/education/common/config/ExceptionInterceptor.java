package com.forteach.education.common.config;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.exception.AssertErrorException;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 14:27
 */
@Slf4j
@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public WebResult defaultErrorHandler(HttpServletRequest req, Throwable e) {
        log.error("全局异常拦截器  {}   请求地址 {}  /  异常信息  {}", e, req.getRequestURL(), e.getMessage());
        return WebResult.failException(e.getMessage());
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public WebResult sqlErrorHandler(HttpServletRequest req, SQLException e) {
        log.error("全局异常拦截器 sql语句异常  {} 请求地址 {}  /  异常信息  {}", e, req.getRequestURL(), e.getMessage());
        return WebResult.failException();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public WebResult methodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("全局异常拦截器 参数校验不正确  {} 请求地址 {}  /  异常信息  {}", e, req.getRequestURL(), e.getMessage());
        return WebResult.failResult(9000);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public WebResult httpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("全局异常拦截器 请求参数格式不正确  {} 请求地址 {}  /  异常信息  {}", e, req.getRequestURL(), e.getMessage());
        return WebResult.failResult(9100);
    }

    @ExceptionHandler(AssertErrorException.class)
    @ResponseBody
    public WebResult serverExceptionHandler(AssertErrorException ex) {
        return WebResult.failResult(ex.getErrorCode(), ex.getMessage());
    }
}
