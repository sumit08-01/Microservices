package com.micro.spring.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("hello-world")
	private String helloWorld() {
		return "Hello Sumit";
	}
	
	@GetMapping("hello-world-bean")
	private HelloworldBean helloWorldBean() {
		return new HelloworldBean("Hello Sumit Returning a bean");
	}
	
	@GetMapping("hello-world-bean/path-variable/{names}")
	 /*
	 * name in uri and name is method parameters both are same if you want to change then
	 * @PathVariable(value ="names") both are same
	 */
	private HelloworldBean helloWorldBeanPathVariable(@PathVariable(value ="names") String name) {
		return new HelloworldBean(String.format("Hello %s Returning a bean with path variable ", name));
	}
}
