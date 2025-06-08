import gui.HomeScreen;
import utils.DatabaseManager;

import gui.SalesSummary;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("===============================");
        System.out.println("🥪 DELI-cious POS – Report Mode");
        System.out.println("===============================");
        SalesSummary.printSummary(); // 👈 auto runs summary
    }
}

