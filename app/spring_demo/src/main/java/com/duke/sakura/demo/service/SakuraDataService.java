package com.duke.sakura.demo.service;

import java.util.HashMap;
import java.util.Map;

import com.duke.sakura.demo.kingdom.model.SakuraEntityBasic;

public interface SakuraDataService {
	
	static Map<String, SakuraEntityBasic> serviceEntityCache = new HashMap<>();

	public String getDataSourceId();
	
	public static SakuraEntityBasic getServiceEntityCache(String serviceEntityId) {
		return serviceEntityCache.get(serviceEntityId);
	};
	
	public static void putServiceEntityCache(String serviceEntityId, SakuraEntityBasic entity) {
		serviceEntityCache.put(serviceEntityId, entity);
	}
}
