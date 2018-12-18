package com.forteach.education.classes.dbconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.classes.repository")
public class ClassMysqlConfig{

	
  
}
