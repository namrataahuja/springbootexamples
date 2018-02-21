package com.example.hello.world.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Value("${message}")
	String msg;
	
	@RequestMapping("/hello")
	public String display() {
		return "HELLO-WORLD - "+msg;
	}

}
