/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author ricardo.braz
 */
public class Order {
    private int id;
    private int account;
    private String status;
    private String shipAddress;
    private float total;
    private Timestamp ordered;
    private Timestamp shipped;

    public Order(int id, int account, String status, String shipAddress, float total, Timestamp ordered, Timestamp shipped) {
        this.id = id;
        this.account = account;
        this.total = total;
        this.status = status;
        this.shipAddress = shipAddress;
        this.ordered = ordered;
        this.shipped = shipped;
    }

    public Order(int account, float total, String shipAddress) {
        this.account = account;
        this.total = total;
        this.shipAddress = shipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Timestamp getOrdered() {
        return ordered;
    }

    public void setOrdered(Timestamp ordered) {
        this.ordered = ordered;
    }

    public Timestamp getShipped() {
        return shipped;
    }

    public void setShipped(Timestamp shipped) {
        this.shipped = shipped;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", account=" + account + ", status=" + status + ", shipAddress=" + shipAddress + ", total=" + total + ", ordered=" + ordered + ", shipped=" + shipped + '}';
    }

    
       
}

