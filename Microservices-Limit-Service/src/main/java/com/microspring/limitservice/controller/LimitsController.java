package com.microspring.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microspring.limitservice.bean.Limits;
import com.microspring.limitservice.configuration.ConfigurationP;

@RestController
public class LimitsController {

	@Autowired
	private ConfigurationP configurationP;
	
	@GetMapping("/limits")
	public Limits retriveLimits() {
		return new Limits(configurationP.getMinimum(), configurationP.getMaximum());
		
	}
}
