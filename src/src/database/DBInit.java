package src.database;

import java.sql.Connection;
import java.sql.Statement;
import src.database.DBUtil;


public class DBInit {

    public static void initializeTables() {
        try (Connection conn = DBUtil.connect(); Statement stmt = conn.createStatement()) {
            // Inventory Table
            String inventoryTable = """
                CREATE TABLE IF NOT EXISTS inventory (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT NOT NULL,
                    is_extra INTEGER DEFAULT 0,
                    price REAL NOT NULL
                );
                """;

            // Transactions Table
            String transactionsTable = """
                CREATE TABLE IF NOT EXISTS transactions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    customer_name TEXT,
                    payment_method TEXT,
                    total REAL,
                    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
                );
                """;

            // Order Items Table
            String orderItemsTable = """
                CREATE TABLE IF NOT EXISTS order_items (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    transaction_id INTEGER,
                    item_type TEXT NOT NULL,
                    description TEXT NOT NULL,
                    FOREIGN KEY(transaction_id) REFERENCES transactions(id)
                );
                """;

            stmt.execute(inventoryTable);
            stmt.execute(transactionsTable);
            stmt.execute(orderItemsTable);

            System.out.println("✅ All tables initialized!");
        } catch (Exception e) {
            System.out.println("❌ Failed to initialize tables: " + e.getMessage());
        }
    }
}
