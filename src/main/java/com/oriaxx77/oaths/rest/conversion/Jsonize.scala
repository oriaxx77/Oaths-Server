package com.oriaxx77.oaths.rest.conversion

import play.api.libs.json.Writes
import play.api.libs.json.JsNumber
import com.oriaxx77.oaths.domain.enity.Oath
import play.api.libs.json.Json
import com.oriaxx77.oaths.domain.enity.User


object Jsonize {

  implicit val userWrites = new Writes[User] {
                def writes(u: User) = Json.obj(
                  "email" -> u.email,
                  "authCode" -> u.authCode,
                  "authToken" -> u.authToken,
                  "deviceToken" -> u.pushNotifictionDeviceToken
                  )
                }
   
    implicit val oathWrites = new Writes[Oath] {
                def writes(oath: Oath) = Json.obj(
                    "id" -> JsNumber( oath.id ),
                    "oath" -> oath.oath,
                    "lastModified" -> oath.lastModified,
                    "temptationFailedCount" -> JsNumber( oath.temptationFailedCount ),
                    "temptationFailedOverCome" -> JsNumber( oath.temptationOvercomeCount),
                    "oathTaker" -> oath.oathTaker
                  )
                }

}