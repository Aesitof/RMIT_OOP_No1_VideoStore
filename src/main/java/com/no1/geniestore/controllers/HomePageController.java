package com.no1.geniestore.controllers;

import com.no1.geniestore.constants.ItemType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    private SplitMenuButton videoBtn;

    private double x;
    private double y;


    public void close() {
        System.exit(0);
    }

    public void onSignInBtnClick() throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/loginpage-view.fxml")));

        root.setOnMousePressed((MouseEvent event) ->  {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage = (Stage)signInTab.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    MenuItem dvd = new MenuItem(ItemType.DVD.toString());
    MenuItem game = new MenuItem(ItemType.GAME.toString());
    MenuItem videoRecord = new MenuItem(ItemType.VIDEO_RECORD.toString());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videoBtn.getItems().addAll(dvd, game, videoRecord);
    }
}
