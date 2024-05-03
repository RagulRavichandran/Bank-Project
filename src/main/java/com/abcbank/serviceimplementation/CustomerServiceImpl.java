//package com.abcbank.serviceimplementation;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.abcbank.dto.LoginDto;
//import com.abcbank.model.Customer;
//import com.abcbank.repository.CustomerRepo;
//import com.abcbank.service.CustomerService;
//
//
//@Service
//public class CustomerServiceImpl implements CustomerService{
//	@Autowired
//	private CustomerRepo customerRepo;
//
//
//	@Override
//	public Object validateUser(LoginDto loginDto) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		String username = loginDto.getUserName();
//		String password = loginDto.getPassword();
//		
//		
//			Customer login = customerRepo.findbyusername(username);
//			
//			if(login != null) {
//				if(login.getPassword().equals(password)) {
//					map.put("status", "success");
//					map.put("message", "Login successful");
//				}
//				else {
//					map.put("status", "error");
//					map.put("message", "Invalid password");
//					map.put("Customer Name: ", login.getName());
//				}
//			}
//			else {
//				map.put("status", "error");
//				map.put("message", "Customer not found");
//			}
//		return map;
//	}
//}


package com.abcbank.serviceimplementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.dto.LoginDto;
import com.abcbank.model.Customer;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public Object validateUser(LoginDto loginDto) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = loginDto.getUserName();
        String password = loginDto.getPassword();
        String status = loginDto.getBillpayment_registration_status();

            try {
                Customer login = customerRepo.findbyusername(username);

            if(login != null) {
                if(login.getPassword().equals(password)) {
                    if(login.getBillpayment_registration_status().equals("yes")) {
                        map.put("status", "success");
                        map.put("message", "Login successful");
                        map.put("customerName",login.getName());
                        map.put("customerId",login.getCustomer_id());
                        
                        return map;
                    }
                    if(login.getBillpayment_registration_status().equals("no")) {
                        map.put("status", "not registered");
                        map.put("message", "Customer not registered");
                        map.put("customerName",login.getName());
                        map.put("customerId",login.getCustomer_id());

                        return map;
                    }
                }
else {
                    map.put("status", "error");
                    map.put("message", "Invalid password");
                    return map;
                }
            }
            else {
                map.put("status","error");
                map.put("message", "Empty field");
                return map;
            }
            }
            catch (Exception e) {
                map.put("status", "error");
                map.put("message", "An error occured during login");
            }
        return map;
    }
}