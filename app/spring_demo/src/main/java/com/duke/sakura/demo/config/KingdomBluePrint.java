package com.duke.sakura.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KingdomBluePrint {
	
	@Value("${spring.kingdom.service.endpoint}")
	private String kingdomBaseUri;
	
	public String getKingsomBaseUri() {
		return "1111";
	}
}
