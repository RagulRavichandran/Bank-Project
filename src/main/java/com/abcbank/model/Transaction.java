//package com.abcbank.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//
//
//@Entity
//@Table( name = "Transaction")
//public class Transaction {
//
//	
//	
//	
//		  @Id
//		  @GeneratedValue(strategy = GenerationType.AUTO)
//		  @Column(name = "transaction_id")
//		  private int transaction_id;
//		  
//		  @Column(name = "transaction_status", nullable = false)
//		  private int transaction_status;
//		  
//		  @ManyToOne(fetch = FetchType.LAZY)
//		  @JoinColumn(name = "payment_request_id")
//		  private Payment payment ;
//
//		public Payment getPayment() {
//			return payment;
//		}
//
//		public void setPayment(Payment payment) {
//			this.payment = payment;
//		}
//
//		public int getTransaction_id() {
//			return transaction_id;
//		}
//
//		public void setTransaction_id(int transaction_id) {
//			this.transaction_id = transaction_id;
//		}
//
//		public int getTransaction_status() {
//			return transaction_status;
//		}
//
//		public void setTransaction_status(int transaction_status) {
//			this.transaction_status = transaction_status;
//		}
//		  
//		
//
//	}
//
