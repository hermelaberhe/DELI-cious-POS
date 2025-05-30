package src.dao;

import src.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public List<String> getAllItems() {
        List<String> items = new ArrayList<>();

        String sql = "SELECT * FROM Inventory";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                items.add(name + " (" + type + ") - $" + price);
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading inventory: " + e.getMessage());
        }
        return items;
    }
}
