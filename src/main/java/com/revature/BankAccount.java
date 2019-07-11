package com.revature;

public class BankAccount {
	String accountNumber;
	int balance;
	

	public BankAccount(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
		this.balance = 0;
	}
	String checkBalance(){
		return Integer.toString(balance);
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

}
