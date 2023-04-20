package com.currencyconversion.microservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currencyconversion.microservice.bean.CurrencyConversion;


//@FeignClient(name ="currency-exchange", url="localhost:8000")
@FeignClient(name ="currency-exchange") // Now other service are handle by eureka 
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
