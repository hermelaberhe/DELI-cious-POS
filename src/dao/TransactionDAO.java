package src.dao;

import src.database.DatabaseConnector;
import src.models.*;
//import src.services.PriceCalculator;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void saveOrder(Order order, String paymentMethod) {
        String insertTransaction = "INSERT INTO Transactions (customer_name, payment_method, total, timestamp) VALUES (?, ?, ?, ?)";
        String insertItem = "INSERT INTO OrderItems (transaction_id, item_type, description) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement transactionStmt = conn.prepareStatement(insertTransaction, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement itemStmt = conn.prepareStatement(insertItem)) {

            conn.setAutoCommit(false);

            transactionStmt.setString(1, "Customer");
            transactionStmt.setString(2, paymentMethod);
            //transactionStmt.setDouble(3, PriceCalculator.calculateOrderTotal(order)); // ✅ Working now
            transactionStmt.setString(4, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            transactionStmt.executeUpdate();

            ResultSet keys = transactionStmt.getGeneratedKeys();
            int transactionId = keys.next() ? keys.getInt(1) : -1;

            for (Sandwich s : order.getSandwiches()) {
                itemStmt.setInt(1, transactionId);
                itemStmt.setString(2, "Sandwich");
                itemStmt.setString(3, s.toString());
                itemStmt.addBatch();
            }

            for (Drink d : order.getDrinks()) {
                itemStmt.setInt(1, transactionId);
                itemStmt.setString(2, "Drink");
                itemStmt.setString(3, d.toString());
                itemStmt.addBatch();
            }

            for (Chip c : order.getChips()) {
                itemStmt.setInt(1, transactionId);
                itemStmt.setString(2, "Chip");
                itemStmt.setString(3, c.toString());
                itemStmt.addBatch();
            }

            itemStmt.executeBatch();
            conn.commit();
            System.out.println("✅ Order saved to database!");

        } catch (Exception e) {
            System.out.println("❌ Failed to save transaction: " + e.getMessage());
        }
    }

    public List<OrderTransaction> getAllTransactions() {
        List<OrderTransaction> results = new ArrayList<>();
        String queryTransactions = "SELECT * FROM Transactions ORDER BY id DESC";
        String queryItems = "SELECT * FROM OrderItems WHERE transaction_id = ?";

        try (Connection conn = DatabaseConnector.connect();
             Statement txStmt = conn.createStatement();
             ResultSet txRs = txStmt.executeQuery(queryTransactions)) {

            while (txRs.next()) {
                int id = txRs.getInt("id");
                double total = txRs.getDouble("total");
                String payment = txRs.getString("payment_method");
                String timestamp = txRs.getString("timestamp");

                List<String> items = new ArrayList<>();
                try (PreparedStatement itemStmt = conn.prepareStatement(queryItems)) {
                    itemStmt.setInt(1, id);
                    ResultSet itemRs = itemStmt.executeQuery();

                    while (itemRs.next()) {
                        items.add(itemRs.getString("description"));
                    }
                }

                results.add(new OrderTransaction(id, total, payment, timestamp, items));
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch transactions: " + e.getMessage());
        }

        return results;
    }
}
