package com.duke.sakura.demo.kingdom.data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.duke.sakura.demo.bean.SakuraDataSourceSchema;
import com.duke.sakura.demo.exception.SakuraDataSourceException;
import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;
import com.duke.sakura.demo.service.SakuraDataService;

public class DataSourceSchema extends SakuraDataSourceSchema {

	private String id;
	
	private List<String> dataSources;
	
	private List<String> dependencies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getDataSources() {
		return dataSources;
	}

	public void setDataSources(List<String> dataSources) {
		this.dataSources = dataSources;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
	
	public List<DataSourceTask> getExtractedDataSources() {
		List<DataSourceTask> dataSources = new ArrayList<>();
		for(int i=0; i<this.dataSources.size(); i++) {
			String dataSourceId = this.dataSources.get(i);
			DataSourceTask task = this.getDataSourceExecutor(dataSourceId);
			dataSources.add(task);
		}
		return dataSources;
	}
	
	public List<DataSourceTask> getExtractedDependencies() {
		List<DataSourceTask> dataSources = new ArrayList<>();
		for(int i=0; i<this.dependencies.size(); i++) {
			String dataSourceId = this.dependencies.get(i);
			dataSources.add(this.getDataSourceExecutor(dataSourceId));
		}
		return dataSources;
	}
	
	public DataSourceTask getDataSourceExecutor(String id) {
		System.out.println("getDataSourceExecutor: start | " + id);
		List<SakuraDataService> dataSources = DataSourceProvider.getDataServices();
		try {
			for(int i=0; i<dataSources.size(); i++) {
				SakuraDataService service = dataSources.get(i);
				Class<?> c = service.getClass();
				for(Method method : c.getDeclaredMethods()) {
					System.out.println("getDataSourceExecutor: methods | " + method);
					if(method.isAnnotationPresent(SakuraDataSource.class)) {
						String key = method.getAnnotation(SakuraDataSource.class).key();
						if(id != null && id.equals(key)) {
							DataSourceTask task = new DataSourceTask(id, method, service);
							return task;
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace(System.out);
			throw new SakuraDataSourceException(e.getMessage());
		}
		return null;
	}
}
