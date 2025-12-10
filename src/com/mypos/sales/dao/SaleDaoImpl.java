/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mypos.sales.dao;

/**
 *
 * @author HP
 */

import com.mypos.sales.model.Sale;
import com.mypos.sales.model.SalesSummary;
import com.mypos.config.connectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class SaleDaoImpl implements SaleDao {

    private Sale mapRow(ResultSet rs) throws SQLException {
        Sale s = new Sale();
        s.setId(rs.getInt("id"));
        s.setReceiptNumber(rs.getString("receipt_number"));
        Timestamp ts = rs.getTimestamp("sale_date");
        if (ts != null) s.setSaleDate(ts.toLocalDateTime());
        s.setTotalAmount(rs.getBigDecimal("total_amount") == null ? BigDecimal.ZERO : rs.getBigDecimal("total_amount"));
        s.setAmountPaid(rs.getBigDecimal("amount_paid") == null ? BigDecimal.ZERO : rs.getBigDecimal("amount_paid"));
        s.setChangeAmount(rs.getBigDecimal("change_amount") == null ? BigDecimal.ZERO : rs.getBigDecimal("change_amount"));
        s.setUserId(rs.getInt("user_id"));
        return s;
    }

    @Override
    public List<Sale> findAll() throws Exception {
        String sql = "SELECT * FROM sales ORDER BY sale_date ASC";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Sale> list = new ArrayList<>();
            while (rs.next()) list.add(mapRow(rs));
            return list;
        }
    }

    @Override
    public List<Sale> findBetween(LocalDateTime from, LocalDateTime to) throws Exception {
        String sql = "SELECT * FROM sales WHERE sale_date BETWEEN ? AND ? ORDER BY sale_date ASC";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));
            try (ResultSet rs = ps.executeQuery()) {
                List<Sale> list = new ArrayList<>();
                while (rs.next()) list.add(mapRow(rs));
                return list;
            }
        }
    }

    @Override
    public List<Sale> findToday() throws Exception {
        LocalDate today = LocalDate.now();
        return findBetween(today.atStartOfDay(), LocalDateTime.now());
    }

    @Override
    public List<Sale> findWeekly() throws Exception {
        return findBetween(LocalDate.now().minusDays(6).atStartOfDay(), LocalDateTime.now());
    }

    @Override
    public List<Sale> findMonthly() throws Exception {
        return findBetween(LocalDate.now().withDayOfMonth(1).atStartOfDay(), LocalDateTime.now());
    }

    @Override
    public Sale findByReceipt(String receipt) throws Exception {
        String sql = "SELECT * FROM sales WHERE receipt_number = ?";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, receipt);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        }
    }

    @Override
    public SalesSummary getSummaryBetween(LocalDateTime from, LocalDateTime to) throws Exception {
        String sql = "SELECT COUNT(*) AS total_transactions, " +
                     "COALESCE(SUM(total_amount),0) AS total_amount, " +
                     "COALESCE(SUM(amount_paid),0) AS total_paid, " +
                     "COALESCE(SUM(change_amount),0) AS total_change " +
                     "FROM sales WHERE sale_date BETWEEN ? AND ?";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));
            try (ResultSet rs = ps.executeQuery()) {
                SalesSummary summary = new SalesSummary();
                if (rs.next()) {
                    summary.setTotalTransactions(rs.getInt("total_transactions"));
                    summary.setTotalAmount(rs.getBigDecimal("total_amount") == null ? BigDecimal.ZERO : rs.getBigDecimal("total_amount"));
                    summary.setTotalPaid(rs.getBigDecimal("total_paid") == null ? BigDecimal.ZERO : rs.getBigDecimal("total_paid"));
                    summary.setTotalChange(rs.getBigDecimal("total_change") == null ? BigDecimal.ZERO : rs.getBigDecimal("total_change"));
                }
                return summary;
            }
        }
    }
}

