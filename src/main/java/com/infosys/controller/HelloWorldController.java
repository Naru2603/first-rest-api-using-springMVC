package com.infosys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.entity.Person;
import com.infosys.service.VoterService;


@RestController
public class HelloWorldController {

	@Autowired	
	VoterService voterService;
	
	@GetMapping("/hello")
	public @ResponseBody String hello() {
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
		
		ResponseEntity<String> response = new ResponseEntity<String>(res, HttpStatus.OK);
		return response;
	}
}
