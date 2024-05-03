package com.abcbank.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table( name = "Customer")
public class Customer {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "customer_id")
	  private int customer_id;
	  
	  @Column(name = "name", length = 30, nullable = false)
	  private String name;
	  
	  @Column(name = "userName", length = 20, nullable = false)
	  private String userName;
	  
	  @Column(name = "password", length = 100, nullable = false)
	  private String password;
	  
	  @Column(name = "dob", nullable = false)
	  @Temporal(TemporalType.DATE)
	  private Date dob;
	  
	  @Column(name = "phone_no", length = 10, nullable = false)
	  private String phone_no;
	  
	  @Column(name = "door_street", length = 45, nullable = false)
	  private String door_street;
	  
	  @Column(name = "city", length = 20, nullable = false)
	  private String city;
	  
	  @Column(name = "pincode", length = 6, nullable = false)
	  private String pincode;
	  
	  @Column(name = "pan_no", length = 10, nullable = false)
	  private String pan_no;
	  
	  @Column(name = "email", length = 35, nullable = false)
	  private String email;
	  
	  @Column(name = "state", length = 30, nullable = false)
	  private String state;
	  
	  @Column(name = "billpayment_registration_status", length = 6, nullable = false)
	  private String billpayment_registration_status;
	  
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	  private List<Account> accounts = new ArrayList<Account>();
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	  private List<Bill_pay_registration> bill_pay_registrations  = new ArrayList<Bill_pay_registration>();
	 
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	  private List<Biller> billers = new ArrayList<Biller>();

		public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Bill_pay_registration> getBill_pay_registrations() {
		return bill_pay_registrations;
	}

	public void setBill_pay_registrations(List<Bill_pay_registration> bill_pay_registrations) {
		this.bill_pay_registrations = bill_pay_registrations;
	}

	public List<Biller> getBillers() {
		return billers;
	}

	public void setBillers(List<Biller> billers) {
		this.billers = billers;
	}

		public int getCustomer_id() {
			return customer_id;
		}
	
		public void setCustomer_id(int customer_id) {
			this.customer_id = customer_id;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
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
	
		public Date getDob() {
			return dob;
		}
	
		public void setDob(Date dob) {
			this.dob = dob;
		}
	
		public String getPhone_no() {
			return phone_no;
		}
	
		public void setPhone_no(String phone_no) {
			this.phone_no = phone_no;
		}
	
		public String getDoor_street() {
			return door_street;
		}
	
		public void setDoor_street(String door_street) {
			this.door_street = door_street;
		}
	
		public String getCity() {
			return city;
		}
	
		public void setCity(String city) {
			this.city = city;
		}
	
		public String getPincode() {
			return pincode;
		}
	
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
	
		public String getPan_no() {
			return pan_no;
		}
	
		public void setPan_no(String pan_no) {
			this.pan_no = pan_no;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getState() {
			return state;
		}
	
		public void setState(String state) {
			this.state = state;
		}
	
		public String getBillpayment_registration_status() {
			return billpayment_registration_status;
		}
	
		public void setBillpayment_registration_status(String billpayment_registration_status) {
			this.billpayment_registration_status = billpayment_registration_status;
		}
  
}
