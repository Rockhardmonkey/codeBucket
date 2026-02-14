import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ButtonGridGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Button Grid");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(10));

        // Create a grid of buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button("Button " + (row * 3 + col + 1));
                grid.add(btn, col, row);
            }
        }

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
