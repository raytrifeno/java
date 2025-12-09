package com.mypos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * A utility class to handle the connection to the MySQL database.
 * It uses a static method to ensure a single connection instance is reused.
 */
public class connectDB {

    // This single Connection object will be shared across the application.
    private static Connection mysqlconfig;

    /**
     * Gets the active database connection. If a connection does not exist,
     * it creates one.
     * 
     * @return The active database Connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        // Check if the connection has not been created yet.
        if (mysqlconfig == null) {
            try {
                // Database connection details for XAMPP
                String url = "jdbc:mysql://localhost:3306/pos_tr"; 
                String user = "root";
                String password = ""; // Default XAMPP password is empty
                
                // Register the MySQL driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                // Establish the connection
                mysqlconfig = DriverManager.getConnection(url, user, password);
                
            } catch (SQLException e) {
                // If connection fails, show an error and re-throw the exception
                // so the calling code knows something went wrong.
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
