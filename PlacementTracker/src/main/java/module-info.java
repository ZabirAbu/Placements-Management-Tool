module com.example.plcmttrkr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;

    opens com.example.plcmttrkr to javafx.fxml;
    exports com.example.plcmttrkr;
}