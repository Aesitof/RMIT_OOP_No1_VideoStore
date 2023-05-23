package com.no1.geniestore;

import com.no1.geniestore.products.ManagementSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GenieStoreApplication extends Application {
    double x;
    double y;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("admindetailpage-view.fxml"));

        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->  {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });


        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.setTitle("Genie Video Store");
        stage.show();
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        ManagementSystem.main();
        launch();
    }

}
