package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.service.AccountService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
public class AccountController {
@Autowired
private AccountService accountService;

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

@GetMapping("/getAllAccountNo/{customer_id}")
public Object getAllAccountNumbers(@PathVariable("customer_id") int customer_id, HttpServletRequest request) {
    try {
        String token = extractTokenFromHeader(request);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
        }
        String tokenCheck = jwtTokenUtil.validateToken(token);

        if (tokenCheck.equalsIgnoreCase("valid")) {
            return new ResponseEntity<>(accountService.getAllAccNumber(customer_id), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
    }
}


//@GetMapping("/getAllAccountNo/{customer_id}")
//public Object getAllAccountNumbers(@PathVariable("customer_id") int customer_id) {
//	return accountService.getAllAccNumber(customer_id);
//
//}
}
