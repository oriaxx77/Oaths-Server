package com.oriaxx77.oaths.rest

import com.oriaxx77.oaths.domain.AuthTokenFactory
import org.springframework.beans.factory.annotation.Autowired
import com.oriaxx77.oaths.domain.AuthTokenFactory
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody


@RestController
@Autowired
class AuthTokenResource @Autowired() ( authTokenFactory: AuthTokenFactory ) {
  
  
  @RequestMapping( value=Array("/auth"), method=Array(RequestMethod.PUT) )  
  def auth( @RequestBody email: String ): String = {
    //TODO: invariants? Use ScalaZ
    return authTokenFactory.createForEmail( email );
  }
  
  def registerForPushNotification( authToken: String, 
                                   deviceToken: String )
  {
      
  }
}