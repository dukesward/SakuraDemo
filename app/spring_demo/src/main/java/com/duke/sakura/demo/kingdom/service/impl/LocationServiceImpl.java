package com.duke.sakura.demo.kingdom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.kingdom.model.LocationEntity;
import com.duke.sakura.demo.kingdom.repository.LocationEntityRepository;
import com.duke.sakura.demo.kingdom.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationEntityRepository locationEntityRepository;

	@Override
	public String getDataSourceId() {
		return "kingdom.location";
	}

	@Override
	public LocationEntity findLocationById(String locationId) {
		return this.locationEntityRepository.findByUuid(locationId);
	}
	
}
