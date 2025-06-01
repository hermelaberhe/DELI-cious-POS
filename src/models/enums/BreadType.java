package models.enums;

public enum BreadType {
    WHITE("White Bread", 150, 5, 28),
    WHEAT("Wheat Bread", 140, 6, 24),
    RYE("Rye Bread", 160, 4, 30),
    WRAP("Wrap", 170, 7, 32);

    private final String displayName;
    private final int calories;
    private final int protein;
    private final int carbs;

    BreadType(String displayName, int calories, int protein, int carbs) {
        this.displayName = displayName;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    public String getDisplayName() { return displayName; }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
}
