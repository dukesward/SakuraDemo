package com.duke.sakura.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ApplicationPortal {

	public static void main(String[] args) {
		// spring application bootstrap
		SpringApplication.run(ApplicationPortal.class, args);
	}

}
