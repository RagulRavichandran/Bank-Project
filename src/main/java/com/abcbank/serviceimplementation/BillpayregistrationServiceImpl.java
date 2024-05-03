package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.model.Account;
import com.abcbank.model.Bill_pay_registration;
import com.abcbank.model.Customer;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.BillpayregistrationRepo;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.service.BillpayregistrationService;


@Service
public class BillpayregistrationServiceImpl implements BillpayregistrationService{

@Autowired
private BillpayregistrationRepo billpayregistrationRepo;

@Autowired
private CustomerRepo customerRepo;
@Autowired
private AccountRepo accountRepo;

//public Object fetchPreferredAccountByCustomerId(int customerId) {
//
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		List<Bill_pay_registration> billPayRegistrations = billpayregistrationRepo.findByCustomerId(customerId);
//
//		Customer customer = customerRepo.findById(customerId).orElse(null);
//		String registrationStatus = customer.getBillpayment_registration_status();
//
//		if (!registrationStatus.equalsIgnoreCase("Yes")) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("Status", "200");
//			map.put("Message", "no preffered accounts are set for this customer");
//
//			list.add(map);
//		} else {
//			for (Bill_pay_registration billPayRegistration : billPayRegistrations) {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("preferredAccount1", billPayRegistration.getPreferred_account1());
//				map.put("preferredAccount2", billPayRegistration.getPreferred_account2());
//				list.add(map);
//			}
//		}
//		return list;
//	}
//
//@Override
//public Object savepreferredAccount(Bill_pay_registration bill_pay_registration) {
//	Map<String, Object> map = new HashMap<String, Object>();
//
//	if (bill_pay_registration.getPreferred_account1() == null) {
//				bill_pay_registration.getPreferred_account1();
//		map.put("Status: ", "200");
//		map.put("Message", "preferred account 1 is mandatory");
//		return map;
//	}
//
//	int customer_id = bill_pay_registration.getCustomer().getCustomer_id();
//
//	List<Account> accounts = accountRepo.findAccountsBycustomer_id(customer_id);
//
//
//	boolean flag1 = false;
//	boolean flag2 = false;
//
//	for (Account account : accounts) {
//		if (account.getAccount_number() == bill_pay_registration.getPreferred_account1()) {
//			flag1 = true;
//		}
//		if (bill_pay_registration.getPreferred_account2() != null) {
//			if (account.getAccount_number() == bill_pay_registration.getPreferred_account2()) {
//				flag2 = true;
//			}
//		}
//	}
//
//	if (flag1 = false) {
//		map.put("Status: ", "200");
//		map.put("Message", "preferred account1 doesnt belong to this customer");
//		return map;
//	}
//	if (flag2 = false) {
//		map.put("Status: ", "200");
//		map.put("Message", "preferred account2 doesnt belong to this customer");
//		return map;
//	}
//	if (customer_id == 0) {
//		map.put("Status: ", "200");
//		map.put("Message", "customer id needed for input");
//		return map;
//	}
//	Customer customer = customerRepo.findById(customer_id).orElse(null);
//
//	if (customer == null) {
//		map.put("Status: ", "200");
//		map.put("Message", "customer not found");
//		return map;
//	}
//	if (customer.getBillpayment_registration_status().equalsIgnoreCase("YES")) {
//				customer.getBillpayment_registration_status();
//		map.put("Status: ", "200");
//		map.put("Message", "this customer is already registered");
//		return map;
//	}
//
//	if (bill_pay_registration.getPreferred_account1().equals(bill_pay_registration.getPreferred_account2())) {
//				bill_pay_registration.getPreferred_account1().equals(bill_pay_registration.getPreferred_account2());
//		map.put("Status: ", "200");
//		map.put("Message", "both accounts cant be same");
//		return map;
//	}
//	customer.setBillpayment_registration_status("Yes");
//	customerRepo.save(customer);
//	bill_pay_registrationRepo.save(bill_pay_registration);
//	map.put("Status: ", "200");
//	map.put("Message", "saved successfully");
//
//	return map;
//}
	
	

