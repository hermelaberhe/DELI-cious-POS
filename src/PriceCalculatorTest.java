import org.junit.jupiter.api.Test;
import src.models.*;
import src.models.enums.*;
import src.services.PriceCalculator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {

    @Test
    public void testDrinkPrice() {
        Drink drink = new Drink(DrinkSize.MEDIUM, "Sprite");
        assertEquals(2.50, PriceCalculator.calculateDrinkPrice(drink));
    }

    @Test
    public void testChipPrice() {
        Chip chip = new Chip("BBQ");
        assertEquals(1.50, PriceCalculator.calculateChipPrice(chip));
    }

    @Test
    public void testBasicSandwichPrice() {
        Sandwich s = new Sandwich(BreadType.WHITE, SandwichSize.SMALL_4, false);
        assertEquals(5.50, PriceCalculator.calculateSandwichPrice(s));
    }

    @Test
    public void testOrderTotal() {
        Order order = new Order();
        order.addDrink(new Drink(DrinkSize.SMALL, "Cola"));
        order.addChip(new Chip("Sour Cream"));
        assertEquals(3.50, PriceCalculator.calculateOrderTotal(order));
    }
}
