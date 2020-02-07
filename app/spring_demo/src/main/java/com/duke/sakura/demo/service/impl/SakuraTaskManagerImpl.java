package com.duke.sakura.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.data.DataSourceTaskConfig;
import com.duke.sakura.demo.data.DataSourceTaskPool;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;
import com.duke.sakura.demo.service.SakuraTaskManager;

@Service
public class SakuraTaskManagerImpl implements SakuraTaskManager {
	
	private static final Logger logger = LoggerFactory.getLogger(SakuraTaskManagerImpl.class);
	private static Map<String, DataSourceTaskPool> taskCollection = new HashMap<>();
	private static List<String> services;
	
	static {
		new Thread(new TaskController()).run();
	}

	private static class TaskController implements Runnable {
		@Override
		public void run() {
			while(true) {
				for(int i=0; i<services.size(); i++) {
					DataSourceTaskPool taskPool = taskCollection.get(services.get(i));
					if(taskPool != null && taskPool.hasPendingTask()) {
						logger.debug("start running task for service " + services.get(i));
						taskPool.execute();
					}
				}
			}
		}
	}

	@Autowired
	DataSourceManager dataSourceManager;

	private void registerService(String serviceId) {
		services.add(serviceId);
	}
	
	@Override
	public void produceTask(String sessionId, String serviceId, String dataSourceId) {
		DataSourceTaskConfig task = new DataSourceTaskConfig(sessionId, serviceId, dataSourceId);
		if(null == taskCollection.get(serviceId)) {
			// this way we can quickly go through the list of services in task pool
			this.registerService(serviceId);
			taskCollection.put(serviceId, new DataSourceTaskPool(serviceId, this.dataSourceManager));
		}
		DataSourceTaskPool taskPool = taskCollection.get(serviceId);
		taskPool.addTask(task);
	}
	
}
