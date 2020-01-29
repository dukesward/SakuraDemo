package com.duke.sakura.demo.bean;

public class SakuraDataSourceEvent extends SakuraEventAbstract {
	
	private SakuraDataSourceSchema schema;
	
	public SakuraDataSourceEvent(SakuraDataSourceSchema schema, Runnable task) {
		this.task = task;
		this.schema = schema;
	}
	
	public Runnable getTask() {
		return this.task;
	}

	@Override
	public boolean completed() {
		// TODO Auto-generated method stub
		return false;
	}
}
