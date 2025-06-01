package models.enums;

public enum Cheese {
    AMERICAN(100, 5, 1),
    PROVOLONE(110, 7, 0),
    CHEDDAR(120, 6, 1),
    SWISS(90, 8, 1);

    private final int calories;
    private final int protein;
    private final int carbs;

    Cheese(int calories, int protein, int carbs) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
}
