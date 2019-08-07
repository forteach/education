package com.forteach.education.wechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.wechat.repository")
public class WeChatMysqlConfig {

}
