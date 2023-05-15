package com.no1.geniestore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button close;
    @FXML
    private Button searchBtn;

    @FXML
    private Label signInTab;

    public void close() {
        System.exit(0);
    }

    public void onSignInBtnClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/loginpage-view.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
