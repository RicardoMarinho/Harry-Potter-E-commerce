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
public class OrderDetailsDbUtil {
    private DataSource dataSource;

    public OrderDetailsDbUtil(DataSource theDataSource) {
        this.dataSource = theDataSource;
    }
    
    void createOrderDetails(OrderDetails theOrderDetails) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "insert into orderdetails(orderId, productId, productname, qty, productprice) values (?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the account
            myStmt.setInt(1, theOrderDetails.getOrderId());
            myStmt.setInt(2, theOrderDetails.getProductId());
            myStmt.setString(3, theOrderDetails.getProductname());
            myStmt.setInt(4, theOrderDetails.getQty());
            myStmt.setFloat(5, theOrderDetails.getProductprice());

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

    List<OrderDetails> getOrdersDetails(int id) throws Exception{
        List<OrderDetails> orders = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select od.* from orderdetails od"
                    + " inner join orders o on o.id = od.orderId"
                    + " where o.accountId=? ";
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
                int orderId = myRs.getInt("orderId");
                int productId = myRs.getInt("productId");
                String productname = myRs.getString("productname");
                int qty = myRs.getInt("qty");
                float price = myRs.getFloat("productprice");

                // create new order details object
                OrderDetails tempOrder = new OrderDetails(orderId, productId, productname, qty, price);

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

    List<OrderDetails> viewOrderDetail(int id) throws  Exception{
        List<OrderDetails> orders = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from orderdetails where orderId=? ";
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
                int orderId = myRs.getInt("orderId");
                int productId = myRs.getInt("productId");
                String productname = myRs.getString("productname");
                int qty = myRs.getInt("qty");
                float price = myRs.getFloat("productprice");

                // create new order details object
                OrderDetails tempOrder = new OrderDetails(orderId, productId, productname, qty, price);

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
    
    
}
