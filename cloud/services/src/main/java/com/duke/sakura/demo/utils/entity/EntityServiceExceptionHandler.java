package com.duke.sakura.demo.utils.entity;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class EntityServiceExceptionHandler implements SakuraExceptionHandler {
	
	private HashMap<Integer, String> exceptions;
	
	@Override
	public void register(SakuraExceptionThrower target) {
		target.defineExceptions(this);
	}
	
	public EntityServiceExceptionHandler() {
		this.exceptions = new HashMap<Integer, String>();
		this.exceptions.put(0, "success");
	}
	
	public String matchException(int code) {
		return this.exceptions.get(code);
	}
	
	public void addException(int code, String message) {
		this.exceptions.put(code, message);
	}
	
	public String success() {
		return this.exceptions.get(0);
	}
	
	public boolean isSuccess(int code) {
		return code == 0;
	}
}
