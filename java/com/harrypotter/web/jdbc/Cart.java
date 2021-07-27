/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;

import java.time.LocalTime;

/**
 *
 * @author ricardo.braz
 */
public class Cart {
    
    private int id;
    private int userId;
    private LocalTime register;

    public Cart(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Cart(int userId) {
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalTime getRegister() {
        return register;
    }

    public void setRegister(LocalTime register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", userId=" + userId + '}';
    }
    
}
