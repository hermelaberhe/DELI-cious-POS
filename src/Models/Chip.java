package src.models;

public class Chip {
    private String flavor;

    public Chip(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return "Chip - " + flavor;
    }
}
