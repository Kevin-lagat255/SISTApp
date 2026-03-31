// DBConnection.java
// Purpose: establish and return a connection to the sist_db database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL pointing to sist_db on localhost
    private static final String URL      = "jdbc:mariadb://localhost:3306/sist_db";

    // Use the new dedicated user instead of root
    private static final String USER     = "sistuser";
    private static final String PASSWORD = "sist1234";

    // Returns a live connection to sist_db
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Quick test
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("✅ Connected to sist_db successfully!");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}