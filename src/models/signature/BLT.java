package models.signature;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.ToppingCategory;
import models.Topping;

public class BLT extends models.signature.SignatureSandwich {

    public BLT() {

        super("BLT", BreadType.WHITE, SandwichSize.MEDIUM_8, true);
    }

    @Override
    protected void loadSignatureToppings() {
        addTopping(new Topping("BACON", ToppingCategory.MEAT, false));
        addTopping(new Topping("CHEDDAR", ToppingCategory.CHEESE, false));
        addTopping(new Topping("LETTUCE", ToppingCategory.REGULAR, false));
        addTopping(new Topping("TOMATOES", ToppingCategory.REGULAR, false));
        addTopping(new Topping("RANCH", ToppingCategory.SAUCE, false));
    }


}
