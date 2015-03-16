package com.test.jaxrs.entity;

public class RESTItem {
	private String key;

	@Override
	public String toString() {
		return "{\"key\":\"" + key + "\"}";
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
