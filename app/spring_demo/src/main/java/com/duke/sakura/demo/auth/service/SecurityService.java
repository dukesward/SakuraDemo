package com.duke.sakura.demo.auth.service;

public interface SecurityService {
	
	String findLoggedInUsername();
	
	void autoLogin(String username, String password);
}
