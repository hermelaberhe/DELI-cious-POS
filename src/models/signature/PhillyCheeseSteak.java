package models.signature;

import models.enums.*;
import models.Topping;

public class PhillyCheeseSteak extends SignatureSandwich {

    public PhillyCheeseSteak() {
        super("Philly Cheesesteak", BreadType.WHITE, SandwichSize.MEDIUM_8, true);
    }

    @Override
    protected void loadSignatureToppings() {
        addTopping(new Topping("STEAK", ToppingCategory.MEAT, false));
        addTopping(new Topping("AMERICAN", ToppingCategory.CHEESE, false));
        addTopping(new Topping("PEPPERS", ToppingCategory.REGULAR, false));
        addTopping(new Topping("MAYO", ToppingCategory.SAUCE, false));
    }

    public void addTopping(Topping steak) {
    }
}

