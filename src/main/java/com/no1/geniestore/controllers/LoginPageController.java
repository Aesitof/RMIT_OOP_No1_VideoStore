package com.no1.geniestore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button close;
    public void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void onRegisterBtnAction (ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(("/registerpage-view.fxml")));
//        root = loader.load();

        root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/registerpage-view.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("About Us Description");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
