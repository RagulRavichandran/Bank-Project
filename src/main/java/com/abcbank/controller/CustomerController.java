//package com.abcbank.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.abcbank.dto.LoginDto;
//import com.abcbank.model.Customer;
//import com.abcbank.model.TokenReqRes;
//import com.abcbank.repository.CustomerRepo;
//import com.abcbank.service.CustomerService;
//import com.abcbank.utils.JwtTokenUtil;
//
//import jakarta.servlet.http.HttpServletRequest;
//@RestController
//@CrossOrigin
//public class CustomerController {
//	@Autowired
//	private CustomerService customerService;
//	@Autowired
//	private CustomerRepo customerRepo;
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	private String extractTokenFromHeader(HttpServletRequest request) {
//		// Extract token from the Authorization header or any other header where you
//		// pass the token
//		// For example, if you pass the token in the Authorization header as "Bearer
//		// <token>"
//		String authorizationHeader = request.getHeader("Authorization");
//		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//			return authorizationHeader.substring(7); // Remove "Bearer " prefix
//		}
//		return null;
//	}
//
//	
////	@PostMapping("/customerLogin")
////	public Object login(@RequestBody LoginDto loginDto) {
////		return customerService.validateUser(loginDto);
////	}
//	
//	
//	@PostMapping("/login_generate-tokens")   
//    public ResponseEntity<Object> generateToken(@RequestBody TokenReqRes tokenReqRes) {
//        Customer dbUser = customerRepo.findbyusername(tokenReqRes.getUserName());
//
//        if (dbUser == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
//        }
//
//        if (new BCryptPasswordEncoder().matches(tokenReqRes.getPassword(), dbUser.getPassword())) {
//            String token = jwtTokenUtil.generateToken(tokenReqRes.getUserName());
//            tokenReqRes.setToken(token);
//            tokenReqRes.setExpirationTime("60 Sec");
//            tokenReqRes.setCustomer_id(dbUser.getCustomer_id());
//
//            if ("yes".equalsIgnoreCase(dbUser.getBillpayment_registration_status())) {
//                tokenReqRes.setMessage("Loginsuccessfully");
//                return ResponseEntity.ok(tokenReqRes);
//            } else {
//                tokenReqRes.setMessage("not registered");
//                return ResponseEntity.ok(tokenReqRes);
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password Doesn't Match. Verify");
//        }
//    }
//}
package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.dto.LoginDto;
import com.abcbank.model.Customer;
import com.abcbank.model.TokenReqRes;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.service.CustomerService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login_generate-tokens")   
    public ResponseEntity<Object> generateToken(@RequestBody TokenReqRes tokenReqRes) {
        Customer dbUser = customerRepo.findbyusername(tokenReqRes.getUserName());

        if (dbUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't exist");
        }

        if (new BCryptPasswordEncoder().matches(tokenReqRes.getPassword(), dbUser.getPassword())) {
            String token = jwtTokenUtil.generateToken(tokenReqRes.getUserName());
            tokenReqRes.setToken(token);
            tokenReqRes.setExpirationTime("60 Sec");
            tokenReqRes.setCustomer_id(dbUser.getCustomer_id());

            if ("yes".equalsIgnoreCase(dbUser.getBillpayment_registration_status())) {
                tokenReqRes.setMessage("Loginsuccessfully");
            } else {
                tokenReqRes.setMessage("Not registered for bill payment");
            }
            return ResponseEntity.ok(tokenReqRes);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password Doesn't Match. Verify");
        }
    }
}

