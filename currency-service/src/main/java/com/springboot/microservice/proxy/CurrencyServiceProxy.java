package com.springboot.microservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.microservice.dto.CurrencyConversionDTO;

@FeignClient(name="forex-service")
@RibbonClient(name="forex-service")
public interface CurrencyServiceProxy {
	@GetMapping("/api/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionDTO retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
