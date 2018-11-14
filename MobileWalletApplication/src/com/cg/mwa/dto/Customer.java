package com.cg.mwa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@Column(name = "mob_no")
	private String CustmobileNo;
	
	@Column(name = "name")
	private String CustName;
	
	@Column(name = "age")
	private Integer CustAge;
	
	@Column(name = "balance")
	private Double CustBal;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String custmobileNo, String custName, Integer custAge,
			Double custBal) {
		super();
		CustmobileNo = custmobileNo;
		CustName = custName;
		CustAge = custAge;
		CustBal = custBal;
	}

	public String getCustmobileNo() {
		return CustmobileNo;
	}

	public void setCustmobileNo(String custmobileNo) {
		CustmobileNo = custmobileNo;
	}

	public String getCustName() {
		return CustName;
	}

	public void setCustName(String custName) {
		CustName = custName;
	}

	public Integer getCustAge() {
		return CustAge;
	}

	public void setCustAge(Integer custAge) {
		CustAge = custAge;
	}

	public Double getCustBal() {
		return CustBal;
	}

	public void setCustBal(Double custBal) {
		CustBal = custBal;
	}

	@Override
	public String toString() {
		return "Customer [CustmobileNo=" + CustmobileNo + ", CustName="
				+ CustName + ", CustAge=" + CustAge + ", CustBal=" + CustBal
				+ "]";
	}

	
}	
	