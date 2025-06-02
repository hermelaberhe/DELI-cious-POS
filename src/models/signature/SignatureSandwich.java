package models.signature;

import models.Sandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;

public abstract class SignatureSandwich extends Sandwich {
    public SignatureSandwich(String name, BreadType breadType, SandwichSize size, boolean toasted) {
        super(breadType, size, toasted);
        setName(name);
        loadSignatureToppings(); // 👈 This loads the default toppings
    }

    protected abstract void loadSignatureToppings();  // Child classes must define
}
