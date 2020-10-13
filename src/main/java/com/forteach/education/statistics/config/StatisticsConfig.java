package com.forteach.education.statistics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:29
 * @version: 1.0
 * @description：统计信息
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.statistics.repository")
public class StatisticsConfig {


}