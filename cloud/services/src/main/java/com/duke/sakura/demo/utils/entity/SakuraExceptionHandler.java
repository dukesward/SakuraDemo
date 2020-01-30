package com.duke.sakura.demo.utils.entity;

public interface SakuraExceptionHandler {
	
	public void register(SakuraExceptionThrower target);

	public String matchException(int code);
	
	public void addException(int code, String message);
	
	public String success();
	
	public boolean isSuccess(int code);
}
