package com.revature.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.revature.Ac;

public class AcDao implements DAO<Ac> {
    
    Connection connection;
    
    @Override
    public List<Ac> getAll() {
        String sql = "SELECT * FROM ac";
        Ac ac;
        List<Ac> acs = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while(results.next()){
                ac = new Ac();
                ac.setAcctnum(results.getInt("acctnum"));
                ac.setId(results.getInt(""));
                acs.add(ac);
            }
        } catch (SQLException e) {
            System.err.println("OOPS");
        } return acs;
    }

    @Override
    public void insert(Ac ac) {
        PreparedStatement preStatement;
        try {
            System.out.println(ac);
            preStatement = connection.prepareStatement("INSERT INTO ac (acctnum, custid) VALUES(?,?)");
            preStatement.setInt(1, ac.getAcctnum());
            preStatement.setInt(2, ac.getcustId());
            preStatement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Username is taken please try again");
            
        }

    }

    @Override
    public void remove() {

    }

    @Override
    public void update() {

    }

    public AcDao(Connection connection) {
        this.connection = connection;
    }}