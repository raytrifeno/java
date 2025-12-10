package com.mypos.product.dao;

import com.mypos.config.connectDB;
import com.mypos.product.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all database operations for the Product entity.
 */
public class ProductDao {

    /**
     * Retrieves all products from the database, ordered by the most recently created.
     * @return 
     */
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT id, code, name, category, price, stock, created_at, updated_at FROM products ORDER BY created_at DESC";

        try (Connection conn = connectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                
                productList.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
        return productList;
    }
    
    /**
     * Adds a new product to the database. 
     * @param product
     * @return true if the product was added successfully, false otherwise.
     */
    public boolean addProduct(Product product) {
        // SQL statement for insertion
        String sql = "INSERT INTO products (code, name, category, price, stock) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the parameters for the PreparedStatement
            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.setInt(5, product.getStock());
            
            // Execute the update and check if a row was affected
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            // Check for duplicate entry specifically
            if (e.getSQLState().equals("23000")) { // SQLSTATE for integrity constraint violation
                javax.swing.JOptionPane.showMessageDialog(null, "Error: A product with this code already exists.", "Duplicate Entry", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    /**
     * Deletes a product from the database based on its code.
     * @param code
     * @return true if deleted successfully.
     */
    public boolean deleteProduct(String code) {
        String sql = "DELETE FROM products WHERE code = ?";
        
        // Menggunakan connectDB sesuai import di atas
        try (Connection conn = connectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, code);
            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
    // Tambahkan ini di ProductDao.java
    public boolean updateProduct(com.mypos.product.model.Product product) {
        String sql = "UPDATE products SET name=?, category=?, price=?, stock=? WHERE code=?";
        try (java.sql.Connection conn = com.mypos.config.connectDB.getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getCategory());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setString(5, product.getCode()); // ID/Code sebagai kunci update
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}