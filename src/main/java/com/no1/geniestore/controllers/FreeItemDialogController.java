package com.no1.geniestore.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.no1.geniestore.products.ManagementSystem.currentUser;
import static com.no1.geniestore.products.ManagementSystem.currentUserRewardPoints;

public class FreeItemDialogController implements Initializable {
    @FXML
    private Button doneBtn;

    @FXML
    private Label usePointPoints;

    @FXML
    private Spinner<Integer> usePointSpinner;

    @FXML
    private Label usePointTitle;

//    private int currentUserPoints = 0;
    public ObservableList<CartData> freeItemCartDataList;
    public CartData freeItemCartData;


    public void setFreeItemData(CartData cartData) {
        this.freeItemCartData = cartData;

        this.usePointPoints.setText(String.valueOf(currentUserRewardPoints));
        this.usePointTitle.setText(freeItemCartData.getItem().getTitle());

        int maxSpinner = (freeItemCartData.getQty() < (int) (currentUser.getRewardPoints() / 100)) ? freeItemCartData.getQty() : (int) (currentUser.getRewardPoints() / 100);
        SpinnerValueFactory<Integer> qtyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxSpinner);
        usePointSpinner.setValueFactory(qtyValueFactory);

        // test
        System.out.println("cartData freeItemqty: " + cartData.getFreeItemQty());

        usePointSpinner.getValueFactory().setValue(cartData.getFreeItemQty());
    }

    public void onUsePointDoneBtnAction(ActionEvent event) {
        // Save remaining points of current user
//        currentUser.setRewardPoints(Integer.parseInt(usePointPoints.getText()));
        currentUserRewardPoints = Integer.parseInt(usePointPoints.getText());

        // loop cart data list, search with item id
        for (CartData cartData : freeItemCartDataList) {
            if (cartData.getItem().getId().equals(freeItemCartData.getItem().getId())) {
                if (usePointSpinner.getValue() > 0) {
                    cartData.setFreeItem(true);
                    cartData.setFreeItemQty(usePointSpinner.getValue());

                    freeItemCartData.setFreeItem(true);
                    freeItemCartData.setFreeItemQty(usePointSpinner.getValue());
                } else {
                    cartData.setFreeItem(false);
                    cartData.setFreeItemQty(0);

                    freeItemCartData.setFreeItem(false);
                    freeItemCartData.setFreeItemQty(0);
                }
            }
            break;
        }

        for (CartData cartData : freeItemCartDataList) {
            System.out.println(cartData);
        }

        System.out.println("freeItemCartData : " + freeItemCartData);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usePointSpinner.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                int newPoint = currentUser.getRewardPoints() - newValue * 100;
                usePointPoints.setText(String.valueOf(newPoint));
            }
        });
    }
}
