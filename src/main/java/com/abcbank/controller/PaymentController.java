package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.dto.UpdatePayInstructionDto;
import com.abcbank.dto.ViewPaymentDto;
import com.abcbank.model.Payment;
import com.abcbank.service.PaymentService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
@CrossOrigin
@RestController
public class PaymentController {
	@Autowired
    private PaymentService paymentService;
	
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
	@PostMapping("/paymentFilter")
    public Object paymentFilter(@RequestBody ViewPaymentDto paymentDto, HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                System.out.println("paymeent" + paymentDto);
                return paymentService.viewPaymentFilter(paymentDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }

    @PostMapping("/makePayment")
    public Object savepay(@RequestBody Payment payment, HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                return paymentService.savepay(payment);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }

    @PutMapping("/updatebyid")
    public Object updateById(@RequestBody UpdatePayInstructionDto updatePayInstructionDto, HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                return paymentService.updateById(updatePayInstructionDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }

    @DeleteMapping("/deletepayment/{paymentRequestId}")
    public Object delete(@PathVariable int paymentRequestId, HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                return paymentService.deletepayment(paymentRequestId);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }

    @GetMapping("/getpayment/{paymentRequestId}")
    public Object GetpaymentById(@PathVariable int paymentRequestId, HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);
            String tokenCheck = jwtTokenUtil.validateToken(token);

            if (tokenCheck.equalsIgnoreCase("valid")) {
                return paymentService.GetpaymentById(paymentRequestId);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
        }
    }
//@PostMapping("/paymentFilter")
//public Object paymentFilter(@RequestBody ViewPaymentDto paymentDto)  {
//	System.out.println("paymeent"+paymentDto);
//	return paymentService.viewPaymentFilter(paymentDto);
//	
//}
//
//// maka a payment
//@PostMapping("/makePayment")
//public Object savepay(@RequestBody Payment payment) throws InstantiationException, IllegalAccessException, JAXBException
//{
//	return (paymentService.savepay(payment));
//}
//
//@PutMapping("/updatebyid")
//public Object updateById(@RequestBody UpdatePayInstructionDto updatePayInstructionDto) {
//	return paymentService.updateById(updatePayInstructionDto);
//}
//@DeleteMapping("/deletepayment/{paymentRequestId}")
//public Object delete(@PathVariable int paymentRequestId) {
//	return (paymentService.deletepayment(paymentRequestId));
//}
//
//@GetMapping("/getpayment/{paymentRequestId}")
//public Object GetpaymentById(@PathVariable int paymentRequestId) {
//	return (paymentService.GetpaymentById(paymentRequestId));
//}

}
