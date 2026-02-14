package menu.crit.three;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.temporal.ChronoUnit;


public class MenuGUI extends Application {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Crit Three Menu Options");
    TextArea textArea = new TextArea();
    textArea.setOpacity(0.4);

    MenuItem menuItem1 = new MenuItem("Option 1: Show Date and Time");
    MenuItem menuItem2 = new MenuItem("Option 2: Write to Log");
    MenuItem menuItem3 = new MenuItem("Option 3: Change Hue");
    MenuItem menuItem4 = new MenuItem("Option 4: Exit");

    MenuButton menuButton = new MenuButton("Please select an option", null, menuItem1, menuItem2, menuItem3, menuItem4);
    VBox mainLayout = new VBox(10);
    mainLayout.setAlignment(Pos.CENTER);
    mainLayout.setStyle("-fx-padding: 10;");
    mainLayout.getChildren().addAll(menuButton, textArea);

    menuItem1.setOnAction(event -> {
        textArea.appendText(java.time.LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + "\n");
    });
    menuItem2.setOnAction(event -> {
    try {
        java.nio.file.Files.write(
            java.nio.file.Paths.get("log.txt"),
            (textArea.getText() + "\n").getBytes(),
            java.nio.file.StandardOpenOption.CREATE,
            java.nio.file.StandardOpenOption.APPEND
        );
        textArea.appendText("Logged to file.\n");
    } catch (IOException e) {
        textArea.appendText("Error writing to log: " + e.getMessage() + "\n");
    }
    });
    menuItem3.setOnAction(event -> {
        Color greenColor = Color.rgb(0, (int)(Math.random() * 256), 0);
        mainLayout.setStyle("-fx-background-color: rgb(0, " + (int)(greenColor.getGreen() * 255) + ", 0);");
    });
    menuItem4.setOnAction(event -> {
    System.exit(0);
    });


    Color bgColor = Color.rgb(0, (int)(Math.random() * 256), 0);
    mainLayout.setStyle("-fx-background-color: rgb(" + (int)(bgColor.getRed() * 255) + ", " + (int)(bgColor.getGreen() * 255) + ", " + (int)(bgColor.getBlue() * 255) + ");");
    scene = new Scene(mainLayout, 400, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
}
public static void main(String[] args) {
Application.launch(args);
}
}