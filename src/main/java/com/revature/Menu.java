package com.revature;

import java.util.Scanner;

public class Menu {
	
	Scanner in = new Scanner(System.in);
	char response = '\0';
	
	public void MainMenu() {
		
		System.out.print("********************\nWELCOME!!!\n********************");
		System.out.print("\n(L)ogin\n(N)ew User\n");
		System.out.println("(B)alance");
		System.out.println("(E)xit");
		//Scanner in = new Scanner(System.in);
		response = in.next().charAt(0);
		switch (response) {
			case 'L':
				ReturnUser();
				break;
			case 'N':
				CreateLogin();
			case 'B':{
				CheckBalance();
			}
			case 'E':{
				return;
			}
			default:
				break;
		}
	}
	public void CreateLogin() {
	
		System.out.print(" Please Enter your First name: ");
		String input = in.nextLine();
		System.out.print(input);
				
	}
	public void ReturnUser(){
		System.out.println("Please Enter Your Username");
		String name = in.nextLine();
		Customer customer = new Customer(name);
		System.out.println("Password?");
		String password = in.next();
		System.out.println(customer.fname + " " + password); 
		
	}
	public void CheckBalance(){
		System.out.println("Please your account number");
		String input = in.nextLine();
		BankAccount bankAccount = new BankAccount(input);
		bankAccount.Deposit(100000);		
		if(input == bankAccount.accountNumber){
			System.out.println("The current balance is " + bankAccount.checkBalance());
		}
	}

}
