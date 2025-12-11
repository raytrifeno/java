package com.mypos.sales.dao;

import com.mypos.config.connectDB;
import com.mypos.sales.model.Sale; 
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all database operations for retrieving sales history.
 */
public class SalesDao {

    /**
     * Retrieves sales records from the database based on a time interval, joining
     * with the users table to get the cashier's name.
     * 
     * @param interval The time period to filter by ("Today", "Weekly", "Monthly", "All Time").
     * @return A list of Sale objects.
     */
    public List<Sale> getSalesByInterval(String interval) {
        List<Sale> salesList = new ArrayList<>();
        
        // Base SQL query with LEFT JOIN to ensure sales are shown even if user is deleted
        StringBuilder sql = new StringBuilder(
            "SELECT s.id, s.receipt_number, s.sale_date, s.total_amount, s.amount_paid, s.change_amount, s.user_id, u.username " +
            "FROM sales s " +
            "LEFT JOIN users u ON s.user_id = u.id "
        );

        // Append WHERE clause based on the selected interval
        switch (interval) {
            case "Today" -> sql.append("WHERE DATE(s.sale_date) = CURDATE() ");
            case "Weekly" -> sql.append("WHERE s.sale_date >= CURDATE() - INTERVAL 7 DAY ");
            case "Monthly" -> sql.append("WHERE YEAR(s.sale_date) = YEAR(CURDATE()) AND MONTH(s.sale_date) = MONTH(CURDATE()) ");
            // "All Time" does not need a WHERE clause
            // "All Time" does not need a WHERE clause
        }
        
        sql.append("ORDER BY s.sale_date DESC");

        try (Connection conn = connectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString());
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Sale sale = new Sale();
                
                // --- CRUCIAL DEBUGGING LOGIC ---
                int saleId = rs.getInt("id");
                int userIdInSales = rs.getInt("user_id");
                String foundName = rs.getString("username");
                sale.setId(saleId);
                sale.setReceiptNumber(rs.getString("receipt_number"));
                if (rs.getTimestamp("sale_date") != null) {
                    sale.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
                }
                
                sale.setTotalAmount(rs.getBigDecimal("total_amount"));
                sale.setAmountPaid(rs.getBigDecimal("amount_paid"));
                sale.setChangeAmount(rs.getBigDecimal("change_amount"));
                
                // Set the cashier name, with a fallback for deleted users
                if (foundName != null) {
                    sale.setCashierName(foundName);
                } else {
                    sale.setCashierName("Unknown (ID: " + userIdInSales + ")");
                }
                
                salesList.add(sale);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching sales history: " + e.getMessage());
            e.printStackTrace();
        }
        return salesList;
    }
}
