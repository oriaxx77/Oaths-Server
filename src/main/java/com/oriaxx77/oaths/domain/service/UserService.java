package com.oriaxx77.oaths.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.oriaxx77.oaths.domain.enity.User;
import com.oriaxx77.oaths.domain.repository.UserRepository;



// TODO: refactor. Looks like a transaction script :(
// TODO: extract interface
@Component
public class UserService {
  
	private UserRepository userRepository;
	
	@Autowired
	public UserService( UserRepository userRepository ){
		this.userRepository = userRepository;
	}
	
  
  /** 
   *  Creates a user based on it's email.
   *  Returns with an authToken.
   */
  public String create( String email, String userName) {
    
    // TODO: use ScalaZ for validation
    // TODO: send auth email instead of returning the token.
    // TODO: token should not be created here.
    if ( email == null || email.length() == 0 )
      throw new IllegalArgumentException( "Missing email!" );
    
    return  userRepository.findUserByEmail( email )
			  			  .orElseGet( () -> { 
			  				  User newUser = new User( email, userName,    			  			  
			  										   UUID.randomUUID().toString(),
			  										   UUID.randomUUID().toString(),"" );
			  				  userRepository.save( newUser ); // TODO: sideffect!!!!
			  				  return newUser;})
			  			  .getAuthToken();    
  }
  
  /** Returns authToken */
  public String authByEmail( String email ) {
    // TODO: use ScalaZ for validation
    // TODO: refactor to send an auth email instead.
    User user = userRepository.findUserByEmail( email )
    								.orElseThrow( () -> new RuntimeException( "Not found" ) );
    user.setAuthToken( UUID.randomUUID().toString() );
    userRepository.save( user );	// WHY? It is [User,Long]
    return user.getAuthToken(); 
  }
  
  
  /** Returns authToken */
  public String authByCode( String authCode){
    // TODO: use ScalaZ for validation
    User user = userRepository.findUserByAuthCode( authCode )
    										.orElseThrow( () -> new RuntimeException( "Not found" ) );    
    user.setAuthToken( UUID.randomUUID().toString() );
    userRepository.save( user );	// WHY? It is [User,Long]
	return user.getAuthToken(); 
  }
  
  
  /** Sets user.pushNotificationDeviceToken */
  public void setPushNotificationDeviceToken( String authToken,
                                      	 String deviceToken ) { 
    // TODO:use ScalaZ for validation  
    // TODO: use ScalaZ for validation
    User user = userRepository.findUserByAuthToken( authToken )
    						  .orElseThrow( () -> new RuntimeException("Not found") );
    user.setPushNotifictionDeviceToken( deviceToken );
    userRepository.save( user );
  }
                          
  
  
}