package com.cg.mwa.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.mwa.dao.BankAccountDAO;
import com.cg.mwa.dao.BankAccountDAOImpl;
import com.cg.mwa.dto.Customer;
import com.cg.mwa.dto.Transact;
import com.cg.mwa.exception.BankAccountException;
import com.cg.mwa.exception.InsufficientBalanceException;
import com.cg.mwa.exception.InvalidInputException;


public class BankAccountServiceImpl implements BankAccountService{

	BankAccountDAO dao  = new BankAccountDAOImpl();
	
	
	

	@Override
	public boolean validateName(String name) throws BankAccountException {
		// TODO Auto-generated method stub
		if(name == null)
			throw new BankAccountException("Null value found");
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{1,10}");
		Matcher m = p.matcher(name); 
		if(!m.matches())
			System.out.println("Name invalid!(Should Start with Capital letter)");
		return m.matches();
		
	}

	@Override
	public boolean validateAge(float age)  throws BankAccountException {
		try{
			// TODO Auto-generated method stub
			if(age == 0)
				throw new BankAccountException("Age cannot be  null");
			else if(age >100)
				throw new BankAccountException("Age cannot be  greater than 100");
			else if(age < 0)
				throw new BankAccountException("Age cannot be a negative number");
			else if(age >17)
				return true;
			
		
	} catch (BankAccountException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
		return false;
	}

	@Override
	public boolean validateMobileNo(String mobileNo) throws BankAccountException{
		try{
			// TODO Auto-generated method stub
			if(mobileNo == null)
				throw new BankAccountException("Null value found");
			Pattern p = Pattern.compile("[6789][0-9]{9}");
			Matcher m = p.matcher(mobileNo);
			if(!m.matches())
				System.out.println("Mobile Number Invalid!(Should start either 6,7,8 or 9 and should be a 10-digit number.)");
			return m.matches();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public boolean validateAmount(double amount) throws BankAccountException {
		// TODO Auto-generated method stub
		if(amount == 0)
			throw new BankAccountException("Null value found");
		String am = String.valueOf(amount);
		if(!am.matches("\\d{1,9}\\.\\d{0,4}"))
			System.out.println("Invalid Amount!(Should be greater than 0");
		return (am.matches("\\d{1,9}\\.\\d{0,4}"));
	}

	@Override
	public boolean validateAccount(String mobileNo) throws BankAccountException {
		// TODO Auto-generated method stub
		return dao.validateAccount(mobileNo);
	}

	@Override
	public Customer showBalance(String mobileno) {
		Customer customer = dao.showBalance(mobileno);
		System.out.println("Name : " + customer.getCustName());
		System.out.println("Mobile No : " + customer.getCustmobileNo());
		System.out.println("Balance : " + customer.getCustBal());
		return customer;
	}

	@Override
	public void fundTransfer(String sourceMobileNo, String targetMobileNo,
			Double amount) {
		Customer source = dao.findOne(sourceMobileNo);
		Customer target = dao.findOne(targetMobileNo);
		if (amount > 0) {
				if((source.getCustBal() - amount) >= 0)
				{	
						double bal1 = target.getCustBal();
						bal1 += amount;
						target.setCustBal(bal1);
						dao.updateBalance(targetMobileNo, bal1);
						double bal2 = source.getCustBal();
						bal2 -= amount;
						source.setCustBal(bal2);
						dao.updateBalance(sourceMobileNo, bal2);
						System.out.println("Balance after deduction is : "+bal2);
						System.out.println("Updated Reciever Balance is : "+bal1);
						
						String s = "Deposited "+amount+" to "+targetMobileNo+" from "+sourceMobileNo;
						dao.sendTr(s);
				}
				else {
						try {
							throw new BankAccountException("Balance should be higher than amount to be transferred !!!! ");
						} catch (BankAccountException e) {
							System.out.println(e);
							
						}
				}
		}
		else 
				throw new InvalidInputException("Amount should be positive to send");
		
	}

	@Override
	public Customer depositAmount(String mobileNo, Double amount){
		Customer customer = dao.findOne(mobileNo);
		if (amount > 0) {
				double bal = customer.getCustBal();
				bal += amount;
				customer.setCustBal(bal);
				System.out.println(amount + " Deposited \n Balance : " + customer.getCustBal());
				dao.updateBalance(mobileNo, bal);
				
				String s = "Deposited "+amount+" to "+mobileNo;
				dao.sendTr(s);
				
				
				return customer;
		}
		else 
				throw new InvalidInputException("Amount should be positive");
	}

	@Override
	public Customer withdrawAmount(String mobileNo, Double amount) {
		Customer customer = dao.findOne(mobileNo);
		try {
			if(amount > 0) {
		}

			if((customer.getCustBal() - amount) >= 0)
			{		
					double bal = customer.getCustBal();
					bal -= amount;
					customer.setCustBal(bal);
					System.out.println(amount + " Withdrawn \n Balance : " + customer.getCustBal());
					dao.updateBalance(mobileNo, bal);
					String s = "Withdrawn "+amount+" from "+mobileNo;
					dao.sendTr(s);
					return customer;
			}
			else				
				
				throw new InsufficientBalanceException("Balance is not sufficient for this withdrawal amount");
			}catch(InsufficientBalanceException e){
				System.out.println(e);
			}
		
		
		return customer;


		
	}

	@Override
	public Customer createAccount(Customer customer) {
		dao.createAccount(customer);
		return customer;
	}

	public List<Transact> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}
}
