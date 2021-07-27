/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.time.LocalDateTime;

/**
 *
 * @author ricardo.braz
 */
public class OrderDetails {
    private int orderId;
    private int productId;
    private String productname;
    private int qty;
    private float productprice;
    private LocalDateTime register;

    public OrderDetails(int orderId, int productId, String productname, int qty, float productprice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productname = productname;
        this.qty = qty;
        this.productprice = productprice;
    }

    public OrderDetails(int orderId, int productId, String productname, float productprice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productname = productname;
        this.productprice = productprice;
    }

    public OrderDetails(int orderId, int productId, String productname, int qty, float productprice, LocalDateTime register) {
        this.orderId = orderId;
        this.productId = productId;
        this.productname = productname;
        this.qty = qty;
        this.productprice = productprice;
        this.register = register;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getProductprice() {
        return productprice;
    }

    public void setProductprice(float productprice) {
        this.productprice = productprice;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = register;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderId=" + orderId + ", productId=" + productId + ", productname=" + productname + ", qty=" + qty + ", productprice=" + productprice + ", register=" + register + '}';
    }
 
}
