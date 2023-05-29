/* Acknowledgement
- Communication between controllers: https://www.youtube.com/watch?v=wxhGKR3PQpo
* */

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
import javafx.scene.text.Text;
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

    private boolean changed = true;

//    private int currentUserPoints = 0;
    public ObservableList<CartData> freeItemCartDataList;
    public CartData freeItemCartData;
    public Text freeItemOrderDiscount;
    public Text freeItemOrderTotal;
    public Text freeItemOrderSubtotal;


    public void setFreeItemData(CartData cartData) {
        this.freeItemCartData = cartData;

        System.out.println("SetItemData");
        changed = !changed; // changed = false

//        int maxSpinner = Math.min(freeItemCartData.getQty(), (int) (currentUser.getRewardPoints() / 100));
        int maxSpinner = Math.min(freeItemCartData.getQty(), (int) (currentUserRewardPoints / 100 + cartData.getFreeItemQty()));
        SpinnerValueFactory<Integer> qtyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, maxSpinner);
        usePointSpinner.setValueFactory(qtyValueFactory);

        // test
        System.out.println("cartData freeItemqty: " + cartData.getFreeItemQty());
        if (cartData.getFreeItemQty() != usePointSpinner.getValue()) {
            changed = !changed; // changed = false
        }

        usePointSpinner.getValueFactory().setValue(cartData.getFreeItemQty());

        this.usePointPoints.setText(String.valueOf(currentUserRewardPoints));
        this.usePointTitle.setText(freeItemCartData.getItem().getTitle());

    }

    public void onUsePointDoneBtnAction(ActionEvent event) {
        // Save remaining points of current user
//        currentUser.setRewardPoints(Integer.parseInt(usePointPoints.getText()));
//        ----
//        currentUserRewardPoints = Integer.parseInt(usePointPoints.getText());

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
                break;
            }
        }

//        System.out.println(freeItemCartData);

        // Update order summary
        updateOrderSummary();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void updateOrderSummary() {
        double subTotal = 0;
        double discount = 0;
        double total = 0;

        for (CartData cartData : freeItemCartDataList) {
            subTotal += cartData.getItemFee();
            discount += cartData.itemDiscount();
        }

        total = subTotal - discount;

        freeItemOrderSubtotal.setText("$" + String.format("%.2f", subTotal));
        freeItemOrderDiscount.setText("$" + String.format("%.2f", discount));
        freeItemOrderTotal.setText("$" + String.format("%.2f", total));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usePointSpinner.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
//                currentUserRewardPoints = currentUser.getRewardPoints() - newValue * 100;
                System.out.println("changed: " + changed);
                System.out.println("currentUserRewardPoints: " + currentUserRewardPoints);
                if (oldValue != null && changed) {
                    System.out.println("not null");
                    currentUserRewardPoints = currentUserRewardPoints - (newValue - oldValue) * 100;
                } else { // if change == false
                    changed = !changed; // changed = true, back to the first state
                }

                // if it is null, it means that after click "Use point btn" -> do nothing
//                else {
//                    currentUserRewardPoints =
//                }

                usePointPoints.setText(String.valueOf(currentUserRewardPoints));
            }
        });
    }
}
