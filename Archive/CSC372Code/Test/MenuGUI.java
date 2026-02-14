import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MenuGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu GUI");

        Button btnStart = new Button("Start");
        btnStart.setOnAction(e -> openButtonGridGUI());

        StackPane root = new StackPane();
        root.getChildren().add(btnStart);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 