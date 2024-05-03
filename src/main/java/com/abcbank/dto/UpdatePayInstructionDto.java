package com.abcbank.dto;

import java.util.Date;

public class UpdatePayInstructionDto {
	private String pay_now_status;

	public String getPay_now_status() {
		return pay_now_status;
	}
	public void setPay_now_status(String pay_now_status) {
		this.pay_now_status = pay_now_status;
	}
	private int payment_request_id;
	private Double bill_amount;
	private Date payment_due_date;
	private String payment_status;
	private int biller_id;
	public int getBiller_id() {
		return biller_id;
	}
	public void setBiller_id(int biller_id) {
		this.biller_id = biller_id;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public int getPayment_request_id() {
		return payment_request_id;
	}
	public void setPayment_request_id(int payment_request_id) {
		this.payment_request_id = payment_request_id;
	}
	public Double getBill_amount() {
		return bill_amount;
	}
	public void setBill_amount(Double bill_amount) {
		this.bill_amount = bill_amount;
	}
	public Date getPayment_due_date() {
		return payment_due_date;
	}
	public void setPayment_due_date(Date payment_due_date) {
		this.payment_due_date = payment_due_date;
	}
}
