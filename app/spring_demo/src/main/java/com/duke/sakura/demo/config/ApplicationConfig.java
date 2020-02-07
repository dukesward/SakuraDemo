package com.duke.sakura.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@ComponentScan
@Configuration
public class ApplicationConfig {
	
	@Autowired
	private Environment env;
	
	@Value("${spring.datasource.sakura.username}")
	private String username;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.sakura")
	public DataSource getDataSource() {
		System.out.println(this.username);
		return DataSourceBuilder.create().build();
	}
}
