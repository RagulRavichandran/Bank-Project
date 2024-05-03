package com.abcbank.dto;

import java.math.BigInteger;

public class UpdatebillerDto {
	private int biller_id;
private String biller_name;
private String biller_ifsc_code;
private String Branch_name;
private long biller_account_no;
private String biller_ac_type;
private String pay_now_status;
private int category_code;
private String status;

public String getBiller_name() {
	return biller_name;
}
public void setBiller_name(String biller_name) {
	this.biller_name = biller_name;
}
public String getBiller_ifsc_code() {
	return biller_ifsc_code;
}
public void setBiller_ifsc_code(String biller_ifsc_code) {
	this.biller_ifsc_code = biller_ifsc_code;
}
public String getBranch_name() {
	return Branch_name;
}
public void setBranch_name(String branch_name) {
	Branch_name = branch_name;
}



public long getBiller_account_no() {
	return biller_account_no;
}
public void setBiller_account_no(long biller_account_no) {
	this.biller_account_no = biller_account_no;
}




public String getBiller_ac_type() {
	return biller_ac_type;
}
public void setBiller_ac_type(String biller_ac_type) {
	this.biller_ac_type = biller_ac_type;
}
public String getPay_now_status() {
	return pay_now_status;
}
public void setPay_now_status(String pay_now_status) {
	this.pay_now_status = pay_now_status;
}
public int getCategory_code() {
	return category_code;
}
public void setCategory_code(int category_code) {
	this.category_code = category_code;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getBiller_id() {
	return biller_id;
}
public void setBiller_id(int biller_id) {
	this.biller_id = biller_id;
}

}
