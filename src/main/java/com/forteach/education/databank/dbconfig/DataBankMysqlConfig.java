package com.forteach.education.databank.dbconfing;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.forteach.education.databank.repository")
public class DataBankMysqlConfig{

	
  
}
