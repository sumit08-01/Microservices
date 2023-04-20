package com.currencyexchange.microservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchange.microservice.bean.CurrencyExchange;
import com.currencyexchange.microservice.repo.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment; // THis will give the current port number
	
	@Autowired
	private CurrencyExchangeRepo cExRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	/currency-exchange/from/USD/to/INR
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		String port = environment.getProperty("local.server.port");
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = cExRepo.findByFromAndTo(from, to);
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data for "+ from+ "to"+to);
		}
		currencyExchange.setEnvironment(port);
		return currencyExchange;
		
	}
}
