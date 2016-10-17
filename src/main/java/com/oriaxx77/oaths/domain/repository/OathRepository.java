package com.oriaxx77.oaths.domain.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oriaxx77.oaths.domain.enity.Oath;
import com.oriaxx77.oaths.domain.enity.User;




// @RepositoryRestResource(collectionResourceRel = "oath", path = "oaths")
public interface OathRepository extends PagingAndSortingRepository<Oath, java.lang.Long> 
{
	Iterable<Oath> findByOathTaker( User oathTaker ); 
	
	@EntityGraph( value="Oath.oathTaker", type=EntityGraphType.FETCH )
	Iterable<Oath> findByOathTakerNot( User oathTaker );
	
	
	@Override
	@EntityGraph( value="Oath.oathTaker", type=EntityGraphType.FETCH )
	Iterable<Oath> findAll();

}