package models.enums;

public enum Meat {
    STEAK(300, 26, 0),
    HAM(250, 22, 2),
    SALAMI(320, 24, 3),
    ROAST_BEEF(280, 25, 1),
    CHICKEN(210, 30, 0),
    BACON(200, 20, 1);

    private final int calories;
    private final int protein;
    private final int carbs;

    Meat(int calories, int protein, int carbs) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
}
