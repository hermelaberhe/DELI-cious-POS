package models;

import models.signature.*;
import models.enums.BreadType;
import models.enums.SandwichSize;


import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String name = "Custom Sandwich";
    private BreadType breadType;
    private SandwichSize size;
    private boolean toasted;
    private List<models.Topping> toppings = new ArrayList<>();

    public Sandwich() {} // For JSON or framework use

    public Sandwich(BreadType breadType, SandwichSize size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public SandwichSize getSize() {
        return size;
    }

    public void setSize(SandwichSize size) {
        this.size = size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<models.Topping> getToppings() {
        return toppings;
    }

    public void addTopping(models.Topping topping) {
        this.toppings.add(topping);
    }
    public void removeTopping(int index) {
        if (index >= 0 && index < toppings.size()) {
            toppings.remove(index);
        }
    }


    public double calculatePrice() {
        double price = size.getBasePrice();
        for (models.Topping topping : toppings) {
            price += topping.getPriceForSize(size);
        }
        return price;
    }

    public String toReceiptString() {
        StringBuilder sb = new StringBuilder();
        for (models.Topping topping : toppings) {
            sb.append("   > ")
                    .append(topping.getName())
                    .append(topping.isExtra() ? " (extra)" : "")
                    .append("\n");
        }

        return sb.toString();
    }
}
