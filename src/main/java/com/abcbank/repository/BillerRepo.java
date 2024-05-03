package com.abcbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Biller;

@Repository
public interface BillerRepo extends JpaRepository<Biller, Integer>{
//	@Query(value ="select * from biller  where customer_id= :customer_id",nativeQuery = true)
//    List<Biller> findAllByCustomerId(@Param("customer_id") int customer_id);
//    
	@Query(value ="select * from biller  where biller_id= :biller_id",nativeQuery = true)
    List<Biller> findAllByBillerId(@Param("biller_id") int biller_id);
	
	@Query(value ="select * from biller  where customer_id= :customer_id",nativeQuery = true)
	List<Biller> findBycustomer(int customer_id);
}
