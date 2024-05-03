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
@Table( name = "Service_request")
public class Service_request {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "service_request_id")
	  private int service_request_id;
	
	  @Column(name = "request_type", length = 45, nullable = false)
	  private String request_type;
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
	  private List<Chequebook_request> chequebook_requests  = new ArrayList<Chequebook_request>();
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
	  private List<Creditordebit_request> creditordebit_requests = new ArrayList<Creditordebit_request>();
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
	  private List<Lostorstolen_request> lostorstolen_requests = new ArrayList<Lostorstolen_request>();

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

	public int getService_request_id() {
		return service_request_id;
	}

	public void setService_request_id(int service_request_id) {
		this.service_request_id = service_request_id;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

}
