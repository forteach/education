package com.forteach.education.count.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 统计信息mysql配置
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.count.repository")
public class CountMysqlConfig {


}
