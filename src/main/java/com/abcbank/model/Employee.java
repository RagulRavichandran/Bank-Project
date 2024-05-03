package com.abcbank.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "employee_id")
		 private int employee_id;
	  
	  @Column(name = "userName", length = 25, nullable = false)
		 private String userName;
	
	  @Column(name = "password", length = 15, nullable = false)
		 private String password;
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
		 private List<Payment> payments = new ArrayList<Payment>();
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	  private List<Chequebook_request> chequebook_requests  = new ArrayList<Chequebook_request>();
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	  private List<Creditordebit_request> creditordebit_requests = new ArrayList<Creditordebit_request>();
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	  private List<Lostorstolen_request> lostorstolen_requests = new ArrayList<Lostorstolen_request>();

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
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

}
