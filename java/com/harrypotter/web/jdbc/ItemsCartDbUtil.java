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
public class ItemsCartDbUtil {
    private DataSource dataSource;

    public ItemsCartDbUtil(DataSource theDataSource) {
        this.dataSource = theDataSource;
    }
    
    List<ItemsCart> getItemsCart(int _cartId) throws Exception{
        List<ItemsCart> itemsCart = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from itemsCart where cartId=?";
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, _cartId);
            
            // execute query
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                int cartId = myRs.getInt("cartId");
                int productId = myRs.getInt("productId");
                int qty = myRs.getInt("qty");
                //float total = myRs.getFloat("total");

                // create new payment object
                ItemsCart tempCart = new ItemsCart(id, cartId, productId, qty);

                // add it to the list of items from cart
                itemsCart.add(tempCart);
            }

            return itemsCart;
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

    void addToCart(ItemsCart theItemsCart) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            
            // create sql for insert
            String sql = "insert into itemsCart"
                    + "(cartId, productId, qty) "
                    + "values (?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);
            
            // set the param values for the item cart
            myStmt.setInt(1, theItemsCart.getCartId());
            myStmt.setInt(2, theItemsCart.getProductId());
            myStmt.setInt(3, theItemsCart.getQty());
            
            // execute sql insert
            myStmt.execute();
            
        } 
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    int checkDuplicateItem(int cartId, int productId) throws Exception{
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        int result = 0;
        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to update qty of product added
            String sql = "Update itemsCart set qty=qty+1 where cartId = ? and productId = ?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, cartId);
            myStmt.setInt(2, productId);
            
            // execute statement
            result = myStmt.executeUpdate();
            
            return result;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    void deleteItemCart(int productId, int cartId) throws  Exception{

        Connection myConn = null;
        PreparedStatement myStmt = null;
        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to remove product from cart
            String sql = "Delete from itemscart where cartId = ? and productId = ?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, cartId);
            myStmt.setInt(2, productId);
            
            // execute statement
            myStmt.execute();
            
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    void emptyItemsCart(int id) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;
        
        try{
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to empty cart
            String sql = "Delete from itemscart where cartId = ?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, id);
            
            // execute statement
            myStmt.execute();
            
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }
}
