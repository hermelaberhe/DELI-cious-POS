package utils;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:deli.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Orders table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    timestamp TEXT,
                    total REAL
                );
            """);

            // Sandwiches table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS sandwiches (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    order_id INTEGER,
                    name TEXT,
                    FOREIGN KEY(order_id) REFERENCES orders(id)
                );
            """);

            System.out.println("✅ SQLite database initialized: deli.db");
        } catch (SQLException e) {
            System.out.println("❌ DB init failed: " + e.getMessage());
        }
    }

    public static int saveOrderToDatabase(String timestamp, double total) {
        String sql = "INSERT INTO orders (timestamp, total) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, timestamp);
            pstmt.setDouble(2, total);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  // return generated order ID
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to save order: " + e.getMessage());
        }
        return -1;
    }

    public static void saveSandwich(int orderId, String name) {

    }

    public static void saveDrink(int orderId, String displayName, String name) {

    }

    public static void saveChip(int orderId, String name) {

    }
}
