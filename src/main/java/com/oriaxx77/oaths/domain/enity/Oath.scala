package com.oriaxx77.oaths.domain.enity

import javax.persistence.Entity
import scala.beans.BeanProperty
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.util.Date
import javax.persistence.ManyToOne
import javax.persistence.FetchType

@Entity
class Oath(@BeanProperty var oath: String) {

  @Deprecated
  def this() { this("") }

  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  @BeanProperty
  var oathTaker: User = _

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _
  var temptationFailedCount: Long = 0
  var temptationOvercomeCount: Long = 0
  var createdAt: Date = new Date()
  var lastModified: Date = new Date()

  override def toString = "Oath{ id: " + id + ", oath:" + oath + "}"
}