package com.oriaxx77.oaths.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.oriaxx77.oaths.domain.Person
import org.springframework.web.bind.annotation.RequestMethod
import play.api.libs.json._
import collection.JavaConversions._
import org.springframework.web.bind.annotation.RequestParam


@RestController
@RequestMapping(Array("/person"))
class PersonResource {
 
  
  implicit val personWrites = new Writes[Person] {
                def writes(p: Person) = Json.obj(
                  "firstName" -> p.firstName,
                  "surName" -> p.surName,
                  "email" -> p.email
                  )
                }
 
  
  @RequestMapping(method = Array(RequestMethod.GET))
  def get( @RequestParam( value="filter", required=false ) filter:String = "" ):String = {
    var userList = List( new Person("Michael","Jackson", "michael.jackson@mj.com" ), 
                         new Person( "Gary", "Gigax", "madonna@m.com" ) )
    if ( Option(filter).isDefined && !filter.isEmpty() ){
       userList = userList.filter { person => person.email.toLowerCase().contains( filter.toLowerCase() ) }
    }
    Json.obj( "people" -> userList ).toString()
  }
    
  
  
  
}