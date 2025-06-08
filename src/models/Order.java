package models;

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

    public List<Chip> getChips() {
        return chips;
    }
    public List<Sandwich> getSandwich() {
        return sandwiches;
    }

    public List<Drink> getDrink() {
        return drinks;
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

    public double calculateSubtotal() {
        double sandwichTotal = sandwiches.stream().mapToDouble(Sandwich::calculatePrice).sum();
        double drinkTotal = drinks.stream().mapToDouble(Drink::getPrice).sum();
        double chipTotal = chips.stream().mapToDouble(Chip::getPrice).sum();
        return sandwichTotal + drinkTotal + chipTotal;
    }

    public String generateSummary() {
        StringBuilder sb = new StringBuilder("🧾 Order Summary:\n");

        if (!sandwiches.isEmpty()) {
            sb.append("\n🥪 Sandwiches:\n");
            for (Sandwich s : sandwiches) {
                sb.append(" - ").append(s.toReceiptString()).append("\n");
            }
        }

        if (!drinks.isEmpty()) {
            sb.append("\n🥤 Drinks:\n");
            for (Drink d : drinks) {
                sb.append(" - ").append(d.toString()).append("\n");
            }
        }

        if (!chips.isEmpty()) {
            sb.append("\n🍟 Chips:\n");
            for (Chip c : chips) {
                sb.append(" - ").append(c.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public String generateReceipt(double total, double tax) {
        return generateSummary() +
                String.format("\nSubtotal: $%.2f\nTax: $%.2f\nTotal: $%.2f", total - tax, tax, total);
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

    public List<Drink> getDrinks() {
        return drinks;
    }
}