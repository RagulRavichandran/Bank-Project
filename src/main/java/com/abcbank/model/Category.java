package com.abcbank.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "Category")
public class Category {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "category_code")
	  private int category_code;
	  
	  @Column(name = "category_desc", length = 20, nullable = false)
	  private String category_desc;
	  
	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	  private List<Biller> billers = new ArrayList<Biller>();

		public List<Biller> getBillers() {
		return billers;
	}

	public void setBillers(List<Biller> billers) {
		this.billers = billers;
	}

		public int getCategory_code() {
			return category_code;
		}
	
		public void setCategory_code(int category_code) {
			this.category_code = category_code;
		}
	
		public String getCategory_desc() {
			return category_desc;
		}
	
		public void setCategory_desc(String category_desc) {
			this.category_desc = category_desc;
		}
		
	
}
