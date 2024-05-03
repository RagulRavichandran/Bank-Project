package com.abcbank.dto;

import java.time.LocalDate;
public class ViewPaymentDto {
	 private LocalDate fromDate; 
	    private LocalDate toDate;
	    private int categoryCode;

	    public LocalDate getFromDate() {
	        return fromDate;
	    }

	    public void setFromDate(LocalDate fromDate) {
	        this.fromDate = fromDate;
	    }

	    public LocalDate getToDate() {
	        return toDate;
	    } 

	    public void setToDate(LocalDate toDate) {
	        this.toDate = toDate;
	    }

	    public int getCategoryCode() {
	        return categoryCode;
	    }

	    public void setCategoryCode(int categoryCode) {
	        this.categoryCode = categoryCode;
	    }

	    public ViewPaymentDto(LocalDate fromDate, LocalDate toDate, int categoryCode) {
	        super();
	        this.fromDate = fromDate;
	        this.toDate = toDate;
	        this.categoryCode = categoryCode;
	    }

	    public ViewPaymentDto() {
	        super();
	    }
	
	
	
	
	
}
