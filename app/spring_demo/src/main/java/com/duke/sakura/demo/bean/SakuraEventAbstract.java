package com.duke.sakura.demo.bean;

public abstract class SakuraEventAbstract {
	
	protected Runnable task;

	public abstract boolean completed();
}
