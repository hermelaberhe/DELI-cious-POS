package src.models;

import src.models.enums.BreadType;
import src.models.enums.SandwichSize;
import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private BreadType breadType;
    private SandwichSize size;
    private boolean toasted;
    private List<Topping> toppings;

    public Sandwich(BreadType breadType, SandwichSize size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public SandwichSize getSize() {
        return size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" sandwich on ").append(breadType)
                .append(" bread").append(toasted ? " (Toasted)" : " (Not Toasted)").append("\n");

        if (toppings.isEmpty()) {
            sb.append("  No toppings added\n");
        } else {
            sb.append("  Toppings:\n");
            for (Topping t : toppings) {
                sb.append("    - ").append(t).append("\n");
            }
        }

        return sb.toString();
    }
}
