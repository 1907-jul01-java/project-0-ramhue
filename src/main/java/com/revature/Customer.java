package com.revature;

public class Customer{
	String fname;
	String lname;
 	String userName;
	String email;

	public Customer(String fname, String lname, String accountNumber, String userName, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.userName = userName;
		this.email = email;
	}
	public Customer (String name){
		this.fname = name;
	}
	
	
}