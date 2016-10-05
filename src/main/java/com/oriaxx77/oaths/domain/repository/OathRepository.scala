package com.oriaxx77.oaths.domain.repository

import org.springframework.data.repository.PagingAndSortingRepository

import com.oriaxx77.oaths.domain.enity.Oath




// @RepositoryRestResource(collectionResourceRel = "oath", path = "oaths")
trait OathRepository extends PagingAndSortingRepository[Oath, java.lang.Long] {

	

}