package com.duke.sakura.demo.kingdom;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {
	
	private static final Map<String, String> constants = new HashMap<>();
	
	static {
		constants.put("DATA_SOURCE_SCHEMA", "datasources.json");
		constants.put("USER_PROFILE_DEFAULT", "admin");
		constants.put("ITEM_SERVICE_SOURCE_INVENTORY_ADD", "/item/inventory/add");
	}
	
	public static String getConstant(String key) {
		return constants.get(key);
	}

}
