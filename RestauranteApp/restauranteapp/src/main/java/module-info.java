module restauranteapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens restauranteapp to javafx.fxml;
    exports restauranteapp;
}
