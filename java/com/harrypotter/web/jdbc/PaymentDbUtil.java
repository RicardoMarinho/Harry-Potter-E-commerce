/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo.braz
 */
public class PaymentDbUtil {

    private DataSource dataSource;
    
    PaymentDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    List<Payment> getAllPaymentMethods(int _userId) throws Exception{
        List<Payment> payments = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from payments where userId=?";
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, _userId);
            
            // execute query
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                int userId = myRs.getInt("userId");
                String fullName = myRs.getString("fullName");
                String cardNumber = myRs.getString("cardNumber");
                int monthExpire = myRs.getInt("monthExpire");
                int yearExpire = myRs.getInt("yearExpire");
                int cvv = myRs.getInt("cvv");
                boolean active = myRs.getBoolean("active");

                // create new payment object
                Payment tempPayment = new Payment(id, userId, fullName, cardNumber, monthExpire, yearExpire, cvv, active);

                // add it to the list of payments
                payments.add(tempPayment);
            }

            return payments;
        } 
        finally {
            // close JDBC objects
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

    void addPayment(Payment thePayment) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "insert into payments"
                    + "(userId, fullName, cardNumber, monthExpire, yearExpire, cvv) "
                    + "values (?, ?, ?, ?, ?, ?)";
            
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the account
            myStmt.setInt(1, thePayment.getUserId());
            myStmt.setString(2, thePayment.getFullName());
            myStmt.setString(3, thePayment.getCardNumber());
            myStmt.setInt(4, thePayment.getMonthExpire());
            myStmt.setInt(5, thePayment.getYearExpire());
            myStmt.setInt(6, thePayment.getCvv());
            
            // execute sql insert
            myStmt.execute();
            
        } 
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }      
    }
}
