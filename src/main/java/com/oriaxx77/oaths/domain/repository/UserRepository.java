package com.oriaxx77.oaths.domain.repository;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.oriaxx77.oaths.domain.enity.User;




// @RepositoryRestResource(collectionResourceRel = "oath", path = "oaths")
public interface UserRepository extends PagingAndSortingRepository<User, java.lang.Long> 
{
  
  Optional<User> findUserByEmail( String email ); 
  
  Optional<User> findUserByAuthCode( String authCode);
  
  Optional<User> findUserByAuthToken( String authToken );

  Iterable<User> findUserByPushNotifictionDeviceTokenIsNotNull();
}