package src.models;

import src.models.enums.BreadType;
import src.models.enums.SandwichSize;
import src.models.enums.ToppingType;

public class SignatureSandwich extends Sandwich {

    public SignatureSandwich(String type) {
        super(BreadType.WHITE, SandwichSize.MEDIUM_8, true);

        switch (type.toLowerCase()) {
            case "blt" -> {
                setName("BLT");
                addTopping(new src.models.Topping("bacon", ToppingType.MEAT, false));
                addTopping(new Topping("cheddar", ToppingType.CHEESE, false));
                addTopping(new Topping("lettuce", ToppingType.REGULAR, false));
                addTopping(new Topping("tomato", ToppingType.REGULAR, false));
                addTopping(new Topping("ranch", ToppingType.SAUCE, false));
            }
            case "philly" -> {
                setName("Philly Cheese Steak");
                addTopping(new Topping("steak", ToppingType.MEAT, false));
                addTopping(new Topping("american", ToppingType.CHEESE, false));
                addTopping(new Topping("peppers", ToppingType.REGULAR, false));
                addTopping(new src.models.Topping("mayo", ToppingType.SAUCE, false));
            }
            default -> throw new IllegalArgumentException("Unknown signature sandwich: " + type);
        }
    }
}