	//fetch customer name by id(need controller class)
//	@Override
//	public Object getCustomerNameByCustomerId(int customer_Id) {
		
//		return null;
//	}
	
	
	@Override
	public Object savepreferredAccount(Bill_pay_registration billPayRegistration) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (billPayRegistration.getPreferred_account1() == null) {

			map.put("Status: ", "error");
			map.put("Message", "preferred account 1 is mandatory");
			return map;
		}
		
		else {
			billpayregistrationRepo.save(billPayRegistration);
			Customer customer=customerRepo.findById(billPayRegistration.getCustomer().getCustomer_id()).orElse(null);;
			customer.setBillpayment_registration_status("yes");
			customerRepo.saveAndFlush(customer);
			map.put("Status: ", "200");
			map.put("Message", "saved successfully");
		}

		return map;
	}

	
	//saves all accounts in the preferred account field
	@Override
	public Object savebillregister(Bill_pay_registration billPayRegistration) {

		Map<String, Object> map = new HashMap<>();

		if (billPayRegistration.getPreferred_account1() == null) {
			map.put("status", "error");
			map.put("msg", "please enter preferred account 1");
		}
		else if (billPayRegistration.getPreferred_account1().equals(billPayRegistration.getPreferred_account2())) {

			map.put("status", "error");
			map.put("msg", "both accounts should not be same");
			return map;
		}
		
		else {
			billpayregistrationRepo.save(billPayRegistration);
			
			map.put("status", "success");
			map.put("msg", "data saved sucessfully");
			map.put("billPayregistrationId: ", billPayRegistration.getBill_payregistration_id());
			return map;
		}
		return map;
	}
	
	
	//fetch all accounts of customers by customer id
//	@Override
//	public Object fetchPreferredAccountByCustomerId(int customerId) {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		List<Bill_pay_registration> billPayRegistrations = billpayregistrationRepo.findByCustomerId(customerId);
//
//		Customer customer = customerRepo.findById(customerId).orElse(null);
//		String registrationStatus = customer.getBillpayment_registration_status();
//
//		if (!registrationStatus.equalsIgnoreCase("Yes")) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("Status", "200");
//			map.put("Message", "no preferred accounts are set for this customer");
//
//			list.add(map);
//		} else {
//			for (Bill_pay_registration billPayRegistration : billPayRegistrations) {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("preferredAccount1", billPayRegistration.getPreferred_account1());
//				map.put("preferredAccount2", billPayRegistration.getPreferred_account2());
//				list.add(map);
//			}
//		}
//		return list;
//	}


	@Override
	public Object getCustomerNameById(int customer_id) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    Bill_pay_registration bill_pay_registration = billpayregistrationRepo.findById(customer_id).orElse(null);
	    
	    if (bill_pay_registration != null && bill_pay_registration.getCustomer() != null) {
	        map.put("CustomerName", bill_pay_registration.getCustomer().getName());
	    } else {
	        map.put("status", "error");
	        map.put("msg", "Enter a valid customer id");
	    }
	    
	    return map;
	}


	@Override
	public Object fetchAccountByCustomerId(int customerId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Bill_pay_registration> accounts = billpayregistrationRepo.findByCustomerId(customerId);

//		Customer customer = customerRepo.findById(customerId).orElse(null);
//		String registrationStatus = customer.getBillpayment_registration_status();

//		if (!registrationStatus.equalsIgnoreCase("Yes")) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("Status", "error");
//			map.put("Message", "no accounts are set for this customer");
//
//			list.add(map);
//		} else {
//			for (Account a : accounts) {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("Account: ", a.getAccount_number());
////				map.put("preferredAccount2", billPayRegistration.getPreferred_account2());
//				list.add(map);
//			}
//		}
//		return list;
		
		List<Map<String, Object>> accountNumbers = new ArrayList<>();
		
		for(Bill_pay_registration a : accounts) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("preferredAccount1", a.getPreferred_account1());
		    map.put("preferredAccount2", a.getPreferred_account2());
		    
		    accountNumbers.add(map);

		}
		
		return accountNumbers;
	}
}
