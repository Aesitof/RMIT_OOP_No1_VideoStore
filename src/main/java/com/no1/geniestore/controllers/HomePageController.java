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
import javafx.stage.Modality;
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

    @FXML
    private Button doneBtn;

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

    // Show dialog
    public void onUsePointBtnAction(ActionEvent event) throws IOException {
        Parent dialogRoot = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/freeitemdialog-view.fxml")));
        Scene dialogScene = new Scene(dialogRoot);

        Stage dialogStage = new Stage();
        dialogStage.setScene(dialogScene);
        dialogStage.setTitle("Genie's Store - Choose free items");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
    }

    public void onUsePointDoneBtnAction() {
        Stage stage = (Stage) doneBtn.getScene().getWindow();
        stage.close();
    }

    public void onChangePasswordBtnAction(ActionEvent event) throws IOException {
        Parent dialogRoot = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/changepassworddialog-view.fxml")));
        Scene dialogScene = new Scene(dialogRoot);

        Stage dialogStage = new Stage();
        dialogStage.setScene(dialogScene);
        dialogStage.setTitle("Genie's Store - Change my password");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        videoBtn.getItems().addAll(dvd, game, videoRecord);
    }
}
