package com.abcbank.service;

import com.abcbank.model.Bill_pay_registration;

public interface BillpayregistrationService {

//	public Object fetchPreferredAccountByCustomerId(int customerId);

	public Object savepreferredAccount(Bill_pay_registration bill_pay_registration);

	public Object getCustomerNameById(int customerId);

	public Object savebillregister(Bill_pay_registration billPayRegistration);

	public Object fetchAccountByCustomerId(int customerId);

//	public Object getCustomerNameByCustomerId(int customerId);
	

	
//	public Object getCustomerNameById(int customerId);
//
//	public Object fetchPreferredAccountByCustomerId(int customerId);
//
//	public Object savebillregister(Bill_pay_registration billPayRegistration);
//
//	public Object savepreferredAccount(Bill_pay_registration billPayRegistration);



}
