package models.enums;

public enum SandwichSize {
    SMALL_4("Small (4\")", 4.00),
    MEDIUM_8("Medium (8\")", 6.50),
    LARGE_12("Large (12\")", 8.00);

    private final String displayName;
    private final double basePrice;

    SandwichSize(String displayName, double basePrice) {
        this.displayName = displayName;
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getDisplayName() {
        return displayName;
    }
}
