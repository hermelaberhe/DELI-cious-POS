package src;

import src.services.OrderManager;
import src.database.DBInit;

public class Main {
    public static void main(String[] args) {
        DBInit.initializeTables();       // Create tables if needed
        OrderManager manager = new OrderManager();
        manager.run();                   // Launch the CLI
    }
}
