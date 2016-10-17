package com.oriaxx77.oaths.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oriaxx77.oaths.domain.enity.User;
import com.oriaxx77.oaths.domain.repository.UserRepository;
import com.oriaxx77.oaths.domain.service.UserService;

// case class RegisterBody( email:String, userName:String );

@RestController
public class UserResource 
{
	
	private UserService userService;
	private UserRepository userRepository;

	@Autowired
	public UserResource(UserService userService, UserRepository userRepository) 
	{
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@RequestMapping(value="/users", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE} )
	public Iterable<User> getUsers()   
	{
		return userRepository.findAll();
	} 
  
  
	@RequestMapping( value="/authByCode", method={RequestMethod.POST}, produces={MediaType.TEXT_PLAIN_VALUE} )  
	public String authByCode( @RequestBody String authCode )
	{
		return userService.authByCode( authCode );
	}
	
	@RequestMapping( value="/authByEmail", method={RequestMethod.POST}, produces={MediaType.TEXT_PLAIN_VALUE} )  
	public String authByEmail( @RequestBody String email )
	{
		return userService.authByEmail( email );
	}

	@RequestMapping( value="/register", method={RequestMethod.PUT}, produces={MediaType.TEXT_PLAIN_VALUE} )
	public String register( @RequestBody User user ) {
		return userService.create( user.getEmail(), user.getName() );
	}
	
//  def register( @RequestBody user: RegisterBody ): String = {
//  //TODO: invariants? Use ScalaZ
//  //TODO: invariant: email is already taken
//  //TODO: get username    
//  return userService.create( user.email,user.userName);
//}

	
//  
//  @RequestMapping( value=Array("/authByEmail"), method=Array(RequestMethod.POST) )  
//  def authByEmail( @RequestBody email: String ): String = {
//    //TODO: invariants? Use ScalaZ
//    return userService.authByEmail( email )
//  }
//  
//  @RequestMapping( value=Array("/register"), method=Array(RequestMethod.PUT) )  
//  def register( @RequestBody user: RegisterBody ): String = {
//    //TODO: invariants? Use ScalaZ
//    //TODO: invariant: email is already taken
//    //TODO: get username    
//    return userService.create( user.email,user.userName);
//  }
//  
//  
//  @RequestMapping( value=Array("/registerPushNotificationDeviceToken"), method=Array(RequestMethod.POST) )  
//  def registerPushNotificationDeviceToken( @RequestParam authToken: String, 
//                                           @RequestParam deviceToken: String ) {
//      println( "deviceToken", deviceToken )
//      userService.setPushNotificationDeviceToken(authToken, deviceToken)
//  }
//  
//  // TODO: remove this. It is here only for testing
//  @RequestMapping( value=Array("/sendPushNotification"), method=Array(RequestMethod.GET))
//  def sendPushNotification(){
////    userService.emailUserMap
////                  .values
////                  .filter { user => user.pushNotifictionDeviceToken != null && !user.pushNotifictionDeviceToken.isEmpty() }
////                  .foreach { user => println( "Sending notification to " + user.pushNotifictionDeviceToken ) }
//  }
}