package src.models;

import src.models.enums.*;

public class SignatureSandwich extends Sandwich {

    public SignatureSandwich(String name) {
        super(BreadType.WHITE, SandwichSize.MEDIUM_8, true); // default for all

        switch (name.toLowerCase()) {
            case "blt" -> {
                this.addTopping(new Topping("bacon", ToppingType.MEAT, false));
                this.addTopping(new Topping("cheddar", ToppingType.CHEESE, false));
                this.addTopping(new Topping("lettuce", ToppingType.REGULAR, false));
                this.addTopping(new Topping("tomato", ToppingType.REGULAR, false));
                this.addTopping(new Topping("ranch", ToppingType.SAUCE, false));
            }
            case "philly" -> {
                this.addTopping(new Topping("steak", ToppingType.MEAT, false));
                this.addTopping(new Topping("american", ToppingType.CHEESE, false));
                this.addTopping(new Topping("peppers", ToppingType.REGULAR, false));
                this.addTopping(new Topping("mayo", ToppingType.SAUCE, false));
            }
            default -> throw new IllegalArgumentException("Unknown signature sandwich: " + name);
        }
    }
}
