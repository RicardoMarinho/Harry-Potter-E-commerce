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
public class Payment {
    private int id ;
    private int userId;
    private String fullName;
    private String cardNumber;
    private int monthExpire;
    private int yearExpire;
    private int cvv;
    private LocalDateTime register;
    private boolean active;

    public Payment(int id, int userId, String fullName, String cardNumber, int monthExpire, int yearExpire, int cvv, boolean active) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.cardNumber = cardNumber;
        this.monthExpire = monthExpire;
        this.yearExpire = yearExpire;
        this.cvv = cvv;
        this.active = active;
    }

    public Payment(int userId, String fullName, String cardNumber, int monthExpire, int yearExpire, int cvv) {
        this.userId = userId;
        this.fullName = fullName;
        this.cardNumber = cardNumber;
        this.monthExpire = monthExpire;
        this.yearExpire = yearExpire;
        this.cvv = cvv;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getMonthExpire() {
        return monthExpire;
    }

    public void setMonthExpire(int monthExpire) {
        this.monthExpire = monthExpire;
    }

    public int getYearExpire() {
        return yearExpire;
    }

    public void setYearExpire(int yearExpire) {
        this.yearExpire = yearExpire;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = register;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", userId=" + userId + ", fullName=" + fullName + ", cardNumber=" + cardNumber + ", monthExpire=" + monthExpire + ", yearExpire=" + yearExpire + ", cvv=" + cvv + '}';
    }
    
    
}
