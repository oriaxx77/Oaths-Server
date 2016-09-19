package com.oriaxx77.oaths.rest


import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import com.oriaxx77.oaths.OathsApplication
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.MockMvc
import org.junit.Before
import org.junit.Assert._
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MvcResult

import org.hamcrest.Matchers.nullValue;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith( classOf[SpringJUnit4ClassRunner])
@ContextConfiguration( classes=Array(classOf[OathsApplication]) )
@WebAppConfiguration
class UserResourceTest 
{
  
    @Autowired
	  var context:WebApplicationContext = null;

	  var mvc: MockMvc = null;

	  @Before
	  def setup() = {
		  mvc = MockMvcBuilders.webAppContextSetup(context).build();
	  }

	 @Test 
   def testGetUsers_ShouldReturnOK() = {
	   val result = mvc.perform( get("/user") )
	                   .andExpect( status().isOk )
	                   .andReturn();
	   
   }
  
}