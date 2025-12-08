//package config;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import javax.swing.JOptionPane;
//
///**
// * This class handles the connection to the MySQL database.
// */
//public class connectDB {
//
//    // This will hold the single instance of our database connection.
//    private static Connection mysqlconfig;
//
//    /**
//     * This method creates and returns a connection to the database.
//     * It uses the Singleton pattern to ensure only one connection is made.
//     * @return Connection to the database.
//     */
//    public static Connection getConnection() {
//        // Check if a connection hasn't been made yet.
//        if (mysqlconfig == null) {
//            try {
//                // Database URL, including the server, port, and database name.
//                // Replace "pbo_tr" with your actual database name.
//                String url = "jdbc:mysql://localhost:3306/pos_tr"; 
//                
//                // Your database username. "root" is common for local development.
//                String user = "root"; 
//                
//                // Your database password. Leave blank if you don't have one.
//                String password = ""; 
//                
//                // This line tells Java to use the MySQL driver we added.
//                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//                
//                // Try to connect to the database with the details above.
//                mysqlconfig = DriverManager.getConnection(url, user, password);
//                
//                // If you want to see a success message, you can uncomment the line below.
//                // JOptionPane.showMessageDialog(null, "Database connection successful!");
//
//            } catch (SQLException e) {
//                // If something goes wrong, show an error message with details.
//                JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage());
//            }
//        }
//        // Return the connection (either the new one or the existing one).
//        return mysqlconfig;
//    }
//}
