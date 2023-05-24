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
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

public class GenieStoreApplication extends Application {
    double x;
    double y;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homepage-view.fxml"));

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

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ParseException, TransformerException {
        ManagementSystem.main();
        launch();
    }

}
