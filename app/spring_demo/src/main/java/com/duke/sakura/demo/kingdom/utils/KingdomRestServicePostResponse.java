package com.duke.sakura.demo.kingdom.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KingdomRestServicePostResponse {
	@JsonProperty("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
