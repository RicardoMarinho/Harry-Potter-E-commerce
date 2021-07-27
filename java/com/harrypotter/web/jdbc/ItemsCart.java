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
public class ItemsCart {
    
    private int id;
    private int cartId;
    private int productId;
    private int qty ;
    private float total;
    private LocalDateTime register;

    public ItemsCart(int id, int cartId, int productId, int qty, float total) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.qty = qty;
        this.total = total;
    }

    public ItemsCart(int id, int cartId, int productId, int qty) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.qty = qty;
    }
    

    public ItemsCart(int cartId, int productId, int qty, float total) {
        this.cartId = cartId;
        this.productId = productId;
        this.qty = qty;
        this.total = total;
    }

    public ItemsCart(int cartId, int productId, int qty) {
        this.cartId = cartId;
        this.productId = productId;
        this.qty = qty;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = register;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", cartId=" + cartId + ", productId=" + productId + ", qty=" + qty + ", total=" + total + ", register=" + register + '}';
    }
    
}
