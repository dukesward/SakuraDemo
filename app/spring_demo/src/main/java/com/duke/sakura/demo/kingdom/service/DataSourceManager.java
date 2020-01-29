package com.duke.sakura.demo.kingdom.service;

import java.util.List;

import com.duke.sakura.demo.kingdom.data.DataSourceSchema;
import com.duke.sakura.demo.kingdom.model.SakuraEntityBasic;
import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;

public interface DataSourceManager {
	
	public void registerDataSources();
	
	public void collectData(String id, List<? extends SakuraEntityBasic> dataList);
	
	public DataSourceSchema collectManagedData(String key);
	
	public DataSourceTask getDataSourceExecutor(String id);
}
