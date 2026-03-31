
// TestConnection.java
public class TestConnection {
    public static void main(String[] args) {
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("✅ Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver NOT found: " + e.getMessage());
        }
    }
}