package com.duke.sakura.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationPortal implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	public static void main(String[] args) {
		// spring application bootstrap
		SpringApplication.run(ApplicationPortal.class, args);
	}
	
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.setPort(8090);
		factory.setContextPath("");
	}

}
