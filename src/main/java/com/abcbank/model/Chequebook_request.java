package com.abcbank.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table( name = "Chequebook_request")
public class Chequebook_request {

	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "chequebook_request_id")
	  private int chequebook_request_id;
	
	  @Column(name = "no_of_cheque_leaves", length = 3, nullable = false)
	  private String no_of_cheque_leaves;
	  
	  @Column(name = "request_date", nullable = false)
	  @Temporal(TemporalType.DATE)
	  private Date request_date;
	  
	  @Column(name = "request_message", length = 75, nullable = false)
	  private String request_message;
	  
	  @Column(name = "response_date")
	  @Temporal(TemporalType.DATE)
	  private Date response_date;
	  
	  @Column(name = "response_message", length = 75)
	  private String response_message;
	  
	  @Column(name = "response_status", length = 10)
	  private String response_status;
	  
	  @Column(name = "process_id")
	  private int process_id;
	  
	  @Column(name = "css_request_id")
	  private int css_request_id;
	  
	  @Column(name= "received_status")
	  private String received_status;
	  
	  public String getReceived_status() {
		return received_status;
	}

	public void setReceived_status(String received_status) {
		this.received_status = received_status;
	}

	public int getCss_request_id() {
		return css_request_id;
	}

	public void setCss_request_id(int css_request_id) {
		this.css_request_id = css_request_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "service_request_id")
	  private Service_request request;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "account_number")
	  private Account account;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "employee_id")
		private Employee employee;
	  
	  
	  public Service_request getRequest() {
		return request;
	}

	public void setRequest(Service_request request) {
		this.request = request;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getChequebook_request_id() {
			return chequebook_request_id;
		}
	  
	  public void setChequebook_request_id(int chequebook_request_id) {
			this.chequebook_request_id = chequebook_request_id;
		}

		public String getNo_of_cheque_leaves() {
			return no_of_cheque_leaves;
		}

		public void setNo_of_cheque_leaves(String no_of_cheque_leaves) {
			this.no_of_cheque_leaves = no_of_cheque_leaves;
		}

		public Date getRequest_date() {
			return request_date;
		}

		public void setRequest_date(Date request_date) {
			this.request_date = request_date;
		}

		public String getRequest_message() {
			return request_message;
		}

		public void setRequest_message(String request_message) {
			this.request_message = request_message;
		}

		public Date getResponse_date() {
			return response_date;
		}

		public void setResponse_date(Date response_date) {
			this.response_date = response_date;
		}

		public String getResponse_message() {
			return response_message;
		}

		public void setResponse_message(String response_message) {
			this.response_message = response_message;
		}

		public String getResponse_status() {
			return response_status;
		}

		public void setResponse_status(String response_status) {
			this.response_status = response_status;
		}

		public int getProcess_id() {
			return process_id;
		}

		public void setProcess_id(int process_id) {
			this.process_id = process_id;
		}

}
