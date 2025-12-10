package com.mypos.cashier.model;

import java.math.BigDecimal;

/**
 * A simple POJO (Plain Old Java Object) that represents a single item
 * in the cashier's shopping cart. This is used to pass data from the
 * view (JTable) to the DAO.
 */
public class CartItem {
    
    private String productCode;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subtotal;

    // Constructor to easily create an item from the cart data
    public CartItem(String productCode, String productName, BigDecimal price, int quantity, BigDecimal subtotal) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // --- Standard Getters ---

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}