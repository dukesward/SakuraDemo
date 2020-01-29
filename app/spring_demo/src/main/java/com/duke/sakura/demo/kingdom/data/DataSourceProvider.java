package com.duke.sakura.demo.kingdom.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.duke.sakura.demo.exception.DataSourceInitException;
import com.duke.sakura.demo.kingdom.model.SakuraEntityBasic;
import com.duke.sakura.demo.service.SakuraDataService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class DataSourceProvider {
	
	private static final DataSourceProvider instance = new DataSourceProvider();
	private static Multimap<String, SakuraEntityBasic> dataEntityCache = ArrayListMultimap.create();
	private static Map<String, String> initDataStorage = new HashMap<>();

	private List<SakuraDataService> dataSources;
	
	private DataSourceProvider() {
		this.dataSources = new ArrayList<>();
	}

	public static void registerDataService(SakuraDataService dataService) {
		instance.dataSources.add(dataService);
	}
	
	public static SakuraDataService findDataService(String dataSourceId) {
		for(int i=0; i<instance.dataSources.size(); i++) {
			if(dataSourceId.equals(instance.dataSources.get(i).getDataSourceId())) {
				return instance.dataSources.get(i);
			}
		}
		return null;
	}

	public static List<SakuraDataService> getDataServices() {
		return instance.dataSources;
	}
	
	public static void storeDataEntities(String id, List<? extends SakuraEntityBasic> dataList) {
		dataList.stream().forEach(dataEntity -> dataEntityCache.put(id, dataEntity));
	}
	
	public static void setInitData(String key, String value) {
		if(initDataStorage.get(key) != null) {
			throw new DataSourceInitException(key, "Duplicate initializing");
		}
		initDataStorage.put(key, value);
	}
	
	public static String getInitData(String key) {
		if(initDataStorage.get(key) == null) {
			throw new DataSourceInitException(key, "Data not initialized");
		}
		return initDataStorage.get(key);
	}

}
