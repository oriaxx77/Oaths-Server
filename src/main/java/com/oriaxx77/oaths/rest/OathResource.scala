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
import com.oriaxx77.oaths.domain.repository.UserRepository
import Json.toJson
import scala.collection.JavaConverters._



@RestController
@RequestMapping(Array("/oaths"))
class OathResource @Autowired() ( oathRepository: OathRepository,
                                  userRepository: UserRepository ) {
 
  
//implicit object AppWriter extends Writes[Oath] {
//  def writes(oath: Oath): JsValue = JsObject(
//    Seq(
//      "id"    -> JsString(oath.oath),
//      "name"  -> JsString(oath.oath),
//      "users" -> toJson(Seq(1, 2, 3))
//    )
//  )
//}
  
  
  
  implicit val oathWrites = new Writes[Oath] {
                def writes(oath: Oath) = Json.obj(
                    "id" -> JsNumber( oath.id ),
                    "oath" -> oath.oath,
                    "lastModified" -> oath.lastModified,
                    "temptationFailedCount" -> JsNumber( oath.temptationFailedCount ),
                    "temptationFailedOverCome" -> JsNumber( oath.temptationOvercomeCount)
                  )
                }
 
  

  
  @RequestMapping(method = Array(RequestMethod.GET))
  def getOaths():String = {
    return Json.obj( "oaths" -> oathRepository.findAll().asScala ).toString()
  }
    
  
  
  
}