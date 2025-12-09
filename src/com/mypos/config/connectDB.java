package com.mypos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * A utility class to handle the connection to the MySQL database.
 * It uses a static method to ensure a single connection instance is reused,
 * and creates a new one if the previous connection was closed.
 */
public class connectDB {

    // This single Connection object will be shared across the application.
    private static Connection mysqlconfig;

    /**
     * Gets the active database connection. If a connection does not exist
     * or has been closed, it creates a new one.
     * 
     * @return The active database Connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        // CRITICAL FIX: Check if the connection is null OR if it has been closed.
        if (mysqlconfig == null || mysqlconfig.isClosed()) {
            try {
                // Database connection details
                String url = "jdbc:mysql://localhost:3306/pos_tr"; 
                String user = "root";
                String password = ""; // Default XAMPP/Laragon password
                
                // Register the MySQL driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                // Establish the new connection
                mysqlconfig = DriverManager.getConnection(url, user, password);
                
            } catch (SQLException e) {
                // If the new connection fails, show an error and propagate the exception.
                JOptionPane.showMessageDialog(
                    null, 
                    "Error connecting to database: " + e.getMessage(), 
                    "Database Connection Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                throw e; // Re-throw to signal failure to the caller
            }
        }
        // Return the existing or newly created connection.
        return mysqlconfig;
    }
}