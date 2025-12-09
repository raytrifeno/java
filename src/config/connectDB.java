package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * This class handles the connection to the MySQL database.
 */
public class connectDB {

    // This will hold the single instance of our database connection.
    private static Connection mysqlconfig;

    /**
     * @return
     */
    public static Connection getConnection() {
        try {
            // PERBAIKAN: Cek apakah null ATAU sudah ditutup (isClosed)
            if (mysqlconfig == null || mysqlconfig.isClosed()) {
                
                // Database URL, including the server, port, and database name.
                String url = "jdbc:mysql://localhost:3306/pos_tr"; 
                
                // Your database username.
                String user = "root"; 
                
                // Your database password.
                String password = ""; 
                
                // Register driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                // Create new connection
                mysqlconfig = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            // If something goes wrong, show an error message with details.
            System.err.println("Koneksi Gagal: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage());
        }
        
        return mysqlconfig;
    }
}
