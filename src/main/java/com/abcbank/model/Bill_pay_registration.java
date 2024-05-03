package com.abcbank.model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table( name = "Bill_pay_registration")
public class Bill_pay_registration {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "bill_payregistration_id")
      private int bill_payregistration_id;
	  
	  @Column(name = "preferred_account1", nullable = false)
      private BigInteger preferred_account1;
	  
	  @Column(name = "preferred_account2")
	  private BigInteger preferred_account2;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "customer_id")
		private Customer customer;

	public int getBill_payregistration_id() {
		return bill_payregistration_id;
	}

	public void setBill_payregistration_id(int bill_payregistration_id) {
		this.bill_payregistration_id = bill_payregistration_id;
	}

	public BigInteger getPreferred_account1() {
		return preferred_account1;
	}

	public void setPreferred_account1(BigInteger preferred_account1) {
		this.preferred_account1 = preferred_account1;
	}

	public BigInteger getPreferred_account2() {
		return preferred_account2;
	}

	public void setPreferred_account2(BigInteger preferred_account2) {
		this.preferred_account2 = preferred_account2;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
