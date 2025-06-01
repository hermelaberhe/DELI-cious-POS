package src.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesSummary {

    public void show() {
        File folder = new File("receipts/");
        if (!folder.exists() || folder.listFiles() == null) {
            System.out.println("📂 No receipts found yet.");
            return;
        }

        double totalRevenue = 0.0;
        int totalOrders = 0;
        Map<String, Integer> sandwichFrequency = new HashMap<>();

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean inSandwichSection = false;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("Total: $")) {
                        double total = Double.parseDouble(line.replaceAll("[^0-9.]", ""));
                        totalRevenue += total;
                        totalOrders++;
                    }

                    if (line.startsWith("🥪 Sandwiches:")) {
                        inSandwichSection = true;
                    } else if (inSandwichSection && line.trim().startsWith("-")) {
                        String[] parts = line.trim().split(" ");
                        if (parts.length > 1) {
                            String name = parts[1];
                            sandwichFrequency.put(name, sandwichFrequency.getOrDefault(name, 0) + 1);
                        }
                    } else if (line.isBlank()) {
                        inSandwichSection = false;
                    }
                }

            } catch (IOException e) {
                System.out.println("❌ Error reading receipt: " + file.getName());
            }
        }

        String bestSeller = sandwichFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        // Print the final report
        System.out.println("\n===========================");
        System.out.println("📊 DELI-cious Sales Summary");
        System.out.println("===========================\n");

        System.out.printf("📦 Total Orders: %d%n", totalOrders);
        System.out.printf("💰 Total Revenue: $%.2f%n", totalRevenue);
        System.out.printf("🌟 Best-Selling Sandwich: %s%n", bestSeller);
    }
}
