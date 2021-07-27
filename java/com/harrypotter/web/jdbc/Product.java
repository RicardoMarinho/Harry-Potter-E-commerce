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
public class Product {
    
    private int id;
    private String name;
    private String description;
    private String category;
    private String subcategory;
    private int stock;
    private float price;
    private String imgUrl;
    private LocalDateTime register;
    private boolean isPopular;

    public Product(int id, String name, String description, String category, String subcategory, int stock, float price, String imgUrl, boolean isPopular) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.stock = stock;
        this.price = price;
        this.imgUrl = imgUrl;
        this.isPopular = isPopular;
    }

    public Product(int id, String name, String subcategory, float price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = register;
    }

    public boolean isIsPopular() {
        return isPopular;
    }

    public void setIsPopular(boolean isPopular) {
        this.isPopular = isPopular;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", category=" + category + ", stock=" + stock + ", price=" + price + ", imgUrl=" + imgUrl + ", register=" + register + '}';
    }
    
    
    
}
