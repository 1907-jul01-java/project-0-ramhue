package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.Customer;

public class CustomerDoa implements DAO<Customer> {
    Connection connection;

    public CustomerDoa(Connection connection){this.connection = connection;}
    
    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        Customer customer;
        List<Customer> customers = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while(results.next()){
                customer = new Customer(null);
                customer.setFname(results.getString("fname"));
                customer.setLname(results.getString("lname"));
                customer.setPassword(results.getString("password"));
                customer.setUserName(results.getString("username"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("OOPS");
        } return customers;
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