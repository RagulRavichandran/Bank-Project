package com.abcbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.dto.UpdatebillerDto;
import com.abcbank.model.Biller;
import com.abcbank.service.BillerService;
import com.abcbank.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
@CrossOrigin
@RestController
public class BillerController {
	@Autowired
private BillerService billerService;
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	private String extractTokenFromHeader1(HttpServletRequest request) {
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
	
	@PostMapping("/addNewBiller")
	public Object addNewBiller(@RequestBody Biller biller, HttpServletRequest request) {
	    try {
	        String token = extractTokenFromHeader1(request);
	        if (token == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
	        }
	        String tokenCheck = jwtTokenUtil.validateToken(token);

	        if (tokenCheck.equalsIgnoreCase("valid")) {
	            return new ResponseEntity<>(billerService.addbiller(biller), HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	    }
	}

//	 @PostMapping("/addNewBiller")
//	 public Object addNewBiller(@RequestBody Biller biller) {
//	 	 return (billerService.addbiller(biller));
//}
	 
//	 @GetMapping("/getAllBiller")
//	 public Object getAllBiller() {
//		 return billerService.getAllBiller();
//	 }
	 
	 @GetMapping("/getAllBiller")
	    public Object getChequeBookRequest(HttpServletRequest request) {
	        try {
	            String token = extractTokenFromHeader1(request);
	            String tokenCheck = jwtTokenUtil.validateToken(token);

	            if (tokenCheck.equalsIgnoreCase("valid")) {
	                return new ResponseEntity<>(billerService.getAllBiller(), HttpStatus.OK);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	        }
	    }
		@PutMapping("/updateBiller")
		public Object updateBiller(@RequestBody UpdatebillerDto updatebillerDto, HttpServletRequest request)
				throws InstantiationException, IllegalAccessException, JAXBException {
			try {
				
				String token = extractTokenFromHeader1(request);
				String tokenCheck = jwtTokenUtil.validateToken(token);
	
				if (tokenCheck.equalsIgnoreCase("valid")) {
					return new ResponseEntity<>(billerService.updateBiller(updatebillerDto), HttpStatus.OK);
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
				}
	
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
	
			}
		}
//	@PutMapping("/updateBiller")
//	 public Object updateBiller(@RequestBody UpdatebillerDto updatebillerDto) {
//		 return billerService.updateBiller(updatebillerDto);
//	 }
////	 @GetMapping("/listActiveBiller/{customer_id}")
////	 public Object listActiveBillerCustomerId(@PathVariable int customer_id) {
////	 return billerService.listActiveBillerCustomerId(customer_id);
////	 }
	 
//	 @GetMapping("/getAllBillerbyId/{biller_id}")
//	 	public Object getAllBillerByBillerId(@PathVariable int biller_id) {
//		 return billerService.getAllBillerByBillerId(biller_id);
//	 }
//	 @PostMapping("/verifyIfsc/{ifsc_code}")
//     public Object codeVerify(@PathVariable String ifsc_code) {
//         return billerService.codeVerify(ifsc_code);
//     }
//	 @GetMapping("/listActiveBiller/{customer_id}")
//	 public Object listActiveBillerCustomerId(@PathVariable int customer_id) {
//		 return billerService.listActiveBillerCustomerId(customer_id);
//	 }	
		@GetMapping("/getAllBillerbyId/{biller_id}")
		public Object getAllBillerByBillerId(@PathVariable int biller_id, HttpServletRequest request) {
		    try {
		        String token = extractTokenFromHeader1(request);
		        if (token == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
		        }
		        String tokenCheck = jwtTokenUtil.validateToken(token);

		        if (tokenCheck.equalsIgnoreCase("valid")) {
		            return new ResponseEntity<>(billerService.getAllBillerByBillerId(biller_id), HttpStatus.OK);
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
		    }
		}

		@PostMapping("/verifyIfsc/{ifsc_code}")
		public Object codeVerify(@PathVariable String ifsc_code, HttpServletRequest request) {
		    try {
		        String token = extractTokenFromHeader1(request);
		        if (token == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
		        }
		        String tokenCheck = jwtTokenUtil.validateToken(token);

		        if (tokenCheck.equalsIgnoreCase("valid")) {
		            return new ResponseEntity<>(billerService.codeVerify(ifsc_code), HttpStatus.OK);
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
		    }
		}

		@GetMapping("/listActiveBiller/{customer_id}")
		public Object listActiveBillerCustomerId(@PathVariable int customer_id, HttpServletRequest request) {
		
		    try {
		        String token = extractTokenFromHeader1(request);
		        if (token == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Token is missing");
		        }
		        String tokenCheck = jwtTokenUtil.validateToken(token);

		        if (tokenCheck.equalsIgnoreCase("valid")) {
		            return new ResponseEntity<>(billerService.listActiveBillerCustomerId(customer_id), HttpStatus.OK);
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheck);
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to invalid token");
		    }
		}


}