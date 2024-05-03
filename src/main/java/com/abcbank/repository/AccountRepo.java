	package com.abcbank.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
//	@Query(value = "Select * from account where customer_id=:customer_id", nativeQuery = true)
//	List<Account> findAccountsBycustomer_id(@Param("customer_id") int customer_id);

	@Query("SELECT a FROM Account a WHERE a.account_number = :account_number")
	Optional<Account> findByAccNo(@Param("account_number") Long account_number);

//	@Modifying
//	@Query("UPDATE Account a SET a.account_balance = :account_balance WHERE a.account_number = :account_number")
//	@Transactional
//	void updateAccountBalance(@Param("account_number") Long account_number, @Param("account_balance") double newBalance);

	@Query("select a from Account a where a.customer.customer_id=:cid")
	List<Account> getAllAccountByCustomerID(@Param("cid")int cid);

	@Query(value = "select account_balance from account where account_number=:inputAccountNo ", nativeQuery = true)
	double getAccountBalanceByAccNo(@Param("inputAccountNo") long inputAccountNo);

	@Query(value = "Select * from account where customer_id=:customer_id", nativeQuery = true)
	List<Account> findAccountsByCustomerId(@Param("customer_id") int customer_id);

	
}
