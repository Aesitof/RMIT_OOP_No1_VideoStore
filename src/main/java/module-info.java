module com.example.latdoi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.latdoi to javafx.fxml;
    exports com.example.latdoi;
}