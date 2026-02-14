module library_system {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens library_system to javafx.fxml;
    exports library_system;
}
