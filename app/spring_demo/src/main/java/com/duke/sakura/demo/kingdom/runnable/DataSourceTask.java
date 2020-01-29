package com.duke.sakura.demo.kingdom.runnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.duke.sakura.demo.exception.SakuraConcurrencyException;
import com.duke.sakura.demo.service.SakuraDataService;

public class DataSourceTask implements Runnable {
	
	private String dataSourceId;
	
	private Method dataSourceExecutor;
	
	private SakuraDataService dataSource;
	
	public DataSourceTask(String dataSourceId, Method dataSourceExecutor, SakuraDataService dataSource) {
		this.dataSourceId = dataSourceId;
		this.dataSourceExecutor = dataSourceExecutor;
		this.dataSource = dataSource;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public Method getDataSourceExecutor() {
		return dataSourceExecutor;
	}

	public void setDataSourceExecutor(Method dataSourceExecutor) {
		this.dataSourceExecutor = dataSourceExecutor;
	}
	
	public SakuraDataService getDataSource() {
		return dataSource;
	}

	public void setDataSource(SakuraDataService dataSource) {
		this.dataSource = dataSource;
	}
	
	public String toString() {
		return "Data source task [" + dataSourceId + "]";
	}

	@Override
	public void run() {
		try {
			System.out.println("invoke data source fetching");
			this.dataSourceExecutor.invoke(this.dataSource);
		}catch (Exception e) {
			e.printStackTrace(System.out);
			throw new SakuraConcurrencyException("Task execution failed due to -> " + e.getMessage());
		}
	}
	
	public Object invokeNow(Object... args) throws InvocationTargetException, IllegalAccessException {
		return this.dataSourceExecutor.invoke(this.dataSource, args);
	}

}
