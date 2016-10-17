package com.oriaxx77.oaths.rest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.oriaxx77.oaths.OathsApplication;


@RunWith( SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes={OathsApplication.class} )
@WebAppConfiguration
public class UserResourceTest 
{
  
    @Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup()
	{
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test 
    public void testGetUsers_ShouldReturnOK() throws Exception
	{
	   mvc.perform( get("/user") )
	                   .andExpect( status().isOk() );
	                   
	   
   }
  
}