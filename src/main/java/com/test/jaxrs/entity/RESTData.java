package com.test.jaxrs.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class RESTData {
	
	@XmlElement(name="item")
	@XmlElementWrapper(name="items")
	private List<RESTItem> items;

	@Override
	public String toString() {
		return items.toString();
	}
	
	public List<RESTItem> getItems() {
		return items;
	}
	public void setItems(List<RESTItem> items) {
		this.items = items;
	}
}
