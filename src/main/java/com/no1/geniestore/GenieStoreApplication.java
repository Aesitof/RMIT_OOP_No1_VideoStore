package com.no1.geniestore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GenieStoreApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage-view.fxml"));

        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.setTitle("Genie Video Store");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
