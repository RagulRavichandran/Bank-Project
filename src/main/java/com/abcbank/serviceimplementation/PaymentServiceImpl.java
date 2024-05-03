package com.abcbank.serviceimplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abcbank.config.RabbitMQConfiguration;
import com.abcbank.dto.UpdatePayInstructionDto;
import com.abcbank.dto.ViewPaymentDto;
import com.abcbank.model.Account;
import com.abcbank.model.Biller;
import com.abcbank.model.Payment;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.BillerRepo;
import com.abcbank.repository.BillpayregistrationRepo;
import com.abcbank.repository.CustomerRepo;
import com.abcbank.repository.PaymentRepo;
import com.abcbank.service.PaymentService;
import com.primesoftinc.message.customerRegister.BillerRequest;
import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.template.BillerRequestTemplate;

import jakarta.xml.bind.JAXBException;
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepo paymentRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BillerRepo billerRepo;
	@Autowired
	private BillpayregistrationRepo billpayregistrationRepo;
	@Autowired
    CoreService coreService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RestTemplate restTemplate;

	@Override
	public Object viewPaymentFilter(ViewPaymentDto paymentDto) {
		List<Payment> payment = null;
		if (paymentDto.getCategoryCode() != 0 && paymentDto.getFromDate() == null
				&& paymentDto.getToDate() == null  ) {
			payment = paymentRepo.getPaymentWithCategory(paymentDto.getCategoryCode());
		} else if (paymentDto.getCategoryCode() != 0 && paymentDto.getFromDate() != null
				&& paymentDto.getToDate() != null) {
			payment = paymentRepo.getPaymentWithCatAndDate(paymentDto.getCategoryCode(), paymentDto.getFromDate(),
					paymentDto.getToDate());
		} else if (paymentDto.getFromDate() != null && paymentDto.getToDate() != null && paymentDto.getCategoryCode() == 0) {
			payment = paymentRepo.getPaymentWithDate(paymentDto.getFromDate(), paymentDto.getToDate());
		} else {
			payment = paymentRepo.getPaymentDeatils();
		}
		List<Map<String, Object>> list = new ArrayList<>();
		if (payment != null) {
			for (Payment pay : payment) {
				Map<String, Object> hmap = new HashMap<String, Object>();
				hmap.put("date", pay.getPayment_date());
				hmap.put("accountNo", pay.getBiller().getBiller_account_no());
				hmap.put("biller", pay.getBiller().getBiller_id());
				hmap.put("amount", pay.getBill_amount());
				hmap.put("category", pay.getBiller().getCategory().getCategory_desc());
				hmap.put("paymentDate", pay.getPayment_date());
				hmap.put("PaymentStatus",pay.getPayment_status());
				hmap.put("PaymentRequestId",pay.getPayment_request_id());
				hmap.put("billerName",pay.getBiller().getBiller_name());


				list.add(hmap);
			}
		}
		return list;
	}
	@Override
	public Object savepay(Payment payment) throws InstantiationException, IllegalAccessException, JAXBException{
		Map<String, Object> map = new HashMap<>();
		Long account_number = payment.getAccount().getAccount_number();
		Payment paymentSave = null;
		boolean flag = false;
		List<Payment> payments = paymentRepo.findAll();
		if (account_number == null) {
			map.put("status", "error");
			map.put("Message", "please enter the acc number");
			return map;
		}if (payment.getBiller().getBiller_id() == 0) {
			map.put("Status", "error");
			map.put("Message", "please enter the billerId");
			return map;
		}
		int billerId = payment.getBiller().getBiller_id();
		Optional<Biller> optionalBiller = billerRepo.findById(billerId);
		if (!optionalBiller.isPresent()) {
			map.put("Status", "error");
			map.put("Message", "Biller does not exist.");
			return map;
		}
		Biller biller = optionalBiller.get();
		Optional<Account> optionalAccount = accountRepo.findByAccNo(account_number); // --fetching account number
		if (!optionalAccount.isPresent()) {
			map.put("Status", "error");
			map.put("Message", "Account not found.");
			return map;
		}
		Account account = optionalAccount.get();
		if (payment.getBill_amount() > account.getAccount_balance()) {
			map.put("Message", "Insufficient balance.");
			return map;
		}
//		else if (payment.getPay_now_status() == null) {
//			map.put("status", "error");
//			map.put("Message", "please enter payment status");
//			return map;
//		}
		if(payment.getPay_now_status() != null && payment.getPay_now_status().equalsIgnoreCase("yes")) {
			if(payment.getPayment_due_date() != null) {
				map.put("status", "error");
				map.put("Message", "Due date should be in null");
				return map;
			}
			payment.setPayment_date(new Date());			//have to set this to payment request date
			payment.setPayment_due_date(null);				//have to check
			payment.setPayment_status("pending");
//			payment.setBiller(biller.getPay_now_status());
//			payment.getBiller().setPay_now_status("yes");
			payment.setPay_now_status("yes");
			billerRepo.save(biller);
			paymentSave = paymentRepo.save(payment); //save payment
			map.put("status", "success");
			map.put("Message", "data saved successfully");
//			String reqxml = BillerRequestTemplate.RequestXMLTemplate;
//            BillerRequest billerRequest = (BillerRequest) coreService.unmarshal(reqxml, BillerRequest.class);
//            billerRequest.setAccountNo(paymentSave.getAccount().getAccount_number());
//            billerRequest.setCustomerId(account.getCustomer().getCustomer_id());
//            billerRequest.getBillerReqDetails().setBillerAccount(biller.getBiller_account_no());
////            billerRequest.getBillerReqDetails().setBillerA(biller.getBiller_account_no());
//            billerRequest.getBillerReqDetails().setBillerAmount(paymentSave.getBill_amount());
//            billerRequest.getBillerReqDetails().setBillerDate(paymentSave.getPayment_date());
//            billerRequest.getBillerReqDetails().setBillerDate(paymentSave.getPayment_date());
//        //    billerRequest.getBillerReqDetails().setPaymentDueDate(paymentSave.getPaymentDueDate());
//            billerRequest.getBillerReqDetails().setBillerId(biller.getBiller_id());
//            billerRequest.getBillerReqDetails().setPaymentReqId(paymentSave.getPayment_request_id());
//            String xml = coreService.marshal(BillerRequest.class, billerRequest);
//            System.out.println("///////" + xml);
//            rabbitTemplate.convertAndSend(RabbitMQConfiguration.Exchange, RabbitMQConfiguration.ROUTINGKEYBILLERSENDER,xml);
			return map;
		}
	
		if(payment.getPay_now_status() != null && payment.getPay_now_status().equalsIgnoreCase("No")) {
			if(payment.getPayment_due_date() == null) {
				map.put("status", "error");
				map.put("Message", "Enter the payment due date");
			}
			else if(payment.getPayment_due_date().before(new Date())) {
				map.put("status", "error");
				map.put("Message", "Due date cannot be a past date");
				return map;
			}
			else {
				Account account1 = optionalAccount.get();
				payment.setPayment_date(new Date());   			//check with it
				payment.setPayment_status("pending");
				payment.setPay_now_status("No");
				billerRepo.save(biller);
				payment.setPayment_due_date(payment.getPayment_due_date());
				paymentSave = paymentRepo.save(payment);
				map.put("status", "success");
				map.put("Message", "data saved successfully");
				String reqxml = BillerRequestTemplate.RequestXMLTemplate;
                BillerRequest billerRequest = (BillerRequest) coreService.unmarshal(reqxml, BillerRequest.class);
                billerRequest.setAccountNo(paymentSave.getAccount().getAccount_number());

                billerRequest.setCustomerId(account.getCustomer().getCustomer_id());
                billerRequest.getBillerReqDetails().setBillerAccount(biller.getBiller_account_no());
                billerRequest.getBillerReqDetails().setBillerAmount(paymentSave.getBill_amount());
                billerRequest.getBillerReqDetails().setBillerDate(paymentSave.getPayment_date());
        //        billerRequest.getBillerReqDetails().setPaymentDueDate(paymentSave.getPaymentDueDate());
                billerRequest.getBillerReqDetails().setBillerId(biller.getBiller_id());
                System.out.println("+=++++++"+paymentSave.getPayment_request_id());
                billerRequest.getBillerReqDetails().setPaymentReqId(paymentSave.getPayment_request_id());
                String xml = coreService.marshal(BillerRequest.class, billerRequest);
                rabbitTemplate.convertAndSend(RabbitMQConfiguration.Exchange,
                        RabbitMQConfiguration.ROUTINGKEYBILLERSENDER, xml);

                System.out.println("xml"+xml);
				return map;
			}
		}
		return map;
	}
