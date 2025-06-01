package models.signature;

import models.*;
import models.enums.*;


public abstract class SignatureSandwich extends Sandwich {
    public SignatureSandwich(String name, BreadType bread, SandwichSize size, boolean toasted) {
        super(bread, size, toasted);
        setName(name);
        loadSignatureToppings();
    }

    public void setName(String name) {
    }

    protected abstract void loadSignatureToppings();  // Child classes must define
}
