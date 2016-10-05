package com.oriaxx77.oaths.domain.repository

import java.util.UUID
import scala.collection._
import org.springframework.stereotype.Component
import scala.collection.concurrent.TrieMap
import com.oriaxx77.oaths.domain.enity.User


// TODO: refactor. Looks like a transaction script :(
@Component
class UserRepository {
  
  var emailUserMap = new TrieMap[String,User];
  
  /** 
   *  Creates a user based on it's email.
   *  Returns with an authToken.
   */
  def create( email: String ): String = {
    
    // TODO: use ScalaZ for validation
    // TODO: send auth email instead of returning the token.
    // TODO: token should not be created here.
    if ( !Option(email).isDefined || email.length() == 0 )
      throw new IllegalArgumentException( "Missing email!" )
    
    if ( emailUserMap.contains( email ) ){
      return emailUserMap(email).authToken
    }
    
    val authCode = UUID.randomUUID().toString();
    val authToken = UUID.randomUUID().toString();
    emailUserMap.put(email, User( email,
                                  authCode,
                                  authToken,
                                  "" ) );
    // TODO: return whole User?
    return authToken
  }
  
  /** Returns authToken */
  def authByEmail( email: String ): String = {
    // TODO: use ScalaZ for validation
    // TODO: refactor to send an auth email instead.
    if ( !emailUserMap.contains( email ) ) {
      throw new RuntimeException( "Not found" );
    }
    val user = emailUserMap( email )
    user.authToken = UUID.randomUUID().toString()
    return emailUserMap(email).authToken 
  }
  
  
  /** Returns authToken */
  def authByCode( authCode: String ): String = {
    // TODO:use ScalaZ for validation
     val user = emailUserMap.values.find( {user => user.authCode == authCode} )
     if ( user.isEmpty ){
       throw new RuntimeException( "Auth token not found." )  
     }
     user.get.authToken = UUID.randomUUID().toString()
     return user.get.authToken
  }
  
  
  /** Sets user.pushNotificationDeviceToken */
  def setPushNotificationDeviceToken( authToken: String,
                                      deviceToken: String ) { 
    // TODO:use ScalaZ for validation  
    val user = emailUserMap.values.find( {user => user.authToken == authToken} )
    if ( user.isEmpty ){
      throw new RuntimeException( "authToken not found" )
    }
    
    emailUserMap.put(user.get.email, user.get.copy( pushNotifictionDeviceToken = deviceToken )) 
  }
                          
  
  
}