package models.enums;

public enum RegularTopping {
    LETTUCE(5, 0, 1),
    PEPPERS(10, 0, 2),
    ONIONS(15, 0, 3),
    TOMATOES(20, 1, 4),
    JALAPENOS(5, 0, 1),
    CUCUMBERS(8, 0, 2),
    PICKLES(10, 0, 1),
    GUACAMOLE(90, 2, 5),
    MUSHROOMS(20, 1, 3);

    private final int calories;
    private final int protein;
    private final int carbs;

    RegularTopping(int calories, int protein, int carbs) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
    }

    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
}
