package com.abcbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	@Query("select e from Customer e where e.userName = :userName")
	Customer findbyusername(@Param("userName") String userName);
	
	//Jwt
	@Query("select e from Customer e where e.userName = :userName")
	Customer findByUsername(@Param("userName") String userName);

}
  