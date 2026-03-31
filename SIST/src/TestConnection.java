
// TestConnection.java
// Purpose: verify the MariaDB JDBC driver loads correctly

public class TestConnection {
    public static void main(String[] args) {
        try {
            // Attempt to load the MariaDB JDBC driver from the JAR
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("✅ Driver loaded successfully!");

        } catch (ClassNotFoundException e) {
            // JAR not linked correctly — revisit Step 4b
            System.out.println("❌ Driver NOT found: " + e.getMessage());
        }
    }
}