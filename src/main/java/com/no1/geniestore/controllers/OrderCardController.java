/* Acknowledgement
- Communication between controllers: https://www.youtube.com/watch?v=wxhGKR3PQpo
* */

package com.no1.geniestore.controllers;

import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.Order;
import com.no1.geniestore.products.OrderDetails;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class OrderCardController {
    @FXML
    private Label myOrderOrderId;
    @FXML
    private Label myOrderLoanDate;
    @FXML
    private Text myOrderTotalItems;
    @FXML
    private Text myOrderStatus;
    @FXML
    private Label myOrderTotalFee;

    public void setOrderCardData(Order order) {
        myOrderOrderId.setText(order.getOrderID());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        for (Item item : order.getOrder().keySet()) {
            myOrderLoanDate.setText(dateFormat.format(order.getOrder().get(item).getLoanDate()));
            break;
        }


        int totalItems = 0;
        int lateItems = 0;
        int returnedItems = 0;
        int notReturnedItems = 0;
        for (Item singleItem : order.getOrder().keySet()) {
            OrderDetails orderDetails = order.getOrder().get(singleItem);
            totalItems += orderDetails.getAmount();


            if (!orderDetails.isReturned()) {
                if (new Date().after(orderDetails.getReturnDate())) {
                    lateItems += orderDetails.getAmount();
                    notReturnedItems += orderDetails.getAmount();
                } else {
                    notReturnedItems += orderDetails.getAmount();
                }
            } else {
                returnedItems += orderDetails.getAmount();
            }
        }

        myOrderTotalItems.setText("Total " + totalItems + " item(s)");
        myOrderStatus.setText(returnedItems + " item(s) returned, " + notReturnedItems + " item(s) not returned, " + lateItems + " item(s) late");

        myOrderTotalFee.setText("$" + String.format("%.2f", order.getTotal()));
    }
}
