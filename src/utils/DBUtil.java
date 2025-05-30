package src.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String DB_URL = "jdbc:sqlite:/Users/hermelaberhe/deli.db";;

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            throw new RuntimeException("❌ Database connection failed: " + e.getMessage());
        }
    }
}
