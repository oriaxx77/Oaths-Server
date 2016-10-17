package com.oriaxx77.oaths.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oriaxx77.oaths.domain.enity.Oath;
import com.oriaxx77.oaths.domain.enity.User;
import com.oriaxx77.oaths.domain.repository.OathRepository;
import com.oriaxx77.oaths.domain.repository.UserRepository;

@RestController
@RequestMapping("/oaths")
public class OathResource 
{

	private OathRepository oathRepository;
	private UserRepository userRepository;

	@Autowired
	public OathResource(OathRepository oathRepository, UserRepository userRepository) 
	{
		this.oathRepository = oathRepository;
		this.userRepository = userRepository;
	}

	@RequestMapping( method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Oath> getOaths() 
	{
		return oathRepository.findAll();
	}

	// TODO Integrate spring security
	@RequestMapping(value = "/mine", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Iterable<Oath> getMyOaths( /* @RequestHeader authToken: String */ ) 
	{
		User user = userRepository.findAll().iterator().next(); // TODO Spring// Security
		return oathRepository.findByOathTaker(user);
	}

	// TODO Integrate spring security
	@RequestMapping(value = "/allbutmine", method = { RequestMethod.GET }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public Iterable<Oath> getAllOathsButMine( /*
												 * @RequestHeader authToken:
												 * String
												 */ ) 
	{
		User user = userRepository.findAll().iterator().next(); // TODO Spring// Security
		return oathRepository.findByOathTakerNot(user);
	}

}