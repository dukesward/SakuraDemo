package com.duke.sakura.demo.kingdom.service.impl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.bean.SakuraDataSourceEvent;
import com.duke.sakura.demo.exception.ResourceUnavailableException;
import com.duke.sakura.demo.exception.SakuraDataSourceException;
import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.data.DataSourceProvider;
import com.duke.sakura.demo.kingdom.data.DataSourceSchema;
import com.duke.sakura.demo.kingdom.model.SakuraEntityBasic;
import com.duke.sakura.demo.kingdom.runnable.DataSourceTask;
import com.duke.sakura.demo.kingdom.service.CharacterService;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;
import com.duke.sakura.demo.kingdom.service.EventService;
import com.duke.sakura.demo.kingdom.service.ItemService;
import com.duke.sakura.demo.kingdom.service.UserProfileService;
import com.duke.sakura.demo.service.SakuraDataService;
import com.duke.sakura.demo.service.SakuraEventLoopManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.duke.sakura.demo.kingdom.ApplicationConstants.getConstant;

@Service
@Scope("singleton")
public class DataSourceManagerImpl implements DataSourceManager {
	
	private static final String DATA_SOURCE_SCHEMA = getConstant("DATA_SOURCE_SCHEMA");
	
	@Autowired
	private CharacterService characterService;
	@Autowired
	private EventService eventService;
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private SakuraEventLoopManager eventLoopManager;

	private List<DataSourceSchema> dataSourceSchemas;
	
	@Override
	@PostConstruct
	public void registerDataSources() {
		DataSourceProvider.registerDataService(characterService);
		DataSourceProvider.registerDataService(eventService);
		DataSourceProvider.registerDataService(userProfileService);
		DataSourceProvider.registerDataService(itemService);
	}
	
	@Override
	public void collectData(String id, List<? extends SakuraEntityBasic> dataList) {
		DataSourceProvider.storeDataEntities(id, dataList);
	}
	
	@Override
	public DataSourceSchema collectManagedData(String key) {
		if(this.dataSourceSchemas == null) {
			this.getDataSourceSchema();
		}
		for(int i=0; i<this.dataSourceSchemas.size(); i++) {
			if(key.equals(this.dataSourceSchemas.get(i).getId())) {
				return this.dataSourceSchemas.get(i);
			}
		}
		return null;
	}
	
	private void getDataSourceSchema() {
		try {
			TypeReference<ArrayList<DataSourceSchema>> typeRef = new TypeReference<ArrayList<DataSourceSchema>>() {};
			ObjectMapper objectMapper = new ObjectMapper();
			String dataSourceSchemaFileName = getClass().getClassLoader().getResource(DATA_SOURCE_SCHEMA).getFile();
			List<DataSourceSchema> dataSourceSchemas = new ArrayList<>();
			dataSourceSchemas = objectMapper.readValue(new File(dataSourceSchemaFileName), typeRef);
			this.dataSourceSchemas = dataSourceSchemas;
		}catch (Exception e) {
			throw new ResourceUnavailableException(DATA_SOURCE_SCHEMA, e.getMessage());
		}
	}
	
	@Override
	public DataSourceTask getDataSourceExecutor(String id) {
		System.out.println("getDataSourceExecutor: start | " + id);
		List<SakuraDataService> dataSources = DataSourceProvider.getDataServices();
		try {
			for(int i=0; i<dataSources.size(); i++) {
				SakuraDataService service = dataSources.get(i);
				Class<?> c = service.getClass();
				for(Method method : c.getDeclaredMethods()) {
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
