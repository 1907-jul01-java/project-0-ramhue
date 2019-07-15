package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.BankAccount;

public class AccountDao implements DAO<BankAccount> {
    Connection connection;

    @Override
    public void insert(BankAccount bankAccount) {
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement("INSERT INTO accounts(nameofacct, balance) VALUES(?, ?)");
            preStatement.setString(1, bankAccount.getAccountNumber());
            preStatement.setDouble(2, bankAccount.getBalance());
            preStatement.executeUpdate();
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
                account = new BankAccount(results2.getString("nameofacct"), results2.getFloat("balance"));
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
    public void update() {
    }

    public AccountDao(Connection connection){ this.connection = connection;}
}