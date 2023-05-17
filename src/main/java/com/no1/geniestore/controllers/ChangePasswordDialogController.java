package com.no1.geniestore.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordDialogController {
    @FXML
    private Button cancelBtn;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField repeatNewPassword;

    @FXML
    private Button saveBtn;

    public void cancelChangePasswordDialog() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
