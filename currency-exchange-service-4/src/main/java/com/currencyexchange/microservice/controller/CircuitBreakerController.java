package com.currencyexchange.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	int count =0;
//	@Retry(name = "default") // it's send request 3 time 
//	@Retry(name = "sample-api") // it's send request 5 time added in properties file
//	@Retry(name = "sample-api", fallbackMethod = "responseBack") // IF the request is not completed the a fallback Method run
//	@CircuitBreaker(name = "sample-api", fallbackMethod = "responseBack") // it's will send the request without calling fallbackMethod
//	@RateLimiter(name = "default") //it's allow how many request to want to a particular api for a particular time periods and configure in .properties file. ex-> 10s  => 10000 calls to your api
	
	@GetMapping("/sam")
	@Bulkhead(name = "default")
	public String sampleApi() {
		logger.info("Sample Api call received", count++);
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8765/currency-exchange/from/USD/to/INR", String.class);
//		return forEntity.getBody();
		return "sample-api";
	}
	
	public String responseBack(Exception ex) {
		return "FallBackResponse";
	}
}
