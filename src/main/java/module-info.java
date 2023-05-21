module com.example.latdoi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.no1.geniestore to javafx.fxml;
    exports com.no1.geniestore;
    exports com.no1.geniestore.controllers;
    opens com.no1.geniestore.controllers to javafx.fxml;
}