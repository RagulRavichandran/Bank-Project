package com.abcbank.service;

import com.abcbank.dto.LoginDto;

public interface CustomerService {

	public Object validateUser(LoginDto loginDto);

}
