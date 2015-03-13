package com.test.jaxrs;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JAXRSConfiguration {
	@Bean
	public IRESTProxy restClientFactory() {
		return JAXRSClientFactory.create("http://localhost:9999", IRESTProxy.class);
	}
}
