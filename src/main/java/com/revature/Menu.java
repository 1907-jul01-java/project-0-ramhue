package com.revature;

import java.util.Scanner;

import com.revature.util.ConnectionUtil;
import com.revature.entities.*;

public class Menu {
	
	Scanner in = new Scanner(System.in);
	char response = '\0';
	
	public void MainMenu() {
		
		System.out.print("********************\nWELCOME!!!\n********************");
		System.out.print("\n(L)ogin\n(N)ew User\n");
		//System.out.println("(A)dmin Login");
		System.out.println("(E)xit");
		//Scanner in = new Scanner(System.in);
		response = in.next().charAt(0);
		switch (response) {
			case 'A':
				break;
			case 'L':
				ReturnUser();
				break;
			case 'N':
				CreateLogin();
				break;
			case 'E':{
				return;
			}
			default:
				break;
		}
	}
	public void CreateLogin() {
		System.out.println("Please Enter your First name: ");
		String input = in.next();
		System.out.println("Please enter your last name");
		String lname = in.next();
		System.out.println("Please enter a unique username");
		String username = in.next();
		System.out.println("Please set your password");
		String password = in.next();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		Customer customer = new Customer(input, lname, username, password);
		CustomerDao customerDao = new CustomerDao(connectionUtil.getConnection());
		customerDao.insert(customer);

		System.out.println(customerDao.getAll());
		CreateAccount(customer);
		//System.out.print(input);
				
	}
	public void CreateAccount(Customer customer){
		char type = '\0';
		String acctType = new String();
		Double balance = 0.0;
		System.out.println("PLease Select the Account type ");
		System.out.println("(S)avings\n(C)heckings\n(O)ther");
		type = in.next().charAt(0);
		switch (type) {
			case 'S':
				acctType = new String("Savings");
				break;
			case'C':
				acctType = new String("Checkings");
				break;
			case 'O':
				System.out.println("Please enter the custom type of account");
				acctType = in.next();
				break;
			default:
				break;
		}
			while(balance <= 0.0){	
			System.out.println("Enter the ammount for your initial Deposit.(must be greater than $0)");
			balance = in.nextDouble();
				if (balance <= 0){
				System.out.println("Deposit must be greater than" + balance);
				}
			}
			BankAccount account = new BankAccount(acctType, balance,0);
			ConnectionUtil connect = new ConnectionUtil();
			ConnectionUtil connect2 = new ConnectionUtil();
			AccountDao accountDoa = new AccountDao(connect.getConnection());
			accountDoa.insert(account);
			Customer customer2 = customer;
			Ac ac = new Ac(account.getAccountNumber(), customer2.getCustomerId());
			AcDao acDoa = new AcDao(connect2.getConnection());
			acDoa.insert(ac);
			//System.out.println(acDoa.getAll());
	}

	public void CustomerMenu(Customer customer){
		boolean quit = false;
		do{
		System.out.println("***********************Customer Menu***********************");
		System.out.println("What would you like to do?");
		System.out.println("(M)y accounts\n(W)ithdraw\n(D)eposit\n(O)pen an account\n(L)ogout");
		char response = in.next().charAt(0);
		
		switch (response) {
			case 'M':
				Myaccounts(customer);
				break;
			
			case 'O':
				CreateAccount(customer);
				break;
			case 'W':
				Myaccounts(customer);
				System.out.println("Please enter an amount you wish to witdraw");
				double amount = in.nextDouble();
				System.out.println("Please enter the account number you want to withdraw from");
				int acctNo = in.nextInt();
				Withdraw(customer, amount, acctNo);
				Myaccounts(customer);
				break;
				
			case 'D':
				Myaccounts(customer);
				System.out.println("Please enter an amount you wish to Deposit");
				double depAmount = in.nextDouble();
				System.out.println("Please enter the account number you want to deposit into");
				int acctNum = in.nextInt();
				Deposit(customer, depAmount, acctNum);
				Myaccounts(customer);
				break;
			case 'L':
				System.out.println("You have sucessfully logged out.");
				quit = true;
				break;
			default:
				break;
		}
		} while(!quit);
		
	}
	public void ReturnUser(){
		System.out.println("Please Enter Your Username");
		String name = in.next();
		System.out.println("Please enter Your password");
		String password = in.next();
		ConnectionUtil connects = new ConnectionUtil();
		CustomerDao customerDoa = new CustomerDao(connects.getConnection());
		Customer customer = new Customer("fname", "lname", name, password );

		if (customerDoa.login(customer)){
			System.out.println("I did it");
			CustomerMenu(customer);
		}
		else{
			System.out.println("Login failed. Wrong credentials");
		}
		MainMenu();
				
	}
	public void Myaccounts(Customer customer){
		ConnectionUtil connection = new ConnectionUtil();
		AccountDao accounts = new AccountDao(connection.getConnection());
		System.out.println("Bank Account(s) for "+ customer.getFname() + " " + customer.getLname());
		customer.bankaccounts = accounts.getAllCustomersAccounts(customer);
		System.out.println(customer.bankaccounts);		
	}
	public void Withdraw(Customer customer, double amount, int acctNo){
		//Myaccounts(customer);
		if (amount > 0){
			for (int i = 0; i < customer.bankaccounts.size(); i++){
				if (customer.bankaccounts.get(i).getAccountNumber() == acctNo){
					if (customer.bankaccounts.get(i).getBalance() >= amount){
						double balance = customer.bankaccounts.get(i).getBalance() - amount;
						customer.bankaccounts.get(i).setBalance(balance);
						System.out.println("Your new balance is " + balance);
						ConnectionUtil connection = new ConnectionUtil();
						AccountDao account = new AccountDao(connection.getConnection());
						account.update(acctNo, balance);
						break;
					}
					else{System.out.println("insufficient Funds");break;}
				}else{
					System.out.println("Wrong account Number");
			}
		} 
		}else{System.out.println("invaild amount");}
	};

	public void Deposit(Customer customer, double amount, int acctNo){
		//Myaccounts(customer);
		if (amount > 0){
			for (int i = 0; i < customer.bankaccounts.size(); i++){
				if (customer.bankaccounts.get(i).getAccountNumber() == acctNo){
						double balance = customer.bankaccounts.get(i).getBalance() + amount;
						customer.bankaccounts.get(i).setBalance(balance);
						System.out.println("Your new balance is " + balance);
						ConnectionUtil connection = new ConnectionUtil();
						AccountDao account = new AccountDao(connection.getConnection());
						account.update(acctNo, balance);
						break;
					}else{
					System.out.println("Wrong account Number");
					}
			}
		}else{System.out.println("invaild amount");} 
	}
	public void transfer(){};
	}
