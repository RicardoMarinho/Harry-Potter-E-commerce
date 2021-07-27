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
public class Account {
    private int id;
    private String address;
    private String name;
    private String password;
    private boolean ativo;
    private String email;
    private String phone;
    private boolean isTemporary;
    private LocalDateTime register;

    public Account(int id, String address, String name, String password, boolean ativo, String email, String phone, boolean isTemporary) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.password = password;
        this.ativo = ativo;
        this.email = email;
        this.phone = phone;
        this.isTemporary = isTemporary;
    }

    public Account(String name, String password, String email, boolean isTemporary) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.register = LocalDateTime.now();
        this.isTemporary = isTemporary;
    }

    public Account(int id, String address, String name, String email, String phone) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Account(int id, String address, String name, String password, String email, String phone, boolean isTemporary) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isTemporary = isTemporary;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = register;
    }

    public boolean getIsTemporary() {
        return isTemporary;
    }

    public void setIsTemporary(boolean isTemporary) {
        this.isTemporary = isTemporary;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", address=" + address + ", name=" + name + ", password=" + password + ", ativo=" + ativo + ", email=" + email + ", phone=" + phone + ", isTemporary=" + isTemporary + ", register=" + register + '}';
    }
    
}
