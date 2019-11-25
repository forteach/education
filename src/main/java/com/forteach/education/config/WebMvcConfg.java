package com.forteach.education.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.forteach.education.filter.SysUserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:10
 */
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(Charset.forName("UTF-8"));
        config.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteBigDecimalAsPlain,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

    @Bean
    SysUserLoginInterceptor sysUserLoginInterceptor() {
        return new SysUserLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 判断是否有@LoginRequired需要进行拦截
        registry.addInterceptor(sysUserLoginInterceptor())
                .excludePathPatterns("*.css", "*.js").addPathPatterns("/**")
                .excludePathPatterns("*").addPathPatterns("/error")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**", "/error/**")
                .excludePathPatterns("/actuator/**");
    }

}
