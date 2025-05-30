package src.models;

import src.models.enums.BreadType;
import src.models.enums.SandwichSize;
import java.util.ArrayList;
import java.util.List;


public class Sandwich {
    private final BreadType breadType;
    private final SandwichSize size;
    private final boolean toasted;
    protected final List<Topping> toppings = new ArrayList<>();

    public Sandwich(BreadType breadType, SandwichSize size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
    }

    public SandwichSize getSize() {
        return size;
    }


    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public double calculatePrice() {
        double base = switch (size) {
            case SMALL_4 -> 5.0;
            case MEDIUM_8 -> 7.0;
            case LARGE_12 -> 9.0;
        };

        double toppingsTotal = toppings.stream().mapToDouble(Topping::getPrice).sum();
        return base + toppingsTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" sandwich on ").append(breadType);
        sb.append(toasted ? " (Toasted)" : " (Not Toasted)");
        if (!toppings.isEmpty()) {
            sb.append("\n  🥗 Toppings:");
            for (src.models.Topping t : toppings) {
                sb.append("\n   - ").append(t);
            }
        } else {
            sb.append("\n  No toppings added.");
        }
        return sb.toString();
    }
}
