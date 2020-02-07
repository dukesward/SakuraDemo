package com.duke.sakura.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationPortal {

	public static void main(String[] args) {
		// spring application bootstrap
		SpringApplication.run(ApplicationPortal.class, args);
	}

}
