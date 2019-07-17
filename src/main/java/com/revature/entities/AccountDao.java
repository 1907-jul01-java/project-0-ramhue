package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.BankAccount;
import com.revature.Customer;

public class AccountDao implements DAO<BankAccount> {
    Connection connection;

    @Override
    public void insert(BankAccount bankAccount) {
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement("INSERT INTO accounts(nameofacct, balance) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preStatement.setString(1, bankAccount.getAccountType());
            preStatement.setDouble(2, bankAccount.getBalance());
            preStatement.executeUpdate();
            ResultSet addAccountResults = preStatement.getGeneratedKeys();
            if(addAccountResults.next()){
                bankAccount.setAccountNumber(addAccountResults.getInt(1));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BankAccount> getAll() {
        BankAccount account;
        List<BankAccount> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM accounts"); 
            ResultSet results2 = results;
            while (results2.next()) {
                account = new BankAccount(results2.getString("nameofacct"), results2.getFloat("balance"),results2.getInt("acctNo"));
                accounts.add(account);
            }
        } catch (Exception e) {
        
        }
        return accounts;
    }

    @Override
    public void remove() {

    }

    @Override
    public void update(int acctnumber, double balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE acctNo = ?";
        try {
            PreparedStatement pstate = connection.prepareStatement(sql);
            pstate.setDouble(1, balance);
            pstate.setInt(2, acctnumber);
            pstate.executeUpdate();
        } catch (SQLException ex) {
            //TODO: handle exception
        }
    }
    public List<BankAccount> getAllCustomersAccounts(Customer customer){
        BankAccount account;
        List<BankAccount> accounts = new ArrayList<>();
        try {
            PreparedStatement pstatement = connection.prepareStatement("SELECT  * FROM accounts a JOIN ac b ON a.acctNo = b.acctNum JOIN customer c ON c.customerid = b.custid WHERE c.customerid = ? "); 
            pstatement.setInt(1, customer.getCustomerId());
            ResultSet results2 = pstatement.executeQuery();
            while (results2.next()) {
                account = new BankAccount(results2.getString("nameofacct"), results2.getFloat("balance"),results2.getInt("acctNo"));
                accounts.add(account);
            }
        } catch (Exception e) {
        
        }
        return accounts;
    }

    public AccountDao(Connection connection){ this.connection = connection;}

}