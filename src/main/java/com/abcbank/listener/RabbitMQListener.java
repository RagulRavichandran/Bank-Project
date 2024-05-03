package com.abcbank.listener;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbank.config.RabbitMQConfiguration;
import com.abcbank.model.Account;
import com.abcbank.model.Payment;
import com.abcbank.repository.AccountRepo;
import com.abcbank.repository.PaymentRepo;
import com.primesoftinc.message.customerRegister.BillerResponse;
import com.primesoftinc.message.service.CoreService;

import jakarta.xml.bind.JAXBException;

@Component
public class RabbitMQListener {

	@Autowired
	RabbitTemplate template;

	@Autowired
	private CoreService coreService;

	@Autowired
	private PaymentRepo paymentRep;

	@Autowired
	private AccountRepo accrepo;

	@RabbitListener(queues = RabbitMQConfiguration.BillerReceiverQueue)
	public void onMessage(String msg) throws JAXBException {
		System.out.println("????" + msg);
		if (msg != null) {
			System.out.println("Receied Msg" + msg);
			BillerResponse billerResponse = (BillerResponse) coreService.unmarshal(msg, BillerResponse.class);
			String status = billerResponse.getStatus();
			System.out.println("///////////////" + status);
			if (status.equalsIgnoreCase("Approved")) {
				Payment payment = paymentRep.findById(billerResponse.getBillerResDetails().getPaymentReqId())
						.orElse(null);
				System.out.println("#############" + payment);
				payment.setPayment_status(billerResponse.getStatus());
				// System.out.println("@@@@@@@@"+billerResponse.getStatus());
				payment.setRemarks(billerResponse.getRemarks());
				Account account = accrepo.findById(billerResponse.getAccountNo()).orElse(null);
				double newBalance = account.getAccount_balance() - payment.getBill_amount();
				account.setAccount_balance(newBalance);
				accrepo.saveAndFlush(account);
				paymentRep.saveAndFlush(payment);

			} else if (status.equalsIgnoreCase("Reject")) {

				Payment payment = paymentRep.findById(billerResponse.getBillerResDetails().getPaymentReqId())
						.orElse(null);
				Account account = accrepo.findById(billerResponse.getAccountNo()).orElse(null);
				double rejectedAmmount = billerResponse.getBillerResDetails().getBillerAmount();
				double currentBalence = account.getAccount_balance();
				double newalence = currentBalence + rejectedAmmount;
				account.setAccount_balance(newalence);
				accrepo.saveAndFlush(account);
//				payment.setPayment_status(billerResponse.getStatus());
//				payment.setRemarks(billerResponse.getRemarks());
//				paymentRep.saveAndFlush(payment);
			}
//			ServiceResponse serResponse = (ServiceResponse) coreService.unmarshal(msg, ServiceResponse.class);

		}
	}

}
