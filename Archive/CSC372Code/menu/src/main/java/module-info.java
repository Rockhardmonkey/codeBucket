module menu.crit.three {
    requires javafx.controls;
    requires javafx.fxml;

    opens menu.crit.three to javafx.fxml;
    exports menu.crit.three;
}
