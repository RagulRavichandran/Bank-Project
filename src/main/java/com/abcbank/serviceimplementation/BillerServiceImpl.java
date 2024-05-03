package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abcbank.dto.UpdatebillerDto;
import com.abcbank.model.Biller;
import com.abcbank.model.Category;
import com.abcbank.model.Customer;
import com.abcbank.model.Payment;
import com.abcbank.repository.BillerRepo;
import com.abcbank.repository.CategoryRepo;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.repository.PaymentRepo;
import com.abcbank.service.BillerService;

@Service
public class BillerServiceImpl implements BillerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
@Autowired
private BillerRepo billerRepo;

@Autowired
private CategoryRepo categoryRepo;
@Autowired
private PaymentRepo paymentRepo;

@Autowired
private CategoryRepo  repo;

@Autowired
RestTemplate restTemplate;
@Override
public Object addbiller(Biller biller) {
	Map<String ,Object> map = new HashMap<String ,Object>();

	if(biller.getBiller_name().isEmpty()) {
		map.put("status","error");
		map.put("msg","Please Enter Your Name");
	}
	else if(biller.getBiller_ifsc_code().isEmpty()){
		map.put("status","error");
		map.put("msg", "Please Enter Your ifsc code");
	}
//	else if(biller.getBranch_name().isEmpty()){
//		map.put("status","error");
//		map.put("msg", "Please Enter Your Branch Name");
//	}
	else if(biller.getBiller_account_no()==0){
		map.put("status", "error");
		map.put("msg", "please Enter Your Account Number");
	}
	else if(biller.getBiller_acc_type().isEmpty()){
		map.put("status", "error");
		map.put("msg", "Please Enter Your Account Type");
	}
	else if(biller.getCustomer().getCustomer_id() == 0) {
        map.put("status", "error");
        map.put("map", "Please enter the customer Id");
    }
//	else if(biller.getPay_now_status().isEmpty()) {
//		map.put("status","error");
//		map.put("map", "Please Enter Your pay now status");
//	}
	else if(biller.getCategory().getCategory_code() == 0.0) {
		map.put("status","error");
		map.put("map", "Please Select Category");
	}
	else {
//		biller_menuRepo.save(biller_menu);
//       
//        map.put("msg", "Data saved successfully");
		biller.setStatus("Active");
	     billerRepo.save(biller);
            

            // Include the status in the response
            map.put("status", "success");
            map.put("msg", "Data saved successfully");
            map.put("biller_status", biller.getStatus());
	}
	return map;

}

@Override
public Object getAllBiller() {
	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	List<Biller> biller = billerRepo.findAll();
	for(Biller e: biller) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("billerId", e.getBiller_id());
		map.put("billerName", e.getBiller_name());
		map.put("billerAccountNo", e.getBiller_account_no());
		map.put("billerIfsCode", e.getBiller_ifsc_code());
		map.put("billeAccType", e.getBiller_acc_type());
//		map.put("branchName", e.getBranch_name());
		map.put("Category", e.getCategory().getCategory_desc());			
//		map.put("payNowStatus", e.getPay_now_status());
		map.put("Status", e.getStatus());
		list.add(map);
		
	}
	return list;
}

