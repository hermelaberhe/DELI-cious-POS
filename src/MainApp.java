import gui.HomeScreen;
import utils.DatabaseManager;

public class MainApp {
    public static void main(String[] args) {
        DatabaseManager.initialize();  // 💽 Set up the database and tables
        new HomeScreen().show();       // 🏠 Launch the CLI POS system
    }
}