package com.oriaxx77.oaths.domain

case class User( email: String, 
                 authCode: String,
                 var authToken: String,
                 var pushNotifictionDeviceToken: String) {
  
  
}