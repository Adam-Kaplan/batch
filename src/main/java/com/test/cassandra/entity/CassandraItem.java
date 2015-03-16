package com.test.cassandra.entity;

import java.util.UUID;

public class CassandraItem {
	private UUID id;
	private String key;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("{\"id\":\"").append(id.toString())
			.append("\", \"key\":\"").append(key).append("\"}");
		
		return builder.toString();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
