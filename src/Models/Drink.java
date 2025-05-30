package src.models;

import src.models.enums.DrinkSize;

public class Drink {
    private final DrinkSize size;
    private final String flavor;


    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }
    public DrinkSize getSize() {
        return size;
    }

    public double getPrice() {
        return switch (size) {
            case SMALL -> 1.5;
            case MEDIUM -> 2.0;
            case LARGE -> 2.5;
        };
    }

    @Override
    public String toString() {
        return size + " " + flavor + " 🥤";
    }

}

