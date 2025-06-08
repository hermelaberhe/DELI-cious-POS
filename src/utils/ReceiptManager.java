package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {
    public static String saveReceipt(String content) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String filename = "receipts/" + timestamp + ".txt";

            FileWriter writer = new FileWriter(filename);
            writer.write(content);
            writer.close();

            return filename;
        } catch (IOException e) {
            return "❌ Error saving receipt: " + e.getMessage();
        }
    }

    public static void emailReceipt(String toEmail, String content) {
        EmailSender.sendEmail(toEmail, "Your DELI-cious Receipt 🧾", content);
    }

}

