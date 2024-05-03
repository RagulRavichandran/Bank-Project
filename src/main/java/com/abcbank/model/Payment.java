package com.abcbank.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table( name = "Payment")
public class Payment {
	
	 @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "payment_request_id")
	  private int payment_request_id;
	  
	  @Column(name = "bill_amount", nullable = false)
	  private Double bill_amount;
	  
	  @Temporal(TemporalType.DATE)
	  @Column(name = "payment_date")
	  private Date payment_date;
	  
	  @Temporal(TemporalType.DATE)
	  @Column(name = "payment_due_date")
	  private Date payment_due_date;
	  
	  @Column(name = "payment_status", length = 9, nullable = false)
	  private String payment_status;
	  
	  public Date getPayment_date() {
		return payment_date;
	}



	@Column(name = "remarks", length = 20)
	  private String remarks;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "account_number")
	  private Account account;
	  
	  
	  @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "biller_id")
		private Biller biller;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "employee_id")
		private Employee employee;
	  
	  @Column(name = "pay_now_status",length = 3,nullable = false)
	  private String pay_now_status;
	  
	  public String getPay_now_status() {
		return pay_now_status;
	}

	public void setPay_now_status(String pay_now_status) {
		this.pay_now_status = pay_now_status;
	}



//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "payment")
//		private List<Transaction> transactions = new ArrayList<Transaction>();
//	  
	  @Column(name= "received_status")
	  private String received_status;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Biller getBiller() {
		return biller;
	}

	public void setBiller(Biller biller) {
		this.biller = biller;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

//	public List<Transaction> getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(List<Transaction> transactions) {
//		this.transactions = transactions;
//	}

	public int getPayment_request_id() {
		return payment_request_id;
	}

	public void setPayment_request_id(int payment_request_id) {
		this.payment_request_id = payment_request_id;
	}

	public Double getBill_amount() {
		return bill_amount;
	}

	public void setBill_amount(Double bill_amount) {
		this.bill_amount = bill_amount;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public Date getPayment_due_date() {
		return payment_due_date;
	}

	public void setPayment_due_date(Date payment_due_date) {
		this.payment_due_date = payment_due_date;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getReceived_status() {
		return received_status;
	}

	public void setReceived_status(String received_status) {
		this.received_status = received_status;
	}
}
