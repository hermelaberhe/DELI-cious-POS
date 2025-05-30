// src/util/OrderStorage.java

package src.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import src.models.Order;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    private static final String RECEIPT_DIR = "receipts";

    public static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        File dir = new File(RECEIPT_DIR);

        if (!dir.exists()) return orders;

        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files == null) return orders;

        ObjectMapper mapper = new ObjectMapper();
        for (File file : files) {
            try {
                String content = Files.readString(file.toPath());
                Order order = mapper.readValue(content, Order.class);
                orders.add(order);
            } catch (Exception e) {
                System.err.println("❌ Failed to load " + file.getName() + ": " + e.getMessage());
            }
        }
        return orders;
    }

    public static List<File> listReceiptFiles() {
        File folder = new File(RECEIPT_DIR);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        List<File> result = new ArrayList<>();
        if (files != null) {
            for (File f : files) result.add(f);
        }
        return result;
    }

    public static void saveOrder(Order order) {
        try {
            File dir = new File(RECEIPT_DIR);
            if (!dir.exists()) dir.mkdir();

            File outFile = new File(dir, order.getTimestamp() + ".txt");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(outFile, order);
        } catch (Exception e) {
            System.err.println("❌ Failed to save order: " + e.getMessage());
        }
    }
}
