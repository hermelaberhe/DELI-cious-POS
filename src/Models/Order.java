package src.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chip> chips;

    private String paymentMethod;
    private String timestamp;
    private double totalPrice;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChip(Chip chip) {
        chips.add(chip);
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

    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n🧾 Order placed on ").append(timestamp);
        sb.append("\n📦 Payment method: ").append(paymentMethod);
        sb.append("\n💰 Total: $").append(String.format("%.2f", totalPrice));

        if (!sandwiches.isEmpty()) {
            int count = 1;
            sb.append("\n\n🥪 Sandwiches:");
            for (Sandwich s : sandwiches) {
                sb.append("\n").append(count++).append(") ").append(s.toString());
            }
        }

        if (!drinks.isEmpty()) {
            int count = 1;
            sb.append("\n\n🥤 Drinks:");
            for (Drink d : drinks) {
                sb.append("\n").append(count++).append(") ").append(d.toString());
            }
        }

        if (!chips.isEmpty()) {
            int count = 1;
            sb.append("\n\n🍟 Chips:");
            for (Chip c : chips) {
                sb.append("\n").append(count++).append(") ").append(c.toString());
            }
        }

        return sb.toString();
    }
}
