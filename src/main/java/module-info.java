module com.noone.geniestore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.xml;


    opens com.no1.geniestore to javafx.fxml;
    exports com.no1.geniestore;
    exports com.no1.geniestore.controllers;
    opens com.no1.geniestore.controllers to javafx.fxml;
    opens com.no1.geniestore.accounts to javafx.base;
}
