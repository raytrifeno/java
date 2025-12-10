package com.mypos.cashier.dao;

import com.mypos.cashier.model.CartItem;
import com.mypos.config.connectDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TransactionDao {

    public boolean saveTransaction(int userId, BigDecimal grandTotal, BigDecimal paid, BigDecimal change, List<CartItem> items) {
        Connection conn = null;
        try {
            conn = connectDB.getConnection();
            conn.setAutoCommit(false); // Start Transaction

            // STEP 1: Insert into 'sales' table
            String receiptNumber = "TRX-" + System.currentTimeMillis();
            String sqlSale = "INSERT INTO sales (receipt_number, total_amount, amount_paid, change_amount, user_id, sale_date) VALUES (?, ?, ?, ?, ?, NOW())";
            long saleId = 0;
            try (PreparedStatement pstmtSale = conn.prepareStatement(sqlSale, Statement.RETURN_GENERATED_KEYS)) {
                pstmtSale.setString(1, receiptNumber);
                pstmtSale.setBigDecimal(2, grandTotal);
                pstmtSale.setBigDecimal(3, paid);
                pstmtSale.setBigDecimal(4, change);
                pstmtSale.setInt(5, userId);
                if (pstmtSale.executeUpdate() == 0) throw new SQLException("Creating sale failed, no rows affected.");

                try (ResultSet generatedKeys = pstmtSale.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        saleId = generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating sale failed, no ID obtained.");
                    }
                }
            }

            // STEP 2: Insert into 'sale_items' table
            String sqlSaleItems = "INSERT INTO sale_items (sale_id, product_id, product_name, price, quantity, subtotal) " +
                                  "VALUES (?, (SELECT id FROM products WHERE code = ? LIMIT 1), ?, ?, ?, ?)";
            try (PreparedStatement pstmtItems = conn.prepareStatement(sqlSaleItems)) {
                for (CartItem item : items) {
                    pstmtItems.setLong(1, saleId);
                    pstmtItems.setString(2, item.getProductCode());
                    pstmtItems.setString(3, item.getProductName());
                    pstmtItems.setBigDecimal(4, item.getPrice());
                    pstmtItems.setInt(5, item.getQuantity());
                    pstmtItems.setBigDecimal(6, item.getSubtotal());
                    pstmtItems.addBatch();
                }
                pstmtItems.executeBatch();
            }

            // STEP 3: Update stock in 'products' table with debugging
            System.out.println("--- Starting Stock Update ---");
            String sqlUpdateStock = "UPDATE products SET stock = stock - ? WHERE code = ?";
            try (PreparedStatement pstmtStock = conn.prepareStatement(sqlUpdateStock)) {
                for (CartItem item : items) {
                    System.out.println("DEBUG: Preparing to update stock for Code: '" + item.getProductCode() + "', Quantity: " + item.getQuantity());
                    pstmtStock.setInt(1, item.getQuantity());
                    pstmtStock.setString(2, item.getProductCode());
                    pstmtStock.addBatch();
                }
                
                int[] updateCounts = pstmtStock.executeBatch();
                System.out.println("DEBUG: Stock update batch executed. Row update counts: " + java.util.Arrays.toString(updateCounts));

                for (int count : updateCounts) {
                    if (count == 0) {
                        // This indicates a product code in the cart did not match any row in the products table.
                        throw new SQLException("Stock update failed for an item. Product code might not exist or stock update was not successful.");
                    }
                }
            }

            // If all steps succeed, commit the transaction
            conn.commit();
            System.out.println("--- Transaction Committed Successfully ---");
            return true;

        } catch (SQLException e) {
            System.err.println("Transaction failed. Rolling back... Error: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.err.println("--- Transaction Rolled Back ---");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}