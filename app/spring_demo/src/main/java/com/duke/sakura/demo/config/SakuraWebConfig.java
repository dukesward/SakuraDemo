package com.duke.sakura.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SakuraWebConfig {
	
	@Bean
	public CorsFilter configCorsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		config.addExposedHeader("Accept");
		config.addExposedHeader("Content-Type");
		config.addExposedHeader("If-None-Match");
		config.addExposedHeader("Cache-Control");
		config.addExposedHeader("Origin");
		config.addExposedHeader("Authorization");
		
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);
		 
		return new CorsFilter(configSource);
	}
}
