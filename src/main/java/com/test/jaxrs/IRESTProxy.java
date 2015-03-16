package com.test.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.test.jaxrs.entity.RESTResult;

public interface IRESTProxy {
	
	@GET
	@Path("/data")
	public RESTResult callWS();
}
