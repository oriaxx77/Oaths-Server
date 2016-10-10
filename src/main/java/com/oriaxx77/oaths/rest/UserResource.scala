package com.oriaxx77.oaths.rest


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import com.oriaxx77.oaths.domain.enity.User
import play.api.libs.json.Writes
import play.api.libs.json._
import com.oriaxx77.oaths.rest.conversion.Jsonize
import com.oriaxx77.oaths.domain.service.UserService


@RestController
@Autowired
class UserResource @Autowired() ( userService: UserService ) 
{
  
  
  
  @RequestMapping(value=Array("/user"), method = Array(RequestMethod.GET))
  def getUsers():String = {
    println( userService.emailUserMap.values )
    import Jsonize._
    return Json.obj( "users" -> userService.emailUserMap.values ).toString()
  }
  
  
  
  @RequestMapping( value=Array("/authByCode"), method=Array(RequestMethod.POST) )  
  def authByCode( @RequestBody authCode: String ): String = {
    //TODO: invariants? Use ScalaZ
    return userService.authByCode( authCode )
  }
  
  @RequestMapping( value=Array("/authByEmail"), method=Array(RequestMethod.POST) )  
  def authByEmail( @RequestBody email: String ): String = {
    //TODO: invariants? Use ScalaZ
    return userService.authByEmail( email )
  }
  
  @RequestMapping( value=Array("/register"), method=Array(RequestMethod.PUT) )  
  def register( @RequestBody email: String ): String = {
    //TODO: invariants? Use ScalaZ
    //TODO: invariant: email is already taken
    //TODO: get username
    return userService.create(email,"unknown");
  }
  
  
  @RequestMapping( value=Array("/registerPushNotificationDeviceToken"), method=Array(RequestMethod.POST) )  
  def registerPushNotificationDeviceToken( @RequestParam authToken: String, 
                                           @RequestParam deviceToken: String ) {
      println( "deviceToken", deviceToken )
      userService.setPushNotificationDeviceToken(authToken, deviceToken)
  }
  
  // TODO: remove this. It is here only for testing
  @RequestMapping( value=Array("/sendPushNotification"), method=Array(RequestMethod.GET))
  def sendPushNotification(){
    userService.emailUserMap
                  .values
                  .filter { user => user.pushNotifictionDeviceToken != null && !user.pushNotifictionDeviceToken.isEmpty() }
                  .foreach { user => println( "Sending notification to " + user.pushNotifictionDeviceToken ) }
  }
}