package com.forteach.education.course.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.course.repository")
public class CourseMysqlConfig {


}
