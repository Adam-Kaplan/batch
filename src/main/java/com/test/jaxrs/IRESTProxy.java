package com.test.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.test.jaxrs.entity.RESTResult;

@Path("/")
public interface IRESTProxy {
	
	@GET
	public RESTResult callWS();
}
