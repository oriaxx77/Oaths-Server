package com.oriaxx77.oaths.domain.service

import java.util.UUID
import scala.collection._
import org.springframework.stereotype.Component
import scala.collection.concurrent.TrieMap
import com.oriaxx77.oaths.domain.enity.User
import org.springframework.beans.factory.annotation.Autowired
import com.oriaxx77.oaths.domain.repository.UserRepository


// TODO: refactor. Looks like a transaction script :(
// TODO: extract interface
@Component
class UserService @Autowired() ( userRepository: UserRepository ) {
  
  var emailUserMap = new TrieMap[String,User];
  
  /** 
   *  Creates a user based on it's email.
   *  Returns with an authToken.
   */
  def create( email: String, userName: String ): String = {
    
    // TODO: use ScalaZ for validation
    // TODO: send auth email instead of returning the token.
    // TODO: token should not be created here.
    if ( !Option(email).isDefined || email.length() == 0 )
      throw new IllegalArgumentException( "Missing email!" )
    
    val userForEmail = userRepository.findUserByEmail( email )
    if ( userForEmail.isPresent() ){
      return userForEmail.get.authToken
    }
    
    val newUser = User( email,
                    userName,
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    "" )
    userRepository.save( newUser )
    // TODO: return whole User?
    return newUser.authToken
  }
  
  /** Returns authToken */
  def authByEmail( email: String ): String = {
    // TODO: use ScalaZ for validation
    // TODO: refactor to send an auth email instead.
    val user = userRepository.findUserByEmail( email )
    if ( user.isPresent() ) {
      throw new RuntimeException( "Not found" );
    }
    
    user.get.authToken =  UUID.randomUUID().toString()
    userRepository.save( user.get )
    return user.get.authToken 
  }
  
  
  /** Returns authToken */
  def authByCode( authCode: String ): String = {
    // TODO: use ScalaZ for validation
    val optUser = userRepository.findUserByAuthCode(authCode)
    if ( optUser.isPresent() ) {
      throw new RuntimeException( "Not found" );
    }
    val user = optUser.get
    user.authToken =  UUID.randomUUID().toString()
    userRepository.save( user )
    return user.authToken
  }
  
  
  /** Sets user.pushNotificationDeviceToken */
  def setPushNotificationDeviceToken( authToken: String,
                                      deviceToken: String ) { 
    // TODO:use ScalaZ for validation  
    // TODO: use ScalaZ for validation
    val optUser = userRepository.findUserByAuthToken( authToken )
    if ( optUser.isPresent() ) {
      throw new RuntimeException( "Not found" );
    }
    val user = optUser.get
    user.pushNotifictionDeviceToken = deviceToken
    userRepository.save( user )
  }
                          
  
  
}