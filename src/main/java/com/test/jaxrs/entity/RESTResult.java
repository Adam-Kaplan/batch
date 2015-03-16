package com.test.jaxrs.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class RESTResult {
	private boolean status;
	private RESTData data;
	
	@Override
	public String toString() {
		return "{\"status\":" + status + ", \"data\":" + data + "}";
	}
	
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
