package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.Customer;



public class CustomerDao implements DAO<Customer> {
    Connection connection;

    public CustomerDao(Connection connection){this.connection = connection;}
    
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
            preStatement = connection.prepareStatement("INSERT INTO customer(userName, password, fname, lname) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preStatement.setString(1, customer.getUserName());
            preStatement.setString(2, customer.getPassword());
            preStatement.setString(3, customer.getFname());
            preStatement.setString(4, customer.getLname());
            preStatement.executeUpdate();
            ResultSet addUserResults = preStatement.getGeneratedKeys();
            if(addUserResults.next())
                customer.setCustomerId(addUserResults.getInt(1));
        }catch(SQLException e){
            System.err.println("Something went wrong and could not insert customer");

        }
    }

    @Override
    public void remove() {
        
    }

    @Override
    public void update(int a, double b) {

    }
    public boolean login(Customer customer){
        try{ 
        PreparedStatement pState = connection.prepareStatement("SELECT * FROM customer WHERE userName = ? AND password = ?");
        pState.setString(1, customer.getUserName());
        pState.setString(2, customer.getPassword());
        ResultSet rs = pState.executeQuery();
        if (rs.next()){
            customer.setCustomerId(rs.getInt("customerid"));
            customer.setFname(rs.getString("fname"));
            customer.setLname(rs.getString("lname"));
            System.out.println("Welcome back " + customer.getFname() + " " + customer.getLname());
            return true;
        }
        else return false;
        }catch(SQLException ex){
            System.err.println("Someting went wrong");
        }
        return false;
    }
    
}