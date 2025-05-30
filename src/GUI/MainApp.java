package src.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        HomeScreen home = new HomeScreen(primaryStage);

        Scene scene = new Scene(home.getView(), 400, 350);
        primaryStage.setTitle("DELI-cious POS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
