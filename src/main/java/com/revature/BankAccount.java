package com.revature;

public class BankAccount {
	String accountNumber;
	double balance;
	

	public BankAccount(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
		this.balance = 0;
	}
	
	String checkBalance(){
		return Double.toString(balance);
	}
	void Deposit(int amount){
		if(amount > 0){
			balance += amount;
		}
	}
	void withdraw(int amount){
		if( amount > 0 && balance > amount){
			balance -= amount;
		}
	}
	//AccountNumber getters and setters
	public String getAccountNumber() {return accountNumber;}

	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
	
	//Account Balance getters and setters
	public double getBalance() {return balance;}

	public void setBalance(double balance) {this.balance = balance;}

	//toString method
	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}
}
