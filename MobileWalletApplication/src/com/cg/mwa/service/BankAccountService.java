package com.cg.mwa.service;

import com.cg.mwa.dto.Customer;
import com.cg.mwa.exception.BankAccountException;

public interface BankAccountService {

	public Customer showBalance (String mobileno);
	public void fundTransfer (String sourceMobileNo,String targetMobileNo, Double amount);
	public Customer depositAmount (String mobileNo,Double amount );
	public Customer withdrawAmount(String mobileNo, Double amount);
	public Customer createAccount(Customer customer);
	
	
	public boolean validateAccount(String mobileNo) throws BankAccountException;
	
	public boolean validateName(String name) throws BankAccountException;
	
	public boolean validateAge(float age) throws BankAccountException;
	
	public boolean validateMobileNo(String mobileNo) throws BankAccountException;
	
	public boolean validateAmount(double amount) throws BankAccountException;
			
}
