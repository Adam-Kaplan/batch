package com.test.jaxrs.entity;


public class RESTResult {
	private boolean status;
	private RESTData data;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public RESTData getData() {
		return data;
	}
	public void setData(RESTData data) {
		this.data = data;
	}
}
