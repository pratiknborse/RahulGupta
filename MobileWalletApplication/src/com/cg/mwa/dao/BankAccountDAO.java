package com.cg.mwa.dao;

import java.util.List;

import com.cg.mwa.dto.Customer;
import com.cg.mwa.dto.Transact;
import com.cg.mwa.exception.BankAccountException;

public interface BankAccountDAO {
		
		public Customer showBalance (String mobileno);
		
		public void updateBalance(String mobNo, Double amount);
		
		public Customer createAccount(Customer customer);
		
		public Customer findOne(String mobNo);
		
		public boolean validateAccount(String mobileNo) throws BankAccountException;

		public List<Transact> getAll();

		public void sendTr(String s);

}