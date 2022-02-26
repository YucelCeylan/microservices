package com.springboot.microservice.currencyservice;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.microservice.proxy.CurrencyServiceProxy;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

public class ConsumerTestCurrencyExchange {

	@Test
	public void contextLoads() {
	}

	@Rule
	public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("forexService", "localhost", 8003, this);
	

	@Autowired
	CurrencyServiceProxy providerService;

	@Pact(provider="forexService", consumer = "forexServiceClient")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=UTF-8");

		return builder
				.given("Test 1 For Currency Exchange")
					.uponReceiving("Pact JVM example Pact interaction")
					.path("/api/currency-exchange/from/USD/to/EUR")
					.method("GET")
					.headers(headers)
				.willRespondWith()
					.status(200)
					.headers(headers)
					.body(new PactDslJsonBody().stringType("conversionMultiple", "1.2"))
				.toPact();
	}

	@Test
	@PactVerification("forexService")
	public void runTest() throws IOException, JSONException {
		System.setProperty("pact.rootDir", "../pacts");

		String url = String.format("http://localhost:%d/api/currency-exchange/from/USD/to/EUR", mockProvider.getConfig().getPort());
		System.out.println("using url: " + url);
		HttpResponse response = Request.Get(url).execute().returnResponse();
		
		
		String json = EntityUtils.toString(response.getEntity());
		System.out.println("json=" + json);
		JSONObject jsonObject = new JSONObject(json);
		String conversionMultiple = jsonObject.get("conversionMultiple").toString();

		assertEquals(conversionMultiple, "1.2");
	}
}
