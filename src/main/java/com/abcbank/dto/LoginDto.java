package com.abcbank.dto;


public class LoginDto {
	
    public String getBillpayment_registration_status() {
		return billpayment_registration_status;
	}
	public void setBillpayment_registration_status(String billpayment_registration_status) {
		this.billpayment_registration_status = billpayment_registration_status;
	}
	private String billpayment_registration_status;

	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
