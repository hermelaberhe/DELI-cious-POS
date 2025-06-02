package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseManager;

public class ViewPastOrders {

    public void show() {
        System.out.println("\n===============================");
        System.out.println("📜 Viewing All Past Orders 📜");
        System.out.println("===============================\n");

        String sql = "SELECT id, timestamp, total FROM orders ORDER BY id DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int count = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String timestamp = rs.getString("timestamp");
                double total = rs.getDouble("total");

                System.out.printf("🧾 Order #%d | %s | Total: $%.2f%n", id, timestamp, total);
                count++;
            }

            if (count == 0) {
                System.out.println("📭 No orders found.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving orders: " + e.getMessage());
        }
    }
}