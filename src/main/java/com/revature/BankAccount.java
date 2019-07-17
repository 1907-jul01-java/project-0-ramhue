package com.revature;

public class BankAccount {
	int accountNumber;
	String accountType;
	double balance;
	

	public BankAccount(String AcctType, double balance, int acctNum) {
		super();
		this.accountType = AcctType;
		this.accountNumber = acctNum;
		this.balance = balance;
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
	public int getAccountNumber() {return accountNumber;}

	public void setAccountNumber(int accountNumber) {this.accountNumber = accountNumber;}

	//Account Balance getters and setters
	public double getBalance() {return balance;}

	public void setBalance(double balance) {this.balance = balance;}



	public String getAccountType() {return accountType;}

	public void setAccountType(String accountType) {this.accountType = accountType;}

	@Override
	public String toString() {
		return "\naccountNumber= " + accountNumber + " accountType= " + accountType + " balance= " + balance
				+ "\n";
	}
}
