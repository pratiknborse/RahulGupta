package com.cg.mwa.ui;

import java.util.List;
import java.util.Scanner;

import com.cg.mwa.dto.Customer;
import com.cg.mwa.dto.Transact;
import com.cg.mwa.exception.BankAccountException;
import com.cg.mwa.service.BankAccountServiceImpl;
//Main Method
public class Main {
	public static void main(String args[]) throws BankAccountException{
		//Service Object
		BankAccountServiceImpl service = new BankAccountServiceImpl();
		
		Scanner sc = new Scanner(System.in);
		
		String name,mobileNo;
		int age;
		double amount;
		int ch = 0;
		do{
			System.out.println("Enter Your Choice:\n1.Add Customer\n2.Deposit amount\n3.Withdraw Amount\n4.Transfer Funds\n5.Check Balance\n6.Print Transactions\n7.Exit");
			
			ch = sc.nextInt();
			Customer customer;
			switch(ch){
				case 1 ://Add Customer
					
						do{//getting user details
							do{
							System.out.println("Enter customer name : ");
							name = sc.next();
							if(service.validateName(name))
								break;
							
							}while(true);
							do{System.out.println("Enter mobile no. : ");
							mobileNo = sc.next();
							if(service.validateMobileNo(mobileNo))
								break;
							}while(true);
							do{
							System.out.println("Enter age : ");
							age = sc.nextInt();
							if(service.validateAge(age))
								break;
							}while(true);
							do{
							System.out.println("Enter initial amount : ");
							amount = sc.nextDouble();

							if(service.validateAmount(amount))
								break;
							}while(true);
							if(service.validateAccount(mobileNo)){
									System.out.println("Mobile No Already Exists");
								}else{
								break;
							}
							
						}while(true);
						
						customer = new Customer();
						
						customer.setCustName(name);
						customer.setCustmobileNo(mobileNo);
						customer.setCustAge(age);
						customer.setCustBal(amount);
						
						service.createAccount(customer);						
						System.out.println("New Customer Added");
											break;
						
				case 2 ://Deposit
						do{
							System.out.println("Enter your mobile number : ");
							mobileNo = sc.next();
							
							System.out.println("Enter the amount you want to deposit");
							amount = sc.nextDouble();
							if(service.validateMobileNo(mobileNo)&& service.validateAmount(amount) && service.validateAccount(mobileNo)){
								if(service.validateAccount(mobileNo))
									break;
							}
							else{
								System.out.println("Mobile Number does not exist. Try Again!");
							}
						}while(true);
						
						service.depositAmount(mobileNo, amount);						
					
					break;
					
				case 3 ://Withdraw
						do{
							System.out.println("Enter your mobile number : ");
							mobileNo = sc.next();
							
							System.out.println("Enter the amount you want to withdraw : ");
							amount = sc.nextDouble();
							if(service.validateMobileNo(mobileNo) && service.validateAmount(amount) && service.validateAccount(mobileNo)){
								if(service.validateAccount(mobileNo))
									break;
							}else
							{
								System.out.println("Mobile Number does not exist!");
							}
							
							
						}while(true);
						
						service.withdrawAmount(mobileNo, amount);
						
					break;
				
				case 4 ://Fund Transfer
						String mobileNoReciever;
						do{
							System.out.println("Enter your mobile number : ");
							mobileNo = sc.next();
							
							System.out.println("Enter the amount you want to transfer : ");
							amount = sc.nextDouble();
							
							System.out.println("Enter receiver's mobile number : ");
							mobileNoReciever = sc.next();
							if(service.validateMobileNo(mobileNo) && service.validateMobileNo(mobileNoReciever) && service.validateAmount(amount) && service.validateAccount(mobileNoReciever) && service.validateAccount(mobileNo)){
								if(service.validateAccount(mobileNoReciever) && service.validateAccount(mobileNo))
									if(!mobileNo.equals(mobileNoReciever))
										
										break;
									else{
										System.out.println("Mobile Numbers cannot be the same");
									}
							}
							else
							{
								System.out.println("Mobile Number does not exist!");
							}
						}while(true);
					service.fundTransfer(mobileNo, mobileNoReciever, amount);
					
					break;
					
				case 5 ://Balance Enquiry
						do{
							System.out.println("Enter the mobile id to check balance");
							mobileNo = sc.next();
							if(service.validateMobileNo(mobileNo)&&service.validateAccount(mobileNo))
								break;
							else
							{
								System.out.println("Mobile Number does not exist!");
							}
						}while(true);
						
						System.out.println(service.showBalance(mobileNo));
						
					break;
					
				case 6://Printing Transactions6
						List<Transact> trans = service.getAll();
						for(Transact t:trans)
						{
							System.out.println(t.getTr());
							System.out.println("=========================================================================================================================");
							
						}
						break;
				case 7 ://Exit
						System.out.println("Thank You for Using the Mobile Wallet!!!");
					break;
				default : System.out.println("Invalid input!");
			}
			
			
		}while(ch != 7);
		sc.close();
	}
}
