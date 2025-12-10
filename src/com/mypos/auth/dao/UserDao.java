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

/**
 * Handles all database operations related to the User entity.
 */
public class UserDao {

    /**
     * Authenticates a user by hashing the provided password with SHA-256 and
     * comparing it with the stored hash in the database.
     * 
     * @param username The user's username.
     * @param rawPassword The user's raw, uncashed password.
     * @return A User object if authentication is successful, otherwise null.
     */
    public User authenticate(String username, String rawPassword) {
        // First, hash the raw password using SHA-256
        String hashedPassword = hashPassword(rawPassword);
        if (hashedPassword == null) {
            // Hashing failed, cannot proceed.
            return null; 
        }

        // The SQL query to find a user with the given username and HASHED password
        String sql = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";
        
        // Use try-with-resources to ensure the connection and statement are closed automatically
        try (Connection conn = connectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the parameters for the query
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the query returned a result
                if (rs.next()) {
                    // If a user is found, create a User object and return it
                    User user = new User();
                    // Note: We do not fetch or store the password in the User object for security
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            // Print an error message if something goes wrong with the SQL execution
            System.err.println("Authentication DAO error: " + e.getMessage());
            // In a real application, you would use a dedicated logging framework
        }
        
        // Return null if no user was found or if an error occurred
        return null;
    }

    /**
     * Hashes a password string using the SHA-256 algorithm to match MySQL's SHA2(pass, 256).
     * 
     * @param password The raw password string.
     * @return The SHA-256 hash as a 64-character hex string, or null if the algorithm is not found.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            
            // Pad with leading zeros to ensure the hash is 64 characters long
            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            // This should not happen on a standard Java installation
            System.err.println("Fatal Error: SHA-256 Algorithm not found.");
            throw new RuntimeException(e);
        }
    }
}
