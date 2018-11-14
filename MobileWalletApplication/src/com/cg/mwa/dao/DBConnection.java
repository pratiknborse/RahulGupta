package com.cg.mwa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.mwa.exception.BankAccountException;

public class DBConnection {
	
	private static Connection con;
	public static Connection getConection() throws BankAccountException{
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "rishabh";
		String password = "rishabh";
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Class Loaded !!!! ");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to db .... ");
		}
		catch (SQLException e) {
			// TODO: handle exception
			throw new BankAccountException(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BankAccountException(e.getMessage());
		}
		return con;
	}
	
	
}