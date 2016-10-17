package com.oriaxx77.oaths.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oriaxx77.oaths.domain.enity.Person;

//TODO: remove this. We do not need this class
@RestController
@RequestMapping( value="/person" )
public class PersonResource 
{
 
  
  
  @RequestMapping( method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
  public Iterable<Person> get( @RequestParam( value="filter", required=false ) String filter ) 
  {
	
    List<Person> userList = Arrays.asList( new Person[]{ new Person("Michael","Jackson", "michael.jackson@mj.com" ), 
                         				                 new Person( "Gary", "Gigax", "madonna@m.com" ) } );
    
    return userList.stream().filter( person -> person.getEmail().toLowerCase().contains(  Optional.of( filter ).orElse( "" ) ))
    					     .collect( Collectors.toList() );
  }
    
  
  
  
}