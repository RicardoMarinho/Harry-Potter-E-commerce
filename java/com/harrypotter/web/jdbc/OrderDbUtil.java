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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author ricardo.braz
 */
class OrderDbUtil {
    
    private DataSource dataSource;

    OrderDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    int createOrder(Order newOrder) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "insert into orders"
                    + "(accountId, total, shipAddress) "
                    + "values (?, ?, ?)";
            myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // set the param values for the item cart
            myStmt.setInt(1, newOrder.getAccount());
            myStmt.setFloat(2, newOrder.getTotal());
            myStmt.setString(3, newOrder.getShipAddress());
            
            // execute sql insert
            myStmt.execute();
            myRs = myStmt.getGeneratedKeys();
            int gerenatedKey = 0;
            if(myRs.next()){
                gerenatedKey = myRs.getInt(1);
            }
            return gerenatedKey;
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

    List<Order> getOrders(int id) throws Exception{
        List<Order> orders = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from orders where accountId=? ";
            myStmt = myConn.prepareStatement(sql);

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, id);
            
            // execute statement
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int orderId = myRs.getInt("id");
                int accountId = myRs.getInt("accountId");
                String status = myRs.getString("status");
                String shipAddress = myRs.getString("shipAddress");
                float total = myRs.getInt("total");
                Timestamp ordered = myRs.getTimestamp("ordered");
                Timestamp shipped = myRs.getTimestamp("shipped");

                // create new order object
                Order tempOrder = new Order(orderId, accountId, status, shipAddress, total, ordered, shipped);

                // add it to the list of orders
                orders.add(tempOrder);
            }

            return orders;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    Order viewOrder(int orderId) throws Exception{
        Order theOrder = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to get selected product
            String sql = "select * from orders where id=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, orderId);
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                // retrieve data from result set row
                int id = myRs.getInt("id");
                int accountId = myRs.getInt("accountId");
                String status = myRs.getString("status");
                String shipAddress = myRs.getString("shipAddress");
                float total = myRs.getInt("total");
                Timestamp ordered = myRs.getTimestamp("ordered");
                Timestamp shipped = myRs.getTimestamp("shipped");

                // create new order object
                theOrder = new Order(id, accountId, status, shipAddress, total, ordered, shipped);
            }
            
            return theOrder;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
    
}
