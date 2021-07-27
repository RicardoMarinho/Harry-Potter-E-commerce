/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo.braz
 */
public class AccountDbUtil {
    private DataSource dataSource;

    public AccountDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public void addAccount(Account theAccount) throws Exception{
        
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "insert into account"
                    + "(name, email, password, isTempory) "
                    + "values (?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the account
            myStmt.setString(1, theAccount.getName());
            myStmt.setString(2, theAccount.getEmail());
            myStmt.setString(3, theAccount.getPassword());
            myStmt.setBoolean(4, theAccount.getIsTemporary());
            
            // execute sql insert
            myStmt.execute();
            
        } 
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
        
    }
    
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } 
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    Account loginAccount(String email, String password) throws Exception{
        Account theAccount = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to verify account credentials
            String sql = "select * from account where email=? and password=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setString(1, email);
            myStmt.setString(2, password);
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                
                int _id = myRs.getInt("id");
                String _name = myRs.getString("name");
                String _address = myRs.getString("address");
                String _email = myRs.getString("email");
                String _password = myRs.getString("password");
                boolean _ativo = myRs.getBoolean("ativo");
                String _phone = myRs.getString("phone");
                boolean _isTemporary = myRs.getBoolean("isTemporary");
                // construct account object with database values
                theAccount = new Account(_id, _address, _name, _password, _ativo, _email, _phone, _isTemporary);
            }
            return theAccount;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updateAccount(Account theAccount) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "update account "
                    + "set name=?, email=?, phone=?, address=? "
                    + "where id=?";
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the account
            myStmt.setString(1, theAccount.getName());
            myStmt.setString(2, theAccount.getEmail());
            myStmt.setString(3, theAccount.getPhone());
            myStmt.setString(4, theAccount.getAddress());
            myStmt.setInt(5, theAccount.getId());
            
            // execute sql insert
            myStmt.execute();
        } 
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }
    public Account getAccount(String theAccountId) throws Exception{
        Account theAccount = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int accountId;
        
        try{
            // convert account id to int
            accountId = Integer.parseInt(theAccountId);
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to get logged account
            String sql = "select * from account where id=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, accountId);
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                
                int _id = myRs.getInt("id");
                String _name = myRs.getString("name");
                String _address = myRs.getString("address");
                String _email = myRs.getString("email");
                String _password = myRs.getString("password");
                boolean _ativo = myRs.getBoolean("ativo");
                String _phone = myRs.getString("phone");
                boolean _isTemporary = myRs.getBoolean("isTemporary");
                
                // construct account object with database values
                theAccount = new Account(_id, _address, _name, _password, _ativo, _email, _phone, _isTemporary);

            }
            
            return theAccount;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    Account verifyTemporaryAccount(Account tempAccount) throws Exception{
        Account theAccount = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to verify account
            String sql = "select * from account where name = ? and email = ? and password = ?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setString(1, tempAccount.getName());
            myStmt.setString(2, tempAccount.getEmail());
            myStmt.setString(3, tempAccount.getPassword());
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                
                int _id = myRs.getInt("id");
                String _name = myRs.getString("name");
                String _address = myRs.getString("address");
                String _email = myRs.getString("email");
                String _password = myRs.getString("password");
                boolean _ativo = myRs.getBoolean("ativo");
                String _phone = myRs.getString("phone");
                boolean _isTemporary = myRs.getBoolean("isTemporary");
                
                // construct account object with database values
                theAccount = new Account(_id, _address, _name, _password, _ativo, _email, _phone, _isTemporary);

            }
            
            return theAccount;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    Account addTemporaryAccount(Account tempAccount) throws Exception{
        Account theAccount = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String firstSql = "insert into account"
                    + "(name, email, password) "
                    + "values (?, ?, ?)";
            myStmt = myConn.prepareStatement(firstSql);
            
            // set the param values for the account
            myStmt.setString(1, tempAccount.getName());
            myStmt.setString(2, tempAccount.getEmail());
            myStmt.setString(3, tempAccount.getPassword());
            
            // execute sql insert
            myStmt.execute();
            
            close(myConn, myStmt, myRs);
            
            myConn = null;
            myStmt = null;
            myRs = null;
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // get data of new temp account
            String secondSql = "select * from account where name = ? and email = ? and password = ?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(secondSql);
            
            // set params
            myStmt.setString(1, tempAccount.getName());
            myStmt.setString(2, tempAccount.getEmail());
            myStmt.setString(3, tempAccount.getPassword());
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                
                int _id = myRs.getInt("id");
                String _name = myRs.getString("name");
                String _address = myRs.getString("address");
                String _email = myRs.getString("email");
                String _password = myRs.getString("password");
                boolean _ativo = myRs.getBoolean("ativo");
                String _phone = myRs.getString("phone");
                boolean _isTemporary = myRs.getBoolean("isTemporary");
                
                // construct account object with database values
                theAccount = new Account(_id, _address, _name, _password, _ativo, _email, _phone, _isTemporary);

            }
            
            return theAccount;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
    
    public void updateTemporaryAccount(Account theAccount) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "update account "
                    + "set name=?, password=?, email=?, phone=?, address=?, isTemporary=? "
                    + "where id=?";
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the account
            myStmt.setString(1, theAccount.getName());
            myStmt.setString(2, theAccount.getPassword());
            myStmt.setString(3, theAccount.getEmail());
            myStmt.setString(4, theAccount.getPhone());
            myStmt.setString(5, theAccount.getAddress());
            myStmt.setBoolean(6, theAccount.getIsTemporary());
            myStmt.setInt(7, theAccount.getId());
            
            // execute sql insert
            myStmt.execute();
        } 
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    
    

    
}
