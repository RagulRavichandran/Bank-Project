package com.abcbank.model;

import java.math.BigInteger;
import java.util.ArrayList;
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


@Entity
@Table( name = "Account")
public class Account {

	    @Id
	    @Column(name = "account_number",length = 17, nullable = false)
		private Long account_number;
	    
	    @Column(name = "account_type", length = 20, nullable = false)
		private String account_type;
			    
	    @Column(name = "account_balance", nullable = false)
		private Double account_balance;
			    
		@Column(name = "branch_name", length = 45, nullable = false)
		private String branch_name;
			    
	    @Column(name = "ifsc_code", length = 11, nullable = false)
		private String ifsc_code;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "customer_id")
		private Customer customer;
	    
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
		private List<Account_statement> account_statements = new ArrayList<Account_statement>();
	
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
		  private List<Chequebook_request> chequebook_requests  = new ArrayList<Chequebook_request>();
		  
		  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
		  private List<Creditordebit_request> creditordebit_requests = new ArrayList<Creditordebit_request>();
		  
		  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
		  private List<Lostorstolen_request> lostorstolen_requests = new ArrayList<Lostorstolen_request>();

		  
		 @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
		 private List<Payment> payments = new ArrayList<Payment>();
			
			
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		public List<Account_statement> getAccount_statements() {
			return account_statements;
		}
		public void setAccount_statements(List<Account_statement> account_statements) {
			this.account_statements = account_statements;
		}
		public List<Chequebook_request> getChequebook_requests() {
			return chequebook_requests;
		}
		public void setChequebook_requests(List<Chequebook_request> chequebook_requests) {
			this.chequebook_requests = chequebook_requests;
		}
		public List<Creditordebit_request> getCreditordebit_requests() {
			return creditordebit_requests;
		}
		public void setCreditordebit_requests(List<Creditordebit_request> creditordebit_requests) {
			this.creditordebit_requests = creditordebit_requests;
		}
		public List<Lostorstolen_request> getLostorstolen_requests() {
			return lostorstolen_requests;
		}
		public void setLostorstolen_requests(List<Lostorstolen_request> lostorstolen_requests) {
			this.lostorstolen_requests = lostorstolen_requests;
		}
		public List<Payment> getPayments() {
			return payments;
		}
		public void setPayments(List<Payment> payments) {
			this.payments = payments;
		}
		

		public Long getAccount_number() {
			return account_number;
		}
		public void setAccount_number(Long account_number) {
			this.account_number = account_number;
		}
		public String getAccount_type() {
			return account_type;
		}

		public void setAccount_type(String account_type) {
			this.account_type = account_type;
		}

		public Double getAccount_balance() {
			return account_balance;
		}

		public void setAccount_balance(Double account_balance) {
			this.account_balance = account_balance;
		}

		public String getBranch_name() {
			return branch_name;
		}

		public void setBranch_name(String branch_name) {
			this.branch_name = branch_name;
		}

		public String getIfsc_code() {
			return ifsc_code;
		}

		public void setIfsc_code(String ifsc_code) {
			this.ifsc_code = ifsc_code;
		}

	
}
