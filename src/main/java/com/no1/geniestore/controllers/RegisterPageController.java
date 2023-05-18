package com.no1.geniestore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    double x;
    double y;
    @FXML
    private Button close;

    @FXML
    private Label signInTab;

    @FXML
    private Button signUpBtn;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void toLoginPage() throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/loginpage-view.fxml")));

        root.setOnMousePressed((MouseEvent event) ->  {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage = (Stage)(signUpBtn).getScene().getWindow();
//        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("About Us Description");
        stage.setScene(scene);
        stage.show();
    }
}

