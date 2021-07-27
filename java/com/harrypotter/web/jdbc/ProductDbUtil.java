/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.sql.Array;
import javax.sql.DataSource;
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

public class ProductDbUtil {
    
    private DataSource dataSource;

    public ProductDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    List<Product> getPopularProducts() throws Exception{
        List<Product> products = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from products where isPopular=1 ";
            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String subcategory = myRs.getString("subcategory");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");

                // create new product object
                Product tempProduct = new Product(id, name, subcategory, price, imgUrl);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
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

    Product getProduct(String theproductId) throws Exception{
        Product theProduct = null;
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int productId;
        
        try{
            // convert product id to int
            productId = Integer.parseInt(theproductId);
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to get selected product
            String sql = "select * from products where id=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setInt(1, productId);
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            if(myRs.next()){
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String category = myRs.getString("category");
                String subcategory = myRs.getString("subcategory");
                int stock = myRs.getInt("stock");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");
                boolean isPopular = myRs.getBoolean("isPopular");
                
                // use the productId during construction
                theProduct = new Product(id, name, description, category, subcategory, stock, price, imgUrl, isPopular);
            }else{
                throw new Exception("Could not find product id: " + productId);
            }
            
            return theProduct;
        }
        finally{
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    List<Product> getAllProducts() throws Exception{
        List<Product> products = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from products order by name ASC";
            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String category = myRs.getString("category");
                String subcategory = myRs.getString("subcategory");
                int stock = myRs.getInt("stock");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");
                boolean isPopular = myRs.getBoolean("isPopular");

                // create new product object
                Product tempProduct= new Product(id, name, description, category, subcategory, stock, price, imgUrl, isPopular);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    List<Product> getSortedProducts(String sortValue, String option) throws Exception {
        
        String orderBy;
        
        switch(sortValue){
            
            case "nameAsc":
                orderBy = "name ASC";
                break;
            case "nameDesc":
                orderBy = "name DESC";
                break;
            case "priceAsc":
                orderBy = "price ASC";
                break;
            case "priceDesc":
                orderBy = "price DESC";
                break;
            default:
                orderBy = "name ASC";
        }
        
        List<Product> products = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql;
            if("clothing".equals(option) || "departments".equals(option) || "souvenirs".equals(option)){
                sql = "select * from products where category = '" + option + "' order by " + orderBy;
            }
            else
            {
                if("costumes".equals(option) || "books".equals(option) || "bookmarks".equals(option) || "knitwear".equals(option))
                {
                    sql = "select * from products where subcategory = '" + option + "' order by " + orderBy;
                }
                else
                {
                    sql = "select * from products where category like '%" + option + "%' or name like '%" + option + "%' or description like '%" + option + "%' or subcategory like '%" + option + "%' order by " + orderBy;
                }
            }
            
            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String category = myRs.getString("category");
                String subcategory = myRs.getString("subcategory");
                int stock = myRs.getInt("stock");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");
                boolean isPopular = myRs.getBoolean("isPopular");

                // create new product object
                Product tempProduct= new Product(id, name, description, category, subcategory, stock, price, imgUrl, isPopular);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    List<Product> getProductsByCategory(String categoryId) throws Exception{
        List<Product> products = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
             // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to verify account credentials
            String sql = "select * from products where category=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setString(1, categoryId);

            // execute statement
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String category = myRs.getString("category");
                String subcategory = myRs.getString("subcategory");
                int stock = myRs.getInt("stock");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");
                boolean isPopular = myRs.getBoolean("isPopular");

                // create new product object
                Product tempProduct= new Product(id, name, description, category, subcategory, stock, price, imgUrl, isPopular);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    List<Product> searchProduct(String keywords) throws Exception {
        List<Product> products = new ArrayList<>();
        keywords = "%"+keywords+"%";
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
             // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to verify account credentials
            String sql = "select * from products where category like ? "
                    + "or name like ? or description like ? or subcategory like ? order by name ASC";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setString(1, keywords);
            myStmt.setString(2, keywords);
            myStmt.setString(3, keywords);
            myStmt.setString(4, keywords);

            // execute statement
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String category = myRs.getString("category");
                String subcategory = myRs.getString("subcategory");
                int stock = myRs.getInt("stock");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");
                boolean isPopular = myRs.getBoolean("isPopular");

                // create new product object
                Product tempProduct= new Product(id, name, description, category, subcategory, stock, price, imgUrl, isPopular);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    List<Product> getProductsBySubCategory(String subcategoryId) throws Exception {
        List<Product> products = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
             // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to verify account credentials
            String sql = "select * from products where subcategory=?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            
            // set params
            myStmt.setString(1, subcategoryId);

            // execute statement
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String description = myRs.getString("description");
                String category = myRs.getString("category");
                String subcategory = myRs.getString("subcategory");
                int stock = myRs.getInt("stock");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");
                boolean isPopular = myRs.getBoolean("isPopular");

                // create new product object
                Product tempProduct= new Product(id, name, description, category, subcategory, stock, price, imgUrl, isPopular);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    List<Product> getInfoProductsCart(int cartId) throws Exception{
        List<Product> products = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
             // get connection to database
            myConn = dataSource.getConnection();
            
            // create sql to verify account credentials
            String sql = "select p.* from products p " +
                         "LEFT JOIN itemscart ic " +
                         "ON ic.productId = p.id " +
                         "WHERE ic.cartId = ?";
            
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, cartId);

            // execute statement
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                String subcategory = myRs.getString("subcategory");
                float price = myRs.getFloat("price");
                String imgUrl = myRs.getString("imgUrl");

                // create new product object
                Product tempProduct= new Product(id, name, subcategory, price, imgUrl);

                // add it to the list of products
                products.add(tempProduct);
            }

            return products;
        } 
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
}
    
