package models.enums;

public enum DrinkType {
    COKE("Coke", 150, 0, 39),
    DIET_COKE("Diet Coke", 0, 0, 0),
    LEMONADE("Lemonade", 120, 0, 31),
    ICED_TEA("Iced Tea", 80, 0, 21),
    WATER("Water", 0, 0, 0);

    private final String displayName;
    private final int calories;
    private final int protein;
    private final int carbs;

    DrinkType(String displayName, int calories, int protein, int carbs) {
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
