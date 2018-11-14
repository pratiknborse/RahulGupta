package com.cg.mwa.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.mwa.dto.Customer;
import com.cg.mwa.dto.Transact;
import com.cg.mwa.exception.BankAccountException;

public class BankAccountDAOImpl implements BankAccountDAO{
	
		//Map<String, Customer> customerMap;
		EntityManager manager;
	
		public BankAccountDAOImpl() {
				// TODO Auto-generated constructor stub
				//customerMap = DataStore.createCollection();
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
				manager = emf.createEntityManager();
			
		}
	
		@Override
		public Customer showBalance(String mobileno) {
				// TODO Auto-generated method stub
				manager.getTransaction().begin();
				 Customer customer = manager.find(Customer.class, mobileno);
				manager.getTransaction().commit();
				return customer;
		}
	
		@Override
		public void updateBalance(String mobNo, Double amount) {
				// TODO Auto-generated method stub
				manager.getTransaction().begin();
				Query sourceQuery = manager.createQuery("UPDATE Customer SET amount = :sourcebal where mobile = :sourcemobile");
				sourceQuery.setParameter("sourcebal", amount);
				sourceQuery.setParameter("sourcemobile", mobNo);
				manager.getTransaction().commit();
		
		}
	
		@Override
		public Customer createAccount(Customer customer) {
				// TODO Auto-generated method stub
				manager.getTransaction().begin();
				manager.persist(customer);
				manager.getTransaction().commit();
				return customer;
		}
	
		@Override
		public Customer findOne(String mobNo) {
				// TODO Auto-generated method stub
			manager.getTransaction().begin();
			 Customer customer = manager.find(Customer.class, mobNo);
			manager.getTransaction().commit();
				return customer;
		}
		@Override
		public boolean validateAccount(String mobileNo) throws BankAccountException {
			// TODO Auto-generated method stub
			manager.getTransaction().begin();
			 Customer customer = manager.find(Customer.class, mobileNo);
			 manager.getTransaction().commit();
			if(customer == null)
				return false;
			
			return true;
		}

		@Override
		public List<Transact> getAll() {
			Query qStr = manager.createQuery("FROM Transact");
			List<Transact> trans = qStr.getResultList();
			return trans;
		}

		@Override
		public void sendTr(String s) {
			int a = 1;
			Transact t = new Transact(a, s);
			manager.getTransaction().begin();
			manager.persist(t);
			manager.getTransaction().commit();
			
			
		}

}