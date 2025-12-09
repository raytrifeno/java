package com.mypos.product.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * A POJO (Plain Old Java Object) that represents a single product
 * from the 'products' database table.
 */
public class Product {

    private int id;
    private String code; // Barcode
    private String name;
    private String category;
    private BigDecimal price;
    private int stock;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default constructor
    public Product() {
    }

    // Constructor with all fields
    public Product(int id, String code, String name, String category, BigDecimal price, int stock, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}