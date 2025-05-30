package src.models;

public class Chip {
    private final String flavor;

    public Chip(String flavor) {
        this.flavor = flavor;
    }

    public double getPrice() {
        return 1.5;
    }

    @Override
    public String toString() {
        return flavor + " 🍟";
    }

}

