package com.currencyexchange.microservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currencyexchange.microservice.bean.CurrencyExchange;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
