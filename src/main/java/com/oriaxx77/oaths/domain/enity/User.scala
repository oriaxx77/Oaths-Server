package com.oriaxx77.oaths.domain.enity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.OneToMany
import javax.persistence.FetchType
import javax.persistence.CascadeType
import javax.persistence.Column
import scala.beans.BeanProperty

@Entity
case class User( @Column(unique=true) email: String, 
                 @Column(unique=true) name: String,
                 @Column(unique=true) authCode: String,
                 var authToken: String,
                 var pushNotifictionDeviceToken: String) {
  
  def this(){ this( "", "","","", "")}
  
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  var id: Long = _
  
  @OneToMany( mappedBy="oathTaker", fetch=FetchType.LAZY, 
              cascade=Array(CascadeType.ALL), orphanRemoval=true )
  var oaths: java.util.Set[Oath] = new java.util.HashSet[Oath];
  
  
}