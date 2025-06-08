package gui;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExportSalesCSV {

    public void show() {
        File folder = new File("receipts/");
        if (!folder.exists() || folder.listFiles() == null) {
            System.out.println("📂 No receipts found to export.");
            return;
        }

        String exportFileName = "sales_export_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportFileName))) {
            writer.write("Order ID,Date,Total Amount,Main Sandwich\n");

            for (File file : Objects.requireNonNull(folder.listFiles())) {
                String orderId = file.getName().replace(".txt", "");
                String date = orderId.replace("-", " ");
                String total = "";
                String mainSandwich = "";

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    boolean sandwichSection = false;

                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Total: $")) {
                            total = line.replaceAll("[^0-9.]", "");
                        }

                        if (line.contains("🥪 Sandwiches:")) {
                            sandwichSection = true;
                        } else if (sandwichSection && line.trim().startsWith("-")) {
                            mainSandwich = line.trim().replace("-", "").trim().split(" ")[0];
                            break;  // just grab the first sandwich
                        }
                    }
                }

                writer.write(String.format("%s,%s,$%s,%s%n", orderId, date, total, mainSandwich));
            }

            System.out.println("📤 Sales exported to CSV: " + exportFileName);
        } catch (IOException e) {
            System.out.println("❌ Failed to export sales: " + e.getMessage());
        }
    }
}