@Override
public Object updateBiller(UpdatebillerDto updatebillerDto) {
//	Map<String, Object> map = new HashMap<>();
	Biller biller = billerRepo.findById(updatebillerDto.getBiller_id()).orElse(null);

	Map<String, Object> map = new HashMap<>();
	if (biller == null) {
	    map.put("status", "error");
	    map.put("msg", "Biller not found with ID: " + updatebillerDto.getBiller_id());
	    return map;
	}
	else
	{
	// Check if ID is not provided
	if (updatebillerDto.getBiller_id() == 0) {
	    map.put("status", "error");
	    map.put("msg", "ID is required for updating. Please provide a valid ID.");
	    return map;
	}

	// Validate other fields
	if (updatebillerDto.getBiller_name().isEmpty()) {
	    map.put("status", "error");
	    map.put("msg", "Please Enter Your Name");
	} 
	else if (updatebillerDto.getBiller_ifsc_code().isEmpty()) {
        map.put("status", "error");
        map.put("msg", "Please Enter Your IFSC Code");
    } 
	else if (updatebillerDto.getBiller_account_no() == 0) {
        map.put("status", "error");
        map.put("msg", "Please Enter Your Account Number");
	 }  else if (updatebillerDto.getBiller_ac_type() == null || updatebillerDto.getBiller_ac_type().isEmpty()) {
       	    map.put("status", "error");
	    map.put("msg", "Please Enter Your Account Type");
	} 

	else if (updatebillerDto.getStatus().isEmpty()) {
	    map.put("status", "error");
	    map.put("msg", "Please Enter Your Status");
	} else if (updatebillerDto.getCategory_code() == 0) {
	    map.put("status", "error");
	    map.put("msg", "Please Enter Your Category Code");
	    System.out.println("Category code "+updatebillerDto.getCategory_code());
	}
	else {
	   // Update Biller entity with new data
	    	
	        biller.setBiller_name(updatebillerDto.getBiller_name());
            biller.setBiller_ifsc_code(updatebillerDto.getBiller_ifsc_code());
	        biller.setBiller_account_no(updatebillerDto.getBiller_account_no());
	        biller.setBiller_acc_type(updatebillerDto.getBiller_ac_type());
//	        updatebillerDto.getPay_now_status();
	        biller.setStatus(updatebillerDto.getStatus());
	        Category category=repo.findById(updatebillerDto.getCategory_code()).orElse(null);
	        biller.setCategory(category);

	        // Save the updated Biller
	        billerRepo.saveAndFlush(biller);

	        map.put("status", "success");
	   
	}
	}

	return map;

	
	
	
	
	
	
	
	
	
	
	
	
////		Biller biller = billerRepo.findById(UpdatebillerDto.biller_id))
//	Biller biller = billerRepo.findById(updatebillerDto.getBiller_id().orElse(null);
//        biller.
//				// Check if ID is not provided
//    if (updatebillerDto.getBiller_id()==0) {
//        map.put("status", "error");
//        map.put("msg", "ID is required for updating. Please provide a valid ID.");
//      return map;
//    }
////biller_menu.getBiller_name() == null || 
//    if (updatebillerDto.getBiller_name().isEmpty()) {
//        map.put("status", "error");
//        map.put("msg", "Please Enter Your Name");
//    } else if (updatebillerDto.getBiller_ifsc_code().isEmpty()) {
//        map.put("status", "error");
//        map.put("msg", "Please Enter Your ifsc code");
//    } 
////    else if (biller_menu.getBranch_name().isEmpty()) {
////        map.put("status", "error");
////        map.put("msg", "Please Enter Your Branch Name");
////    }
//    else if (updatebillerDto.getBiller_account_no() == 0) {
//        map.put("status", "error");
//        map.put("msg", "Please Enter Your Account Number");
//    } else if (updatebillerDto.getBiller_ac_type().isEmpty()) {
//        map.put("status", "error");
//        map.put("msg", "Please Enter Your Account Type");
//    } else if (updatebillerDto.getPay_now_status().isEmpty()) {
//        map.put("status", "error");
//        map.put("map", "Please Enter Your pay now status");
//    }
//        else if(updatebillerDto.getStatus().isEmpty()) {
//        	map.put("status", "error");
//        	map.put("map", "Please Enter Your Status");
//        }
//        else if(updatebillerDto.getCategory_code()==0) {
//        	map.put("status", "error");
//        	map.put("map", "Please Enter Your Status");
//        }
//     else {
//        try {
            // Save and flush only if the ID is provided
//        	 biller_menu.setStatus("Active");
//	            billerRepo.saveAndFlush(updatebillerDto);
//
//	            map.put("status", "success");
//	            map.put("msg", "Data Updated successfully");
//	            map.put("biller_status", biller.getStatus());

            // Set status and message
           
//        } catch (Exception e) {
            // Handle exceptions
//            map.put("status", "error");
//            map.put("msg", "Error updating data: " + e.getMessage());
//        }
//    }
//
//    return map;

}

