package com.currencyexchange.microservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchange.microservice.bean.CurrencyExchange;
import com.currencyexchange.microservice.repo.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchange.class);

	@Autowired
	private Environment environment; // THis will give the current port number

	@Autowired
	private CurrencyExchangeRepo cExRepo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	/currency-exchange/from/USD/to/INR
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		logger.info("retrieveExchangeValues called with {} to {} ", from, to);
		/*
		 * This micrometer give the id to a every request
		 * [currency-exchange,190b5e327c86d5b66929cd3cae5ede83,f1a299e63d1fbfab] [0;39m
		 * [35m31532[0;39m [2m---[0;39m [2m[nio-8000-exec-1][0; \39m
		 * [36mc.c.microservice.bean.CurrencyExchange [0;39m [2m:[0;39m
		 * retrieveExchangeValues called with USD to INR
		 */

		String port = environment.getProperty("local.server.port");
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = cExRepo.findByFromAndTo(from, to);
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data for " + from + "to" + to);
		}
		currencyExchange.setEnvironment(port);
		return currencyExchange;

	}
}
