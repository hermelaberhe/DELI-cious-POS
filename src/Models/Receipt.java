package src.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receipt {
    private final String timestamp;
    private final List<Sandwich> sandwiches;
    private final List<Drink> drinks;
    private final List<Chip> chips;
    private final double total;

    public Receipt(List<Sandwich> sandwiches, List<Drink> drinks, List<Chip> chips, double total) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        this.sandwiches = sandwiches;
        this.drinks = drinks;
        this.chips = chips;
        this.total = total;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chip> getChips() {
        return chips;
    }

    public double getTotal() {
        return total;
    }

    public String generateTextReceipt() {
        StringBuilder builder = new StringBuilder();
        builder.append("====== DELI-cious Receipt ======\n\n");

        for (int i = 0; i < sandwiches.size(); i++) {
            builder.append("Sandwich ").append(i + 1).append(":\n").append(sandwiches.get(i)).append("\n\n");
        }

        for (int i = 0; i < drinks.size(); i++) {
            builder.append("Drink ").append(i + 1).append(": ").append(drinks.get(i)).append("\n");
        }

        for (int i = 0; i < chips.size(); i++) {
            builder.append("Chip ").append(i + 1).append(": ").append(chips.get(i)).append("\n");
        }

        builder.append(String.format("\nTotal: $%.2f\n", total));
        builder.append("\n🙏 Thank you for dining with us!\n");
        return builder.toString();
    }
}
