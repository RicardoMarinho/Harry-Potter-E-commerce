/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author ricardo.braz
 */
public class CartDbUtil {
    private DataSource dataSource;

    public CartDbUtil(DataSource theDataSource) {
        this.dataSource = theDataSource;
    }
    
    public Cart getCart(int theCartId) throws Exception{
        Cart theCart = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try{

            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to get logged account
            String sql = "select * from cart where userId=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, theCartId);
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                
                int _id = myRs.getInt("id");
                int _userId = myRs.getInt("userId");
                
                // construct account object with database values
                theCart = new Cart(_id, _userId);

            }
            
            return theCart;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
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

    void createCart(int userId) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "insert into cart(userId) values (?)";
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the account
            myStmt.setInt(1, userId);

            // execute sql insert
            myStmt.execute();
            
        } 
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

}
