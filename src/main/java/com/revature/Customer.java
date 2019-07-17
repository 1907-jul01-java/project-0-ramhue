package com.revature;

public class Customer{
	String fname;
	String lname;
 	String userName;
	String password;
	int customerId;

	public Customer(String fname, String lname, String userName, String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.userName = userName;
		this.password = password;
		this.customerId = -1;

	}
	public Customer (String name){this.fname = name;}

	public String getFname() {return fname;}
	public void setFname(String fname) {this.fname = fname;}

	public String getLname() {return lname;}
	public void setLname(String lname) {this.lname = lname;}

	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public int getCustomerId() {return customerId;}

	public void setCustomerId(int customerId) {this.customerId = customerId;}

	@Override
	public String toString() {
		return "Customer [fname=" + fname + ", lname=" + lname + ", userName=" + userName + "]";
	}

	
	
	
}