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
		System.out.println("(B)alance");
		System.out.println("(A)dmin Login");
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
			case 'B':{
				CheckBalance();
				break;
			}
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
			System.out.println(accountDoa.getAll());
			//System.out.println(acDoa.getAll());
	}

	public void CustomerMenu(){
		System.out.println("***********************Customer Menu***********************");
		System.out.print("What would you like to do?");
		System.out.print("(M)y accounts\n(W)ithdraw\n(D)eposit\n(T)ransfer\n(A)pply\n(L)ogout");
		char response = in.next().charAt(0);
		switch (response) {
			case 'L':
				System.out.println("You have sucessfully logged out.");
				MainMenu();
				break;
			case 'W':

		
			default:
				break;
		}
	}
	public void ReturnUser(){
		System.out.println("Please Enter Your Username");
		String name = in.next();
		System.out.println("Please enter Your password");
		String password = in.next();
		ConnectionUtil connects = new ConnectionUtil();
		CustomerDao customerDoa = new CustomerDao(connects.getConnection());
		
		if (customerDoa.login(name, password)){
			System.out.println("I did it");
		}
		else{
			System.out.println("Login failed. Wrong credentials");
		}
		
		//Customer customer = new Customer(name);
		System.out.println("Password?");
		//String password = in.next();
				
	}
	public void CheckBalance(){
		System.out.println("Please your account number");
		//String input = in.next();
		}
	public void Withdraw(){};
	public void Deposit(){};
	public void transfer(){};
	}
