import org.junit.jupiter.api.Test;
import src.models.*;
import src.models.enums.*;
import src.services.PriceCalculator;

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
    public void testSandwichWithToppingsPrice() {
        Sandwich s = new Sandwich(BreadType.WHEAT, SandwichSize.MEDIUM_8, true);
        s.addTopping(new Topping("turkey", ToppingType.MEAT, false)); // +1.00
        s.addTopping(new Topping("cheddar", ToppingType.CHEESE, true)); // +0.90
        s.addTopping(new Topping("lettuce", ToppingType.REGULAR, false)); // +0.00
        s.addTopping(new Topping("ranch", ToppingType.SAUCE, true)); // +0.50
        // Base 7.00 + toppings 1.00 + 0.90 + 0.00 + 0.50 = 9.40
        assertEquals(9.40, PriceCalculator.calculateSandwichPrice(s), 0.001);
    }

    @Test
    public void testOrderTotal() {
        Order order = new Order();
        order.addDrink(new Drink(DrinkSize.SMALL, "Cola"));  // $2.00
        order.addChip(new Chip("Sour Cream"));               // $1.50
        assertEquals(3.50, PriceCalculator.calculateOrderTotal(order));
    }

    @Test
    public void testFullOrderWithSandwich() {
        Sandwich s = new Sandwich(BreadType.WHITE, SandwichSize.LARGE_12, true);
        s.addTopping(new Topping("ham", ToppingType.MEAT, true)); // 1.50
        s.addTopping(new Topping("swiss", ToppingType.CHEESE, false)); // 0.75
        s.addTopping(new Topping("onion", ToppingType.REGULAR, false)); // 0.00

        Order order = new Order();
        order.addSandwich(s); // base 8.50 + 1.50 + 0.75 + 0.00 = 10.75
        order.addDrink(new Drink(DrinkSize.LARGE, "Root Beer")); // 3.00
        order.addChip(new Chip("Jalapeno")); // 1.50

        double expectedTotal = 10.75 + 3.00 + 1.50; // = 15.25
        assertEquals(expectedTotal, PriceCalculator.calculateOrderTotal(order), 0.001);
    }
}
