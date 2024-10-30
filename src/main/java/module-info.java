module com.eightpuzzle {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.eightpuzzle to javafx.fxml;
    exports com.eightpuzzle.graficInterface;
}