//	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//
//	Map<String ,Object> map = new HashMap<String ,Object>();
//	Biller biller=billerRepo.findById(customer_id).orElse(null);
//	if(biller!=null){
//		map.put("biller_id",biller .getBiller_id());
//		map.put("biller_name", biller.getBiller_name());
//		map.put("biller_account_no", biller.getBiller_account_no());
//		map.put("biller_ifsc_code", biller.getBiller_ifsc_code());
//		map.put("biller_acc_type", biller.getBiller_acc_type());
////		map.put("branch_name", e.getBranch_name());
//		map.put("Category", biller.getCategory().getCategory_desc());			
//		map.put("pay_now_status", biller.getPay_now_status());
//		map.put("Status", biller.getStatus());
//		list.add(map);
//	}
//
//	return list;
//}
//	@Override
//	public Object getAllBillerByCustomerId(int customer_id) {
//	  
//
//	    // Assuming billerRepo has a method to find all billers by customer_id
//	    List<Biller> billers = billerRepo.findAllByCustomerId(customer_id);
//	    List<Map<String, Object>> list = new ArrayList<>();
//	    
//	    Customer customer = customerRepo.findById(customer_id).orElse(null);
//   
//	    if(customer == null) {
//	        Map<String, Object> map = new HashMap<>();
//	        map.put("Status", "error");
//	        map.put("Message", "customer id doesn't exist");
//	        list.add(map);
//	        return list;
//	    }
//	    
//	    if(billers == null || billers.isEmpty()) {
//	        Map<String, Object> map = new HashMap<>();
//	        map.put("Status", "error");
//	        map.put("Message", "there is no billers for this customer");
//	        list.add(map);
//	        return list;
//	    }
//	    
//	    for (Biller biller : billers) {
//	        Map<String, Object> map = new HashMap<>();
//	        map.put("billerId", biller.getBiller_id());
//	        map.put("billerName", biller.getBiller_name());
//	        map.put("billerAccount_no", biller.getBiller_account_no());
//	        map.put("billerIfsCode", biller.getBiller_ifsc_code());
//	        map.put("billerAccType", biller.getBiller_acc_type());
//	        // Assuming Category is a field in Biller entity and is fetched eagerly
//	        map.put("Category", biller.getCategory().getCategory_desc());
//	        
//	        //error
////	        map.put("payNowStatus", biiler.getPay_now_status());
//	        map.put("Status", biller.getStatus());
//	        map.put("Category_code", biller.getCategory().getCategory_code());
//	        list.add(map);
//	    }
//
//      
//	    return list;
//
//	}
@Override
public Object getAllBillerByBillerId(int biller_id) {
    // Assuming billerRepo has a method to find all billers by customer_id
//    List<Biller> billers = billerRepo.findAllByBillerId(biller_id);
    List<Map<String, Object>> list = new ArrayList<>();
    Biller biller = billerRepo.findById(biller_id).orElse(null);
//    if(customer == null) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("Status", "error");
//        map.put("Message", "customer id doesn't exist");
//        list.add(map);
//        return list;
//    }
//    
//    if(billers == null || billers.isEmpty()) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("Status", "error");
//        map.put("Message", "there is no billers for this customer");
//        list.add(map);
//        return list;
//    }   
        Map<String, Object> map = new HashMap<>();
        map.put("billerId", biller.getBiller_id());
        map.put("billerName", biller.getBiller_name());
        map.put("billerAccount_no", biller.getBiller_account_no());
        map.put("billerIfscCode", biller.getBiller_ifsc_code());
        map.put("billerAccType", biller.getBiller_acc_type());
        // Assuming Category is a field in Biller entity and is fetched eagerly
        map.put("Category", biller.getCategory().getCategory_desc());
//        map.put("payNowStatus", biller.getPay_now_status());
        map.put("Status", biller.getStatus());
        list.add(map);
    return list;
}
	@Override
	public Object codeVerify(String id) {
		// Map<String,Object>  hmap = new HashMap<>();
	     // hmap= restTemplate.getForObject(" https://ifsc.razorpay.com/"+id, null, hmap);
	     // return ifscResponse;
	     HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     HttpEntity<String> entity = new HttpEntity<String>(headers);
	     return restTemplate.exchange("https://ifsc.razorpay.com/" + id, HttpMethod.GET, entity, String.class).getBody();
	}
	@Override
	public Object listActiveBillerCustomerId(int customer_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			List<Biller> billers=billerRepo.findBycustomer(customer_id);
			for(Biller b: billers) {
					Map<String ,Object> map = new HashMap<String ,Object>();
					map.put("biller_id",b.getBiller_id());
					map.put("biller_name", b.getBiller_name());
					map.put("billerAccountNo", b.getBiller_account_no());
					map.put("Category", b.getCategory().getCategory_desc());
					map.put("billerIfsCode", b.getBiller_ifsc_code());
					map.put("Status", b.getStatus());
					list.add(map);
			}
			return list;
	}
}
