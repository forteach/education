package com.forteach.education.authority.dbconfing;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.authority.repository")
public class AuthMysqlConfig {

	
  
}
