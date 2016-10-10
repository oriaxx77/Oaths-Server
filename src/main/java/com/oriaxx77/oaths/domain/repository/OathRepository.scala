package com.oriaxx77.oaths.domain.repository

import org.springframework.data.repository.PagingAndSortingRepository

import com.oriaxx77.oaths.domain.enity.Oath
import com.oriaxx77.oaths.domain.enity.User




// @RepositoryRestResource(collectionResourceRel = "oath", path = "oaths")
trait OathRepository extends PagingAndSortingRepository[Oath, java.lang.Long] {

	def findByOathTaker( oathTaker: User ):java.lang.Iterable[Oath] 
	
	def findByOathTakerNot( oathTaker: User ): java.lang.Iterable[Oath]

}