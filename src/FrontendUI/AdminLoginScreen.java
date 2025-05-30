package src.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.models.Order;
import src.util.OrderStorage;

import java.util.List;

public class AdminLoginScreen {
    private VBox view;

    public AdminLoginScreen(Stage stage) {
        view = new VBox(15);
        view.setPadding(new Insets(30));
        view.setAlignment(Pos.CENTER);

        Label label = new Label("Enter Admin Password:");
        PasswordField passwordField = new PasswordField();
        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Back");

        loginBtn.setOnAction(e -> {
            if (passwordField.getText().equals("deli123")) {
                showOrderSummaryPopup();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "❌ Incorrect Password", ButtonType.OK);
                alert.showAndWait();
            }
        });

        backBtn.setOnAction(e -> {
            HomeScreen home = new HomeScreen(stage);
            stage.setScene(new Scene(home.getView(), 400, 350));
        });

        view.getChildren().addAll(label, passwordField, loginBtn, backBtn);
    }

    public VBox getView() {
        return view;
    }

    private void showOrderSummaryPopup() {
        List<Order> orders = OrderStorage.loadOrders();

        if (orders.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "📭 No transactions found.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        StringBuilder content = new StringBuilder();
        double totalRevenue = 0;

        for (Order order : orders) {
            content.append("🧾 ").append(order.getTimestamp())
                    .append(" - 💳 ").append(order.getPaymentMethod())
                    .append(" - 💰 $").append(String.format("%.2f", order.getTotalPrice()))
                    .append("\n");

            int count = 1;
            for (var s : order.getSandwiches()) {
                content.append("   🥪 ").append(count++).append(") ").append(s).append("\n");
            }

            count = 1;
            for (var d : order.getDrinks()) {
                content.append("   🥤 ").append(count++).append(") ").append(d).append("\n");
            }

            count = 1;
            for (var c : order.getChips()) {
                content.append("   🍟 ").append(count++).append(") ").append(c).append("\n");
            }

            content.append("------\n");
            totalRevenue += order.getTotalPrice();
        }

        content.append(String.format("\n📈 Total Revenue: $%.2f\n", totalRevenue));

        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Admin Order Summary");
        alert.getDialogPane().setContent(textArea);
        alert.getDialogPane().setPrefWidth(500);
        alert.getDialogPane().setPrefHeight(400);
        alert.showAndWait();
    }
}
