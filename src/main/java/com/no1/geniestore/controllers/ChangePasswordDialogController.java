package com.no1.geniestore.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import static com.no1.geniestore.products.ManagementSystem.customerChangePassword;

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

    public void saveChangePasswordDialog() {
        if (oldPassword.getText().isEmpty()) {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.setTitle("Error Message");
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("You must enter your old password");
            updateAlert.showAndWait();

            oldPassword.requestFocus();
            return;
        }

        if (newPassword.getText().isEmpty()) {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.setTitle("Error Message");
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("You must enter your new password");
            updateAlert.showAndWait();

            newPassword.requestFocus();
            return;
        }

        if (repeatNewPassword.getText().isEmpty()) {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.setTitle("Error Message");
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("You must re-enter your new password for confirmation");
            updateAlert.showAndWait();

            repeatNewPassword.requestFocus();
            return;
        }

        String changePassword = customerChangePassword(oldPassword.getText(), newPassword.getText(), repeatNewPassword.getText());
        if (changePassword.equals("success")) {
            Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
            updateAlert.setTitle("Success Message");
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("New password saved successfully");
            updateAlert.showAndWait();
        } else if (changePassword.equals("not match")) {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.setTitle("Error Message");
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("New password and the confirmation password don't match");
            updateAlert.showAndWait();

            newPassword.clear();
            repeatNewPassword.clear();
            newPassword.requestFocus();
            return;
        } else if (changePassword.equals("incorrect")) {
            Alert updateAlert = new Alert(Alert.AlertType.ERROR);
            updateAlert.setTitle("Error Message");
            updateAlert.setHeaderText(null);
            updateAlert.setContentText("You old password is incorrect");
            updateAlert.showAndWait();

            oldPassword.clear();
            oldPassword.requestFocus();
            return;
        }

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
