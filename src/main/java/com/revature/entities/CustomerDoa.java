package com.revature.entities;

import java.sql.*;
import java.util.List;

import com.revature.Customer;

public class CustomerDoa implements DAO<Customer> {
    Connection connection;

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void insert(Customer customer) {
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement("INSERT INTO customer(userName, password, fname, lname) VALUES(?,?,?,?)");
            preStatement.setString(1, customer.getUserName());
            preStatement.setString(2, customer.getPassword());
            preStatement.setString(3, customer.getFname());
            preStatement.setString(4, customer.getLname());
            preStatement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Something went wrong and could not insert customer");
        }
    }

    @Override
    public void remove() {
        
    }

    @Override
    public void update() {

    }
    
}