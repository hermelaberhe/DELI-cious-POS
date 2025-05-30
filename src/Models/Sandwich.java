package src.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import src.models.enums.BreadType;
import src.models.enums.SandwichSize;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String name = "Custom Sandwich";
    private BreadType breadType;
    private SandwichSize size;
    private boolean toasted;
    private List<Topping> toppings = new ArrayList<>();

    public Sandwich() {}  // For Jackson

    @JsonCreator
    public Sandwich(
            @JsonProperty("breadType") BreadType breadType,
            @JsonProperty("size") SandwichSize size,
            @JsonProperty("toasted") boolean toasted
    ) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BreadType getBreadType() { return breadType; }
    public void setBreadType(BreadType breadType) { this.breadType = breadType; }

    public SandwichSize getSize() { return size; }
    public void setSize(SandwichSize size) { this.size = size; }

    public boolean isToasted() { return toasted; }
    public void setToasted(boolean toasted) { this.toasted = toasted; }

    public List<Topping> getToppings() { return toppings; }
    public void setToppings(List<Topping> toppings) { this.toppings = toppings; }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculatePrice() {
        double base = switch (size) {
            case SMALL_4 -> 5.50;
            case MEDIUM_8 -> 7.00;
            case LARGE_12 -> 8.50;
        };
        double toppingTotal = toppings.stream().mapToDouble(t -> t.getPrice(size)).sum();
        return base + toppingTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n🍽 ").append(name);
        sb.append("\n").append(size).append(" sandwich on ").append(breadType);
        sb.append(toasted ? " (Toasted)" : " (Not Toasted)");
        if (!toppings.isEmpty()) {
            sb.append("\n  🥗 Toppings:");
            for (Topping t : toppings) {
                sb.append("\n   - ").append(t);
            }
        } else {
            sb.append("\n  No toppings added.");
        }
        sb.append("\n  💵 Price: $").append(String.format("%.2f", calculatePrice()));
        return sb.toString();
    }
}
