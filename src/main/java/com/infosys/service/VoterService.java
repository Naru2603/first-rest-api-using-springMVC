package com.infosys.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.infosys.entity.Person;
import com.infosys.exception.InvalidVoterException;

@Service
public class VoterService {

	public String doVote(Person person) {
		String res = null;
		
		if(isValidVoter(person)) {
			res = person.getName()+ " is valid to do the vote..!!!";
		}
		else
			res = "Not allowed to vote..!!" ;
		
		return res;
	}

	private boolean isValidVoter(Person person) {
		boolean result = false;
		
		if(person.getAge() >= 18) {
			if(person.getCountry().equalsIgnoreCase("INDIA"))
			result = true;
			else 
				throw new InvalidVoterException("Country is not valid..!!");
		}
		else {
			throw new InvalidVoterException("Age is not valid..!!");
		}
		return result;
	}
}
