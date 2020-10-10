package com.springboot.microservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.dto.CurrencyConversionDTO;
import com.springboot.microservice.proxy.CurrencyServiceProxy;

@RestController
public class CurrencyController {
	@Autowired
	private CurrencyServiceProxy proxy;

	@GetMapping("/api/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionDTO convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		CurrencyConversionDTO dto = proxy.retrieveExchangeValue(from, to);
		
//		ResponseEntity<CurrencyConversionDTO> response = new RestTemplate().getForEntity(
//				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionDTO.class,
//				uriVariables);
		
//		CurrencyConversionDTO dto = response.getBody();
		return new CurrencyConversionDTO(dto.getId(), from, to, dto.getConversionMultiple(), quantity,
				quantity.multiply(dto.getConversionMultiple()), dto.getPort());
	}
}
