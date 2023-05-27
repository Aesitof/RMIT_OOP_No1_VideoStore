package com.no1.geniestore.controllers;

import com.no1.geniestore.accounts.Account;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.no1.geniestore.products.ManagementSystem.*;

public class RegisterPageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Alert alert;
    double x;
    double y;
    @FXML
    private Button close;

    @FXML
    private Label signInTab;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField registerName;

    @FXML
    private TextField registerPhone;

    @FXML
    private TextField registerAddress;

    @FXML
    private TextField registerUsername;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private PasswordField registerRepeatPassword;



    @FXML
    void close(ActionEvent event) throws ParserConfigurationException, IOException, TransformerException {
        // Save items info to file before closing the application
        saveData();
        // close the app
        System.exit(0);
    }


    @FXML
    public void toLoginPage() throws IOException {
        if (registerName.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your name");
            alert.showAndWait();
            registerName.requestFocus();
            return;
        }

        if (registerPhone.getText().isEmpty() || !registerPhone.getText().matches("\\d{10}")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Valid phone number contains 10 digits");
            alert.showAndWait();
            registerPhone.requestFocus();
            return;
        }

        if (registerAddress.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your address");
            alert.showAndWait();
            registerAddress.requestFocus();
            return;
        }

        if (registerUsername.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username that you will use to Sign in");
            alert.showAndWait();
            registerUsername.requestFocus();
            return;
        }

        if (registerPassword.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your password");
            alert.showAndWait();
            registerPassword.requestFocus();
            return;
        }

//        passwordLabel.setText(registerPassword);

        if (registerRepeatPassword.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please re-enter your password for confirmation");
            alert.showAndWait();
            registerRepeatPassword.requestFocus();
            return;
        } else if (!registerRepeatPassword.getText().equals(registerPassword.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Your password and the confirmation password do not match");
            alert.showAndWait();

            registerPassword.clear();
            registerRepeatPassword.clear();
            registerPassword.requestFocus();
            return;
        }

        Account newAccount = new Account(registerName.getText(), registerAddress.getText(), registerPhone.getText(), registerUsername.getText(), registerPassword.getText(), "Guest");
        if (createUsername(newAccount) == true) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Register successfully");
            alert.showAndWait();

            for (Account account : accountList) {
                System.out.println(account);
            }

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
            scene = new Scene(root);
            stage.setTitle("About Us Description");
            stage.setScene(scene);
            stage.show();

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("This username already exists");
            alert.showAndWait();
            registerUsername.requestFocus();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //    Conditions for registerPhone TextField (only digits (10 digits))
        registerPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    registerPhone.setText(newValue.replaceAll("[\\D]", ""));
                } else if (newValue.length() > 10) {
                    registerPhone.setText(oldValue);
                }
            }
        });
    }
}

