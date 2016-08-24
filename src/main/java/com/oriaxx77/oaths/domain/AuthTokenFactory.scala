package com.oriaxx77.oaths.domain

import java.util.UUID
import scala.collection._
import org.springframework.stereotype.Component
import scala.collection.concurrent.TrieMap

@Component
class AuthTokenFactory {
  
  var tokens = new TrieMap[String,String];
  
  
  
  def createForEmail( email: String ): String = {
    
    // TODO:use ScalaZ for this
    if ( !Option(email).isDefined || email.length() == 0 )
      throw new IllegalArgumentException( "Missing email!" )
    
    print( "Email: " + email )
    print( "\nTokens: " + tokens.hashCode() )
    if ( tokens.contains( email ) ){
      return tokens(email) 
    }
    val token = UUID.randomUUID().toString();
    tokens.put(email, token);   
    return token
  }
}