package src.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlite:deli.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            System.out.println("❌ Failed to connect to database: " + e.getMessage());
            return null;
        }
    }
}
