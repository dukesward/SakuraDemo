package com.duke.sakura.demo.kingdom.utils;

import static com.duke.sakura.demo.kingdom.ApplicationConstants.getConstant;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.duke.sakura.demo.service.impl.SakuraRestServiceInterceptor;
import com.duke.sakura.demo.service.impl.SakuraRestServiceProxy;
import com.duke.sakura.kingdom.exception.InvalidArgumentException;

@Service
public class KingdomRestServiceProvider {
	
	private static String ITEM_INVENTORY_ADD = "ITEM_SERVICE_SOURCE_INVENTORY_ADD";
	
	protected RestTemplate restTemplate;
	
	@Autowired
	private SakuraRestServiceProxy restProxy;
	
	@Autowired
	public KingdomRestServiceProvider(
			@Value("${spring.kingdom.service.endpoint}") final String kingdomBaseUri,
			@Value("${sakura.http.uri.connect.timeout}") final long connectTimeout,
			RestTemplateBuilder builder
	) {
		this.restTemplate = builder.rootUri(kingdomBaseUri).setConnectTimeout(Duration.ofMillis(connectTimeout)).build();
		this.restTemplate.setInterceptors(Collections.singletonList(new SakuraRestServiceInterceptor()));
	}
	
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}
	
	public HttpHeaders httpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return headers;
	}
	
	public KingdomRestServicePostResponse KingdomItemInventoryAdd(String userProfileId, Map<String, Integer> items) {
		JSONObject itemsJsonObject = new JSONObject();
		HttpHeaders headers = this.httpHeaders();
		headers.add("userProfileId", userProfileId);
		items.entrySet()
		.stream()
		.forEach(entry -> {
			try {
				itemsJsonObject.put(entry.getKey(), entry.getValue());
			} catch (JSONException e) {
				throw new InvalidArgumentException(getConstant(ITEM_INVENTORY_ADD), entry.getKey());
			}
		});
		HttpEntity<String> request = new HttpEntity<String>(itemsJsonObject.toString(), headers);
		return this.httpPostKingdomProxy(getConstant(ITEM_INVENTORY_ADD), request);
	}

	public KingdomRestServicePostResponse httpPostKingdomProxy(String service, HttpEntity<String> request) {
		KingdomRestServicePostResponse response = this.restProxy.httpPostProxy(
				this.restTemplate, service, request, KingdomRestServicePostResponse.class);
		return response;
	}
	
	
}
