package src.models.enums;

public enum ChipType {
    BBQ("BBQ", 150, 2, 15),
    SOUR_CREAM("Sour Cream & Onion", 160, 2, 14),
    PLAIN("Plain", 140, 2, 13),
    SALT_VINEGAR("Salt & Vinegar", 155, 2, 16),
    CHEDDAR("Cheddar", 170, 3, 17);

    private final String name;
    private final int calories;
    private final int protein;
    private final int carbs;

    ChipType(String name, int calories, int protein, int carbs) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    public String getName() { return name; }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
}
