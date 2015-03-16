package com.test.batch;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.apache.http.HttpStatus;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.initialize.ExpectationInitializer;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;

public class MockServerInit implements ExpectationInitializer {

	@Override
	public void initializeExpectations(MockServerClient mockServerClient) {
		// create data
		mockServerClient.when(
			request()
				.withMethod("GET")
				.withPath("/data"),
			Times.unlimited()
		).respond(
			response()
				.withStatusCode(HttpStatus.SC_OK)
				.withHeaders(
					new Header("Content-Type", "application/xml")
				)
				.withBody("<result><status>true</status><data><items><item><key>value</key></item><item><key>value2</key></item></items></data></result>")
		);
		
	}

}
