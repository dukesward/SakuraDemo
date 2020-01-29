package com.duke.sakura.demo.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SakuraRestServiceProxy {
	
	public <T> T httpPostProxy(RestTemplate template, String service, HttpEntity<String> request, Class<T> c) {
		T response = template.postForObject(service, request, c);
		return response;
	}
}
