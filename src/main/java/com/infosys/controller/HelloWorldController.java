package com.infosys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.config.FrontController;
import com.infosys.entity.Person;
import com.infosys.service.VoterService;


@RestController
public class HelloWorldController {


	public static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired	
	VoterService voterService;
	
	@GetMapping("/hello")
	public @ResponseBody String hello() {
		
		MDC.put("HelloReq", "Hello Rishi");
		logger.info("Request received for : /hello");
		return "Hello World Welcome to Spring MVC..!!";
	}
	
	@GetMapping("/vote")
	public String doVote() {
		
		
		
		Person ram = new Person(10, "Narendra", 21, "India");
		return voterService.doVote(ram);
	}
	
	@PostMapping("/vote")
	public ResponseEntity<String> doVote(@RequestBody Person person) {

		
		String res = voterService.doVote(person);
		logger.info("Request received for : /vote for person {}: ", person);
		ResponseEntity<String> response = new ResponseEntity<String>(res, HttpStatus.OK);
		return response;
		
		
	}
}
