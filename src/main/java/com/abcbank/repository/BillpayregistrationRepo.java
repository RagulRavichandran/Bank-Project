package com.abcbank.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Bill_pay_registration;

@Repository
public interface BillpayregistrationRepo extends JpaRepository<Bill_pay_registration, Integer> {
//	@Query("select e from Bill_pay_registration e where e.bill_payregistration_id=:bill_payregistration_id")
//	Optional<Bill_pay_registration> findByBillerId(@Param("bill_payregistration_id") int biller_id);
//
//	@Query(value = "select preferred_account1 from bill_pay_registration where preferred_account1=:inputAccountNo",nativeQuery = true)
//	Long findByPreferredAccoutnNo(@Param("inputAccountNo") BigInteger account_number);
//
//	@Query(value = "select preferred_account2 from bill_pay_registration where preferred_account2=:inputAccountNo",nativeQuery = true)
//	Long findByPreferredAccoutnNo2(@Param("inputAccountNo") BigInteger account_number);
//
//	@Query(value = "select * from bill_pay_registration where customer_id=:customer_id")
//	List<Bill_pay_registration> findByCustomerId(@Param("customer_id") int customer_id);
	
//	@Query("select e from BillPayRegistration e where e.biller_id=:biller_id")
//	Optional<Bill_pay_registration> findBybiller_id(@Param("biller_id") int biller_id);

//	@Query(value = "select preferred_account1 from bill_pay_registration where preferred_account1=:inputAccountNo", nativeQuery = true)
//	Long findByPreferredAccoutnNo(@Param("inputAccountNo") long inputAccountNo);
//
//	@Query(value = "select preferred_account2 from bill_pay_registration where preferred_account2=:inputAccountNo", nativeQuery = true)
//	Long findByPreferredAccoutnNo2(@Param("inputAccountNo") long inputAccountNo);
//
//	@Query(value = "select * from bill_pay_registration where customer_id=:customer_id", nativeQuery = true)
//	List<Bill_pay_registration> findByCustomerId(@Param("customer_id") int customer_id);

	@Query("select e from Bill_pay_registration e where e.bill_payregistration_id=:bill_payregistration_id")
	Optional<Bill_pay_registration> findByBillerId(@Param("bill_payregistration_id") int bill_payregistration_id);

	@Query(value = "select preferred_account1 from bill_pay_registration where preferred_account1=:inputAccountNo", nativeQuery = true)
	Long findByPreferredAccoutnNo(@Param("inputAccountNo") long inputAccountNo);

	@Query(value = "select preferred_account2 from bill_pay_registration where preferred_account2=:inputAccountNo", nativeQuery = true)
	Long findByPreferredAccoutnNo2(@Param("inputAccountNo") long inputAccountNo);

	
	@Query(value = "select * from bill_pay_registration where customer_id=:customer_id", nativeQuery = true)
	List<Bill_pay_registration> findByCustomerId(@Param("customer_id") int customer_id);
	
	
}


