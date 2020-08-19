package com.forteach.education.paper.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.paper.repository")
public class PaperMysqlConfig {


}
