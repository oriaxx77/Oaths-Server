package com.oriaxx77.oaths.domain.enity

import javax.persistence.Entity
import scala.beans.BeanProperty
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.util.Date



@Entity
class Oath(@BeanProperty var oath: String) {

	@Deprecated
	def this() {this("")}


//	@OneToMany(mappedBy = "book")
//	var reviews: ju.Set[Review] = new ju.HashSet()

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   
    var id: Long = _
    var temptationFailedCount: Long = _
    var temptationOvercomeCount: Long = _
    var createdAt: Date = _
    var lastModified: Date = _
    
        
    // def this() = this (null, null)
 
    // override def toString = id + " = " + firstName + " " + lastName
  
}