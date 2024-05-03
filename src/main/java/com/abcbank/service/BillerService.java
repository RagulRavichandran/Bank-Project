package com.abcbank.service;

import com.abcbank.dto.UpdatebillerDto;
import com.abcbank.model.Biller;

public interface BillerService {

	public Object addbiller(Biller biller);

 	public Object getAllBiller();

	public Object updateBiller(UpdatebillerDto updatebillerDto);

//	public Object listActiveBillerCustomerId(int customer_id);

	public Object getAllBillerByBillerId(int biller_id);

	public Object codeVerify(String ifsc_code);

	public Object listActiveBillerCustomerId(int customer_id);

}
