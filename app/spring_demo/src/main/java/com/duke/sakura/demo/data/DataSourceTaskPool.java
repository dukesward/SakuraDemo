package com.duke.sakura.demo.data;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;

public class DataSourceTaskPool {
	
	private String serviceId;
	private DataSourceManager dataSourceManager;
	private ThreadPoolExecutor taskPool;
	private List<DataSourceTask> tasks;
	
	public String getServiceId() {
		return this.serviceId;
	}
	
	public DataSourceTaskPool(String serviceId, DataSourceManager dataSourceManager) {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4);
		this.taskPool = new ThreadPoolExecutor(4, 16, 30L, TimeUnit.SECONDS, queue);
		this.serviceId = serviceId;
		this.dataSourceManager = dataSourceManager;
	}
	
	public void addTask(DataSourceTaskConfig config) {
		DataSourceTask task = this.dataSourceManager.getDataSourceExecutor(config.getDataSourceId());
		if(task != null) {
			this.tasks.add(task);
		}
	}
	
	public boolean hasPendingTask() {
		return this.tasks.size() > 0;
	}
	
	public void execute() {
		DataSourceTask task = this.tasks.get(0);
		this.taskPool.submit(task);
		tasks.remove(0);
	}
}
