package src.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.models.Order;

public class HomeScreen {
    private VBox view;

    public HomeScreen(Stage stage) {
        view = new VBox(15);
        view.setPadding(new Insets(30));
        view.setAlignment(Pos.CENTER);

        Button newOrderBtn = new Button("🧾 New Order");
        Button viewOrdersBtn = new Button("📄 View Past Orders");
        Button exportCSVBtn = new Button("📤 Export Orders to CSV");
        Button adminLoginBtn = new Button("🔐 Admin Login");
        Button exitBtn = new Button("🚪 Exit");

        newOrderBtn.setOnAction(e -> {
            Order newOrder = new Order();  // ✅ create a fresh order
            NewOrderScreen orderScreen = new NewOrderScreen(stage, newOrder);
            stage.setScene(new Scene(orderScreen.getView(), 400, 400));
        });

        adminLoginBtn.setOnAction(e -> {
            AdminLoginScreen adminScreen = new AdminLoginScreen(stage);
            stage.setScene(new Scene(adminScreen.getView(), 400, 250));
        });

        exitBtn.setOnAction(e -> stage.close());

        view.getChildren().addAll(newOrderBtn, viewOrdersBtn, exportCSVBtn, adminLoginBtn, exitBtn);
    }

    public VBox getView() {
        return view;
    }
}
