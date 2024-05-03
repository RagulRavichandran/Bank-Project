package com.abcbank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcbank.model.Biller;
import com.abcbank.model.Payment;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{

	@Query(value ="SELECT p.* FROM payment p, biller b, category c "
			+ " WHERE c.category_code = b.category_code AND b.biller_id = p.biller_id  and c.category_code = :cat",nativeQuery = true)
	List<Payment> getPaymentWithCategory(@Param("cat") int categoryCode);
	
	
	@Query(value ="SELECT p.* FROM payment p, biller b, category c "
			+ " WHERE c.category_code = b.category_code AND b.biller_id = p.biller_id  and c.category_code = :cat and p.payment_date BETWEEN :startDate And :endDate ",nativeQuery = true)
	List<Payment> getPaymentWithCatAndDate(@Param("cat") int categoryCode, @Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate );
	
	@Query(value ="SELECT p.* FROM payment p, biller b, category c "
			+ " WHERE c.category_code = b.category_code AND b.biller_id = p.biller_id ",nativeQuery = true)
	List<Payment> getPaymentDeatils();
	
	@Query(value ="SELECT p.* FROM payment p, biller b, category c "
			+ " WHERE c.category_code = b.category_code AND b.biller_id = p.biller_id  and p.payment_date BETWEEN :startDate And :endDate ",nativeQuery = true)
	List<Payment> getPaymentWithDate(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate );
	
	
	//make payment
	@Query("select e from Payment e where e.payment_request_id=:pid")
	List<Payment> getpaymentRequestId(int pid);

	@Query("select p from Payment p where p.biller = :biller AND p.payment_status = :payment_status")
	List<Payment> findByBillerMenuAndPaymentStatus(@Param("biller") Biller biller, @Param("payment_status") String payment_status);

	
}
