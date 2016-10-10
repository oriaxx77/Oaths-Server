package com.oriaxx77.oaths.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.oriaxx77.oaths.domain.enity.Person
import org.springframework.web.bind.annotation.RequestMethod
import play.api.libs.json._
import collection.JavaConversions._
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import com.oriaxx77.oaths.domain.repository.OathRepository
import play.api.libs.json.Writes
import play.api.libs.json._
import com.oriaxx77.oaths.domain.enity.Oath
import com.oriaxx77.oaths.domain.enity.User
import Json.toJson
import scala.collection.JavaConverters._
import com.oriaxx77.oaths.rest.conversion.Jsonize
import org.springframework.web.bind.annotation.RequestHeader
import com.oriaxx77.oaths.domain.repository.UserRepository

@RestController
@RequestMapping(Array("/oaths"))
class OathResource @Autowired() (oathRepository: OathRepository,
    userRepository: UserRepository) {


  @RequestMapping(method = Array(RequestMethod.GET))
  def getOaths(): String = {
    import Jsonize._
    return Json.obj( "oaths" -> oathRepository.findAll().asScala).toString()
  }
  
  // TODO Integrate spring security
  @RequestMapping( value=Array("/mine"), method=Array(RequestMethod.GET) )
  def getMyOaths( /*@RequestHeader authToken: String*/ ): String = {
    import Jsonize._
    val user = userRepository.findAll().headOption.get // TODO Spring Security
    return Json.obj( "oaths" -> oathRepository.findByOathTaker( user ).asScala).toString() 
  }
  
  // TODO Integrate spring security
  @RequestMapping( value=Array("/allbutmine" ), method=Array(RequestMethod.GET))
  def getAllOathsButMine( /*@RequestHeader authToken: String*/ ): String = {
    import Jsonize._
    val user = userRepository.findAll().headOption.get // TODO Spring Security
    return Json.obj( "oaths" -> oathRepository.findByOathTakerNot( user ).asScala).toString()
  }

}