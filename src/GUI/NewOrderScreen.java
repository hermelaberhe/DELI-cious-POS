package src.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewOrderScreen {
    private VBox view;

    public NewOrderScreen(Stage stage) {
        view = new VBox(15);
        view.setPadding(new Insets(30));
        view.setAlignment(Pos.CENTER);

        Button customSandwichBtn = new Button("🥪 Add Custom Sandwich");
        Button signatureBtn = new Button("⭐ Add Signature Sandwich");
        Button drinkBtn = new Button("🥤 Add Drink");
        Button chipBtn = new Button("🍟 Add Chips");
        Button checkoutBtn = new Button("✅ Checkout");
        Button backBtn = new Button("🔙 Back");

        backBtn.setOnAction(e -> {
            HomeScreen home = new HomeScreen(stage);
            stage.setScene(new Scene(home.getView(), 400, 350));
        });

        view.getChildren().addAll(customSandwichBtn, signatureBtn, drinkBtn, chipBtn, checkoutBtn, backBtn);
    }

    public VBox getView() {
        return view;
    }
}
