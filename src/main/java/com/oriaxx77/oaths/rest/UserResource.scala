package com.oriaxx77.oaths.rest

import com.oriaxx77.oaths.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import com.oriaxx77.oaths.domain.repository.UserRepository
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import com.oriaxx77.oaths.domain.enity.User
import play.api.libs.json.Writes
import play.api.libs.json._


@RestController
@Autowired
class UserResource @Autowired() ( userRepository: UserRepository ) 
{
  
  implicit val userWrites = new Writes[User] {
                def writes(u: User) = Json.obj(
                  "email" -> u.email,
                  "authCode" -> u.authCode,
                  "authToken" -> u.authToken,
                  "deviceToken" -> u.pushNotifictionDeviceToken
                  )
                }
 
  
  @RequestMapping(value=Array("/user"), method = Array(RequestMethod.GET))
  def getUsers():String = {
    println( userRepository.emailUserMap.values )
    return Json.obj( "users" -> userRepository.emailUserMap.values ).toString()
  }
  
  
  
  @RequestMapping( value=Array("/authByCode"), method=Array(RequestMethod.POST) )  
  def authByCode( @RequestBody authCode: String ): String = {
    //TODO: invariants? Use ScalaZ
    return userRepository.authByCode( authCode )
  }
  
  @RequestMapping( value=Array("/authByEmail"), method=Array(RequestMethod.POST) )  
  def authByEmail( @RequestBody email: String ): String = {
    //TODO: invariants? Use ScalaZ
    return userRepository.authByEmail( email )
  }
  
  @RequestMapping( value=Array("/register"), method=Array(RequestMethod.PUT) )  
  def register( @RequestBody email: String ): String = {
    //TODO: invariants? Use ScalaZ
    //TODO: invariant: email is already taken
    return userRepository.create(email);
  }
  
  
  @RequestMapping( value=Array("/registerPushNotificationDeviceToken"), method=Array(RequestMethod.POST) )  
  def registerPushNotificationDeviceToken( @RequestParam authToken: String, 
                                           @RequestParam deviceToken: String ) {
      println( "deviceToken", deviceToken )
      userRepository.setPushNotificationDeviceToken(authToken, deviceToken)
  }
  
  // TODO: remove this. It is here only for testing
  @RequestMapping( value=Array("/sendPushNotification"), method=Array(RequestMethod.GET))
  def sendPushNotification(){
    userRepository.emailUserMap
                  .values
                  .filter { user => user.pushNotifictionDeviceToken != null && !user.pushNotifictionDeviceToken.isEmpty() }
                  .foreach { user => println( "Sending notification to " + user.pushNotifictionDeviceToken ) }
  }
}