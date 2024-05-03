package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.model.Account;
import com.abcbank.repository.AccountRepo;
import com.abcbank.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
@Autowired
private AccountRepo accountRepo;

@Override
public Object getAllAccNumber(int customer_id) {
	List<Account> accounts = accountRepo.getAllAccountByCustomerID(customer_id);
	List<Map<String, Object>> accountNumbers = new ArrayList<>();
	for (Account a : accounts) {
		Map<String, Object> map = new HashMap<>();
		map.put("accountNumber", a.getAccount_number());
		map.put("accountBalance",  a.getAccount_balance());
		accountNumbers.add(map);
	}
	return accountNumbers;
}

}
