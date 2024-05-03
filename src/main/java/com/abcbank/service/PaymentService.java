package com.abcbank.service;

import com.abcbank.dto.UpdatePayInstructionDto;
import com.abcbank.dto.ViewPaymentDto;
import com.abcbank.model.Payment;

import jakarta.xml.bind.JAXBException;

public interface PaymentService {

	public Object viewPaymentFilter(ViewPaymentDto paymentDto);

	public Object savepay(Payment payment)throws InstantiationException, IllegalAccessException, JAXBException;

	public Object updateById(UpdatePayInstructionDto updatePayInstructionDto);

	public Object deletepayment(int paymentRequestId);

	public Object GetpaymentById(int paymentRequestId);

}
