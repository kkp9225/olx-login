package com.olxlogin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@GetMapping("/read-property")
	public String getProperty(){
		return dbUrl;
	}
}
