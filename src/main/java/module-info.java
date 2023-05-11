module com.example.latdoi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.no1.geniestore to javafx.fxml;
    exports com.no1.geniestore;
}