package com.oriaxx77.oaths.domain.enity;

import lombok.Getter;
import lombok.Setter;

public class Person
{
	@Getter @Setter private String firstName;
	@Getter @Setter private String surName;
	@Getter @Setter private String email;
	
	public Person(String firstName, String surName, String email) 
	{
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.email = email;
	}
	
	 
}