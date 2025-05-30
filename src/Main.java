package src;

import src.services.OrderManager;

public class Main {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();
        manager.run(); // Launch the CLI
    }
}
