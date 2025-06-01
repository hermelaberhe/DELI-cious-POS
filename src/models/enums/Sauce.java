package models.enums;

public enum Sauce {
    MAYO(90, 0, 0),
    MUSTARD(10, 0, 1),
    KETCHUP(20, 0, 5),
    RANCH(100, 1, 2),
    THOUSAND_ISLANDS(110, 1, 4),
    VINAIGRETTE(60, 0, 3);

    private final int calories;
    private final int protein;
    private final int carbs;

    Sauce(int calories, int protein, int carbs) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
}
