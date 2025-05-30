// truncated: add missing closing braces
package src.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.models.Order;
import src.services.PriceCalculator;
import src.util.OrderStorage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class NewOrderScreen {
    private VBox view;

    public NewOrderScreen(Stage stage, Order order) {
        view = new VBox(15);
        view.setPadding(new Insets(30));
        view.setAlignment(Pos.CENTER);

        Button customSandwichBtn = new Button("\uD83E\uDD6A Add Custom Sandwich");
        Button signatureBtn = new Button("⭐ Add Signature Sandwich");
        Button drinkBtn = new Button("\uD83E\uDD64 Add Drink");
        Button chipBtn = new Button("\uD83C\uDF5F Add Chips");
        Button checkoutBtn = new Button("✅ Checkout");
        Button viewOrdersBtn = new Button("\uD83D\uDCC4 View Past Orders");
        Button exportCSVBtn = new Button("\uD83D\uDCC4 Export Orders to CSV");
        Button adminLoginBtn = new Button("\uD83D\uDD10 Admin Login");
        Button backBtn = new Button("\uD83D\uDD19 Back");

        customSandwichBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Custom Sandwich");
            alert.setContentText("👉 This is where you'd add custom sandwich logic.");
            alert.showAndWait();
        });

        signatureBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Signature Sandwich");
            alert.setContentText("👉 This is where you'd add signature sandwich logic.");
            alert.showAndWait();
        });

        drinkBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Drink");
            alert.setContentText("👉 This is where you'd add drink options.");
            alert.showAndWait();
        });

        chipBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chips");
            alert.setContentText("👉 This is where you'd add chips to the order.");
            alert.showAndWait();
        });

        checkoutBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Checkout");
            alert.setContentText("✅ Checkout process and receipt generation goes here.");
            alert.showAndWait();
        });

        viewOrdersBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("View Past Orders");
            alert.setContentText("📄 Display previous orders from receipts folder.");
            alert.showAndWait();
        });

        exportCSVBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Orders");
            alert.setContentText("📤 Export functionality writes to orders.csv.");
            alert.showAndWait();
        });

        adminLoginBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Admin Login");
            dialog.setHeaderText("Enter Admin Password:");
            dialog.setContentText("Password:");
            dialog.showAndWait().ifPresent(pass -> {
                if (!pass.equals("deli123")) {
                    new Alert(Alert.AlertType.ERROR, "❌ Incorrect password.", ButtonType.OK).showAndWait();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "✅ Welcome, Admin! Transaction summary here.", ButtonType.OK).showAndWait();
                }
            });
        });

        backBtn.setOnAction(e -> {
            stage.close();
        });

        view.getChildren().addAll(
                customSandwichBtn, signatureBtn, drinkBtn, chipBtn,
                checkoutBtn, viewOrdersBtn, exportCSVBtn, adminLoginBtn, backBtn
        );
    }

    public VBox getView() {
        return view;
    }
}
