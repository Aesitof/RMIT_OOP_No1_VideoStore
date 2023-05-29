package com.no1.geniestore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.no1.geniestore.products.ManagementSystem.*;

public class LoginPageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Alert alert;

    double x;
    double y;
    @FXML
    private Button close;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    public void close(ActionEvent event) throws ParserConfigurationException, IOException, TransformerException {
        // Save items info to file before closing the application
        saveData();
        // close the app
        System.exit(0);
    }

    @FXML
    public void onRegisterBtnAction (MouseEvent e) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(("/registerpage-view.fxml")));
//        root = loader.load();

        root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/registerpage-view.fxml")));

        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("About Us Description");
        stage.setScene(scene);
        stage.show();
    }

    public void onSignInBtnAction (ActionEvent e) throws IOException {
        if (loginUsername.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username");
            alert.showAndWait();
            loginUsername.requestFocus();
        } else if (loginPassword.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your password");
            alert.showAndWait();
            loginPassword.requestFocus();
        } else {
            String loginResult = login(loginUsername.getText(), loginPassword.getText());

            if (loginResult.equals("notExist")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The account doesn't exist. Please register");
                alert.showAndWait();

                loginUsername.clear();
                loginPassword.clear();
            } else if (loginResult.equals("wrongPassword")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong password!!!");
                alert.showAndWait();
                loginPassword.clear();
                loginPassword.requestFocus();
            } else if (loginResult.equals("adminLogin")) {
                root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/admindetailpage-view.fxml")));

                root.setOnMousePressed((MouseEvent event) ->  {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                System.out.println("currentUser: " + currentUser);
            } else if (loginResult.equals("loginSuccess")) {
                root = FXMLLoader.load(getClass().getResource(("/com/no1/geniestore/homepage-view.fxml")));

                root.setOnMousePressed((MouseEvent event) ->  {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                System.out.println("currentUser: " + currentUser);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
