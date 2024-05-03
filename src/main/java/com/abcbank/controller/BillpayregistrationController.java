package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.model.Bill_pay_registration;
import com.abcbank.service.BillpayregistrationService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
public class BillpayregistrationController {
	
	@Autowired
	private BillpayregistrationService billpayregistrationService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private String extractTokenFromHeader(HttpServletRequest request) {
		// Extract token from the Authorization header or any other header where you
		// pass the token
		// For example, if you pass the token in the Authorization header as "Bearer
		// <token>"
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7); // Remove "Bearer " prefix
		}
		return null;
	}

//	@GetMapping("/listPreferredAccounts/{customer_id}")
//	public Object fetchPreferredAccountByCustomerId(@PathVariable int customer_id) {
//		return bill_pay_registrationService.fetchPreferredAccountByCustomerId(customer_id);
//
//	}
//	
//	@PostMapping("/savePreferredAccounts")
//	public Object savepreferredAccount(@RequestBody Bill_pay_registration bill_pay_registration) {
//		return billpayregistrationService.savepreferredAccount(bill_pay_registration);
//	}
	
	// 2. Bill Pay Registration
		@PostMapping("/savePreferredAccounts")
		public Object savepreferredAccount(@RequestBody Bill_pay_registration billPayRegistration,HttpServletRequest request) {
		try {
	        String token = extractTokenFromHeader(request);
	        if (token == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
	        }
	        String tokenCheck = jwtTokenUtil.validateToken(token);

	        if (tokenCheck.equalsIgnoreCase("valid")) {
	            return new ResponseEntity<>(billpayregistrationService.savepreferredAccount(billPayRegistration), HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	    }
	}

//		// 4.Fetching Preferred Accounts By Customer Id
//		@GetMapping("/listPreferredAccounts/{customerId}")
//		public Object fetchPreferredAccountByCustomerId(@PathVariable int customerId) {
//			return billpayregistrationService.fetchPreferredAccountByCustomerId(customerId);
//		}
		
		@GetMapping("/getcustomerName/{customer_id}")
		public Object getCustomerNameById(@PathVariable ("customer_id")int customer_id,HttpServletRequest request) {
		
		try {
	        String token = extractTokenFromHeader(request);
	        if (token == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
	        }
	        String tokenCheck = jwtTokenUtil.validateToken(token);

	        if (tokenCheck.equalsIgnoreCase("valid")) {
	            return new ResponseEntity<>(billpayregistrationService.getCustomerNameById(customer_id), HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	    }
	}
		
		
		
		// 4.Fetching Preferred Accounts By Customer Id
		@GetMapping("/listPreferredAccounts/{customerId}")
		public Object fetchAccountByCustomerId(@PathVariable int customerId,HttpServletRequest request) {
		
		try {
	        String token = extractTokenFromHeader(request);
	        if (token == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
	        }
	        String tokenCheck = jwtTokenUtil.validateToken(token);

	        if (tokenCheck.equalsIgnoreCase("valid")) {
	            return new ResponseEntity<>(billpayregistrationService.fetchAccountByCustomerId(customerId), HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	    }
	}
}
