package src.models;

import src.models.enums.ToppingType;

public class Topping {
    private String name;
    private ToppingType type;
    private boolean extra;

    public Topping(String name, ToppingType type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return (extra ? "Extra " : "") + name;
    }
}
