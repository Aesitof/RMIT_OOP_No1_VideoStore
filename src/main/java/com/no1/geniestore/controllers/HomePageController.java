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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane homeView;

    @FXML
    private AnchorPane productView;

    @FXML
    private AnchorPane cartView;

    @FXML
    private AnchorPane accountDetailView;

    @FXML
    private Button searchBtn;

    @FXML
    private SplitMenuButton myAccountBtn;

    @FXML
    private SplitMenuButton videoBtn;

    @FXML
    private Button doneBtn;

    @FXML
    private Button accountSettingsBtn;

    @FXML
    private AnchorPane accountSettingsView;

    @FXML
    private Button myOrdersBtn;

    @FXML
    private AnchorPane myOrdersView;

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

        stage = (Stage)myAccountBtn.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    MenuItem dvd = new MenuItem(ItemType.DVD.toString());
    MenuItem game = new MenuItem(ItemType.GAME.toString());
    MenuItem videoRecord = new MenuItem(ItemType.VIDEO_RECORD.toString());

    /* Home View */
    public void toHomeView() {
        homeView.setVisible(true);
        productView.setVisible(false);
        cartView.setVisible(false);
        accountDetailView.setVisible(false);
    }

    /* Product View */
    public void toProductView() {
        homeView.setVisible(false);
        productView.setVisible(true);
        cartView.setVisible(false);
        accountDetailView.setVisible(false);
    }


    /* Cart View */
    public void toCartView() {
        homeView.setVisible(false);
        productView.setVisible(false);
        cartView.setVisible(true);
        accountDetailView.setVisible(false);
    }


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

    public void onUsePointDoneBtnAction(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /* My Account View */
    public void toAccountDetailView() {
        homeView.setVisible(false);
        productView.setVisible(false);
        cartView.setVisible(false);
        accountDetailView.setVisible(true);
    }

    public void toAccountSettingsView() {
        toAccountDetailView();
        accountSettingsView.setVisible(true);
        myOrdersView.setVisible(false);

        accountSettingsBtn.setStyle("-fx-background-color:#a8ed8a;-fx-text-fill:#1e4622");
        myOrdersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
    }

    public void toMyOrdersView() {
        toAccountDetailView();
        accountSettingsView.setVisible(false);
        myOrdersView.setVisible(true);

        myOrdersBtn.setStyle("-fx-background-color:#a8ed8a;-fx-text-fill:#1e4622");
        accountSettingsBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
    }


    /**
     * Switch between 2 views of My Account using the Buttons
     * @param event
     */
    public void switchAccountView(ActionEvent event) {
        if (event.getSource() == accountSettingsBtn) {
            toAccountSettingsView();
        } else if (event.getSource() == myOrdersBtn) {
            toMyOrdersView();
        }
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
        toHomeView();
    }
}
