package com.forteach.education.images.course.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.images.course.repository")
public class CourseImgMysqlConfig {


}
