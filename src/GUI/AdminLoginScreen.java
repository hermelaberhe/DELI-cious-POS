package src.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "✅ Access Granted!", ButtonType.OK);
                alert.showAndWait();
                // Replace with Admin Dashboard screen if needed
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
}
