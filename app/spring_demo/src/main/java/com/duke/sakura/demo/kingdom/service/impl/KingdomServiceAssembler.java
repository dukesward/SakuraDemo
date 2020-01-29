package com.duke.sakura.demo.kingdom.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.bean.SakuraDataSourceEvent;
import com.duke.sakura.demo.exception.SakuraConcurrencyException;
import com.duke.sakura.demo.exception.SakuraDataSourceException;
import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.annotation.SakuraEventSource;
import com.duke.sakura.demo.kingdom.data.DataSourceProvider;
import com.duke.sakura.demo.kingdom.data.DataSourceSchema;
import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;
import com.duke.sakura.demo.service.SakuraDataService;

@Service
@Scope("singleton")
public class KingdomServiceAssembler {
	
	@Autowired
	private DataSourceManager dataSourceManager;
	private ThreadPoolExecutor executor;
	
	public SakuraDataService dataSource(String dataSourceId) {
		return DataSourceProvider.findDataService(dataSourceId);
	}
	
	public void bootstrap(String serviceId) {
		if(this.executor != null) {
			throw new SakuraConcurrencyException("Thread pool bootstrap already started");
		}
		this.executor = new ThreadPoolExecutor(2, 16, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
		this.dataSourceManager.registerDataSources();
		DataSourceSchema schemas = this.dataSourceManager.collectManagedData("event");
		this.buildEvents(schemas);
		List<DataSourceTask> tasks = schemas.getExtractedDataSources();
		for(int i=0; i<tasks.size(); i++) {
			DataSourceTask task = tasks.get(i);
			System.out.println("data source task running for: " + task);
			this.executor.execute(task);
		}
		this.executor.shutdown();
	}
	
	private void buildEvents(DataSourceSchema schemas) {
		List<SakuraDataSourceEvent> events = new ArrayList<>();
		List<SakuraDataService> dataSources = DataSourceProvider.getDataServices();
		for(int i=0; i<dataSources.size(); i++) {
			SakuraDataService service = dataSources.get(i);
			Class<?> c = service.getClass();
			for(Method method : c.getDeclaredMethods()) {
				if(method.isAnnotationPresent(SakuraEventSource.class)) {
					String key = method.getAnnotation(SakuraEventSource.class).key();
					
				}
			}
		}
	}
}
