package com.duke.sakura.demo.kingdom.service;

import com.duke.sakura.demo.kingdom.model.LocationEntity;
import com.duke.sakura.demo.service.SakuraDataService;

public interface LocationService extends SakuraDataService {
	
	public LocationEntity findLocationById(String locationId);
}
