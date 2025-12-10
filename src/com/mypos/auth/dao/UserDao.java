package com.mypos.auth.dao;

import com.mypos.auth.model.User;
import com.mypos.config.connectDB;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all CRUD and authentication database operations for the User entity.
 */
public class UserDao {
    
    // --- AUTHENTICATION ---
    public User authenticate(String username, String rawPassword) {
        String hashedPassword = hashPassword(rawPassword);
        if (hashedPassword == null) return null;

        String sql = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Authentication DAO error: " + e.getMessage());
        }
        return null;
    }

    // --- CRUD OPERATIONS ---

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, username, role, created_at, updated_at FROM users ORDER BY created_at DESC";
        try (Connection conn = connectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
        return userList;
    }

    public boolean addUser(User user) {
        // We use MySQL's SHA2 function for hashing during insertion.
        String sql = "INSERT INTO users (username, password, role) VALUES (?, SHA2(?, 256), ?)";
        try (Connection conn = connectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        // Conditionally build the SQL query based on whether a new password is provided.
        StringBuilder sql = new StringBuilder("UPDATE users SET username = ?, role = ?");
        boolean passwordProvided = user.getPassword() != null && !user.getPassword().isEmpty();
        
        if (passwordProvided) {
            sql.append(", password = SHA2(?, 256)");
        }
        sql.append(" WHERE id = ?");

        try (Connection conn = connectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getRole());
            if (passwordProvided) {
                pstmt.setString(3, user.getPassword());
                pstmt.setInt(4, user.getId());
            } else {
                pstmt.setInt(3, user.getId());
            }
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = connectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // --- UTILITY ---
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}