//	@Override
//	public Object savepay(Payment payment) {
//		Map<String, Object> map = new HashMap<>();
//		Long account_number = payment.getAccount().getAccount_number();
//		Payment paymentSave = null;
//		boolean flag = false;
//		if (account_number == null) {
//			map.put("Status", "200");
//			map.put("Message", "please enter the acc number");
//		}
//		if (payment.getBiller().getBiller_id() == 0) {
//			map.put("Status", "200");
//			map.put("Message", "please enter the billerId");
//			return map;
//		}
//		int billerId = payment.getBiller().getBiller_id();
//		Optional<Biller> optionalBiller = billerRepo.findById(billerId);
//		if (!optionalBiller.isPresent()) {
//			map.put("Status", "200");
//			map.put("Message", "Biller does not exist.");
//			return map;
//		}
//		Biller biller = optionalBiller.get();
//		Optional<Account> optionalAccount = accountRepo.findByAccNo(account_number); // --fetching accno
//		if (!optionalAccount.isPresent()) {
//			map.put("Status", "200");
//			map.put("Message", "Account not found.");
//			return map;
//		} else if (payment.getBiller().getPay_now_status() == null) {
//			map.put("status", "error");
//			map.put("Message", "please enter payment status");
//			return map;
//		}
//		if (payment.getBiller().getPay_now_status() != null
//				&& payment.getBiller().getPay_now_status().equalsIgnoreCase("yes")) {
//			Account account = optionalAccount.get();
//			if (payment.getBill_amount() > account.getAccount_balance()) {
//				map.put("Status", "200");
//				map.put("Message", "Insufficient balance.");
//				return map;
//			}
//			double newBalance = account.getAccount_balance() - payment.getBill_amount();
//			if (newBalance < 0) {
//				map.put("Status", "200");
//				map.put("Message", "Insufficient account balance");
//			} else {
//				account.setAccount_balance(newBalance);
////				accountRepo.updateAccountBalance(account.getAccount_number(), newBalance);
//				accountRepo.saveAndFlush(account);
//			}
//			payment.setPayment_date(new Date());
//			payment.setPayment_due_date(null);
//			payment.setPayment_status("pending");
//			paymentSave = paymentRepo.save(payment); // --save payment
//		} else {
//			payment.setPayment_due_date(payment.getPayment_due_date());
//			payment.setPayment_status("pending");
//			paymentSave = paymentRepo.save(payment);
//		}
//		biller.setPay_now_status(payment.getBiller().getPay_now_status());
//		billerRepo.saveAndFlush(biller);
//		map.put("Status", "200");
//		map.put("Message", "Data Saved Succesfully");
//		return map;
//	}

	@Override
	public Object updateById(UpdatePayInstructionDto updatePayInstructionDto) {
//		 Map<String, Object> map = new HashMap<>();
//
//		    Optional<Payment> optionalPayment = paymentRepo.findById(updatePayInstructionDto.getPayment_request_id());
//
//		    if (optionalPayment.isPresent()) {
//		        Payment payment = optionalPayment.get();
//
//		        if ("pending".equals(updatePayInstructionDto.getPayment_status())) {
//		            map.put("Message", "Payment status is not pending");
//		            map.put("Status", "error");
//		            return map;
//		        }
//		        
//		        if ("pending".equals(updatePayInstructionDto.getPayment_status())) {
//		            map.put("Message", "Payment status is not pending");
//		            map.put("Status", "error");
//		            return map;
//		        }
//
//		        if (updatePayInstructionDto.getBill_amount() == null) {
//		            map.put("Message", "Bill amount cannot be null");
//		            map.put("Status", "error");
//		            return map;
//		        }
//
//		        if (updatePayInstructionDto.getPayment_due_date() == null) {
//		            map.put("Message", "Date cannot be null");
//		            map.put("Status", "error");
//		            return map;
//		        }
//		        
//		        if (updatePayInstructionDto.getBiller_id() == 0 || updatePayInstructionDto.getBiller_id() != payment.getBiller().getBiller_id()) {
//		            map.put("Message", "Invalid biller_id");
//		            map.put("Status", "error");
//		            return map;
//		        }
//		        
//		        payment.setPayment_date(new Date());
//		        payment.setBill_amount(updatePayInstructionDto.getBill_amount());
//		        payment.setPayment_due_date(updatePayInstructionDto.getPayment_due_date());
//		        payment.setPayment_status("pending");
//
//		        try {
//		            paymentRepo.saveAndFlush(payment);
//		            map.put("Message", "Updated Payment Details");
//		            map.put("Status", "200");
//		            return map;
//		        } catch (Exception e) {
//		            map.put("Status", "error");
//		            map.put("Message", "Error updating instruction details");
//		        }
//		    } else {
//		        map.put("Status", "error");
//		        map.put("Message", "Payment with ID " + updatePayInstructionDto.getPayment_request_id() + " not found");
//		    }
//
//		    return map;
		 Map<String, Object> map = new HashMap<>();

		    Optional<Payment> optionalPayment = paymentRepo.findById(updatePayInstructionDto.getPayment_request_id());
		   
		    if (optionalPayment.isPresent()) {
		        Payment payment = optionalPayment.get();

		        // Check if payment status is "pending"
		          if ("pending".equals(updatePayInstructionDto.getPayment_status())) {
		            map.put("Message", "Payment status is not pending");
		            map.put("Status", "error");
		            return map;
		        }
		        
//		          else if(updatePayInstructionDto.getPay_now_status().equalsIgnoreCase("yes")){
//		        	  map.put("Message", "Your Request Can't Update");
//			            map.put("Status", "error");
//			            return map;
//		          }
//		          else if(payment.getPay_now_status().equalsIgnoreCase("Yes")) {
//		        	  map.put("message", "Your Request Can't Update");
//		        	  map.put("Status", "error");
//		        	  return map;
//		          }
		          else if (updatePayInstructionDto.getBill_amount() == null) {
		            map.put("Message", "Bill amount cannot be null");
		            map.put("Status", "error");
		            return map;
		        }

		          else if (updatePayInstructionDto.getPayment_due_date() == null) {
		            map.put("Message", "Date cannot be null");
		            map.put("Status", "error");
		            return map;
		        }
		        
		        // Check if the biller_id from the DTO is provided and matches the biller_id associated with the payment
		          else if (updatePayInstructionDto.getBiller_id() == 0 || updatePayInstructionDto.getBiller_id() != payment.getBiller().getBiller_id()) {
		            map.put("Message", "Invalid biller_id");
		            map.put("Status", "error");
		            return map;
		        }
		        
		        // Update the payment entity with the values from the DTO
		        payment.setPayment_date(new Date());
		        payment.setBill_amount(updatePayInstructionDto.getBill_amount());
		        payment.setPayment_due_date(updatePayInstructionDto.getPayment_due_date());
		        

//		        try {
//		            
//		        } catch (Exception e) {
//		            map.put("Status", "error");
//		            map.put("Message", "Error updating instruction details");
//		        }
		        
		     // Save the updated payment entity
	            paymentRepo.saveAndFlush(payment);
	            map.put("Message", "Updated Payment Details");
	            map.put("Status", "200");
	            return map;
		    } else {
		        map.put("Status", "error");
		        map.put("Message", "Payment with ID " + updatePayInstructionDto.getPayment_request_id() + " not found");
		    }

		    return map;
}


		@Override
		public Object deletepayment(int paymentRequestId) {
			Map<String, Object>map=new HashMap<>();
			Payment payment=paymentRepo.findById(paymentRequestId).orElse(null);

			if(payment == null) {
				map.put("Status", "error");
				map.put("Message", "Id does no exists");
				return map;
			}
			else if(payment.getPay_now_status().equalsIgnoreCase("yes")) {
				map.put("Status", "error");
				map.put("Message", "Payment is already done.");
				return map;
			}
//			if(payment.getPayment_status().equalsIgnoreCase("Approved")||
//					payment.getPayment_status().equalsIgnoreCase("Rejected")) {
//				map.put("Status", "200");
//				map.put("Message", "we cannot delete approved / rejected payments");
//				return map;
//			}
			paymentRepo.delete(payment);
			map.put("Status", "200");
			map.put("Message", "Deleted Successfully");
			return map;
		}
		@Override
		public Object GetpaymentById(int paymentRequestId) {
			Map<String, Object>map=new HashMap<>();
			Payment payment=paymentRepo.findById(paymentRequestId).orElse(null);
			if(payment == null) {
				map.put("Status", "200");
				map.put("Message", "Id does no exists");
				return map;
			}
			else{
				map.put("paymentRequestId", payment.getPayment_request_id());
				map.put("billerId", payment.getBiller().getBiller_id());
//				map.put("employeeId", payment.getEmployee().getEmployee_id());
				map.put("accountNumber", payment.getAccount().getAccount_number());
				map.put("billAmount", payment.getBill_amount());
				map.put("paymentDate", payment.getPayment_date());
				map.put("paymentDueDate", payment.getPayment_due_date());
				map.put("payNowStatus", payment.getPay_now_status());
				map.put("paymentStatus", payment.getPayment_status());
				map.put("remarks", payment.getRemarks());
				map.put("billerName", payment.getBiller().getBiller_name());
				map.put("accountBalance", payment.getAccount().getAccount_balance());

			}
			
			return map;
		}
		}

