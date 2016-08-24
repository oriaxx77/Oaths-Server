package com.oriaxx77.oaths

import play.api.libs.json.Writes
import com.oriaxx77.oaths.domain.Person
import play.api.libs.json.Json

/**
 * Then instead of writing a main method, you place the code you would have put 
 * in the main method directly between the curly braces of the singleton object. 
 * You can access command-line arguments via an array of strings named args. 
 */
object FallWinterSpringSummer {
//  for ( season <- List("fall","winter","spring","summer") )
//    println( season )
  
  def main(args: Array[String]): Unit = {
  val userList = List( new Person("a","a", "a"), new Person( "b", "b", "b") )
          
    implicit val personWrites = new Writes[Person] {
                  def writes(p: Person) = Json.obj(
                    "firstName" -> p.firstName,
                    "surName" -> p.surName,
                    "email" -> p.email
                  )
                }
    
    
    println( userList );
   val json = Json.obj( "people" -> userList )
   println( json )
  }
}