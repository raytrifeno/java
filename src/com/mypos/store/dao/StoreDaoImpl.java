/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mypos.store.dao;

/**
 *
 * @author HP
 */

import com.mypos.store.model.Store;
import com.mypos.config.connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StoreDaoImpl implements StoreDao {

    @Override
    public Store getStoreInfo() throws Exception {
        String sql = "SELECT * FROM stores LIMIT 1";
        try (Connection conn = connectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Store s = new Store();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                return s;
            }
        }
        return null;
    }
}

