package src.models;

import java.util.List;

public class OrderTransaction {
    private int id;
    private double totalAmount;
    private String paymentMethod;
    private String timestamp;
    private List<String> itemDescriptions;

    public OrderTransaction(int id, double totalAmount, String paymentMethod, String timestamp, List<String> itemDescriptions) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.timestamp = timestamp;
        this.itemDescriptions = itemDescriptions;
    }

    public int getId() { return id; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getTimestamp() { return timestamp; }
    public List<String> getItemDescriptions() { return itemDescriptions; }
}
