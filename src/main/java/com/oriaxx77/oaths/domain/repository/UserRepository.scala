package com.oriaxx77.oaths.domain.repository

import org.springframework.data.repository.PagingAndSortingRepository

import com.oriaxx77.oaths.domain.enity.Oath
import com.oriaxx77.oaths.domain.enity.User
import java.util.Optional




// @RepositoryRestResource(collectionResourceRel = "oath", path = "oaths")
trait UserRepository extends PagingAndSortingRepository[User, java.lang.Long] {
  
  def findUserByEmail( email: String ): Optional[User]; 
  
  def findUserByAuthCode( authCode: String): Optional[User];
  
  def findUserByAuthToken( authToken: String): Optional[User];

}