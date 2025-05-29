package src.models;

import src.models.enums.DrinkSize;

public class Drink {
    private DrinkSize size;
    private String flavor;

    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return size + " drink - " + flavor;
    }
}
