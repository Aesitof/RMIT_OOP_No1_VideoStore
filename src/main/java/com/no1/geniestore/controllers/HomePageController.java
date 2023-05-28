/* Acknowledgement
- Name regex: https://stackoverflow.com/questions/15805555/java-regex-to-validate-full-name-allow-only-spaces-and-letters;
* */

package com.no1.geniestore.controllers;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.no1.geniestore.products.ManagementSystem.*;
import static com.no1.geniestore.products.Stock.stockList;

public class HomePageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button close;

    @FXML
    AnchorPane homeView;

    @FXML
    AnchorPane productView;

    @FXML
    AnchorPane cartView;

    @FXML
    AnchorPane accountDetailView;

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

    @FXML
    private VBox productListVBox;

    @FXML
    private TextField topSearchTextField;

    @FXML
    private VBox cartListVBox;

    @FXML
    private Text orderTotal;

    @FXML
    private Text orderSubtotal;

    @FXML
    private Text orderDiscount;

    @FXML
    private Button payNowBtn;

    private Alert alert;

    /* My Account View attributes */
    @FXML
    private AnchorPane vipBox;
    @FXML
    private Label vipRewardPoints;
    @FXML
    private AnchorPane regularBox;
    @FXML
    private Label regularReturnedItems;
    @FXML
    private AnchorPane guestBox;
    @FXML
    private Label guestReturnedItems;
    @FXML
    private TextField myAccountUsername;
    @FXML
    private TextField myAccountName;
    @FXML
    private TextField myAccountAddress;
    @FXML
    private TextField myAccountPhone;
    @FXML
    private Text myAccountNameError;
    @FXML
    private Text myAccountPhoneError;
    @FXML
    private VBox myOrderListVBox;


    private double x;
    private double y;

    private static ObservableList<CartData> cartDataList = FXCollections.observableArrayList();


    public void close() throws ParserConfigurationException, IOException, TransformerException {
        // Save items info to file before closing the application
        saveData();

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

    public void logout() throws IOException {

        // add alert for Confirmation
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout? ");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            // set currentUser to null
            currentUser = null;
            currentUserRewardPoints = 0;
            System.out.println("currentUser " + currentUser);
        }

        // Delete cart data
        deleteCartDataList();

        // redirect to Login page
        onSignInBtnClick();
    }

    public void deleteCartDataList() {
        cartDataList.clear();
        orderDiscount.setText("$0.00");
        orderSubtotal.setText("$0.00");
        orderTotal.setText("$0.00");
        cartListVBox.getChildren().clear();
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
    public void loadProductList() throws IOException {
        topSearchTextField.setText("starting...");


        for (Item item : stockList.keySet()) {
            topSearchTextField.setText("loading...");
            FXMLLoader loader = new FXMLLoader();
//                loader.getClass().getResource("/com/no1/geniestore/productcard-view.fxml");
            loader.setLocation(getClass().getResource("/com/no1/geniestore/productcard-view.fxml"));
//                AnchorPane pane = loader.load(getClass().getResource("/com/no1/geniestore/productcard-view.fxml"));
            AnchorPane pane = loader.load();

            ProductCardController productCardController = loader.getController();
            productCardController.productHomeView = homeView;
            productCardController.productProductView = productView;
            productCardController.productCartView = cartView;
            productCardController.productAccountDetailView = accountDetailView;
            productCardController.productCartDataList = cartDataList;
            productCardController.productCartListVBox = cartListVBox;
            productCardController.productOrderTotal = orderTotal;
            productCardController.productOrderSubtotal = orderSubtotal;
            productCardController.productOrderDiscount = orderDiscount;

            productCardController.setData(item, stockList.get(item));

            productListVBox.getChildren().add(pane);
        }
    }



    /* Cart View */
    public void toCartView() {
        homeView.setVisible(false);
        productView.setVisible(false);
        cartView.setVisible(true);
        accountDetailView.setVisible(false);
    }

//    public static void updateOrderTotal() {
//        double total = 0.0;
//        for (CartData c : cartDataList) {
//            total += c.getItemFee();
//        }
//
//        orderTotal.setText("$" + total);
//    }



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

    public void onPayNowBtnAction() {
        Order newOrder = new Order();
        for (Account account : accountList) {
            if (account.getId().equals(currentUser.getId())) {
                newOrder.setOwner(account);
                orderList.add(newOrder);
                break;
            }
        }


        for (CartData cartData : cartDataList) {
            newOrder.addItemForRent(cartData.getItem().getId(), new Date(), cartData.getQty(), cartData.isFreeItem(), cartData.getFreeItemQty());
        }

        for (Order order : orderList) {
            System.out.println(order);
        }

        // Successful alert
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Pay successfully. Your order is being processed");
        alert.showAndWait();

        // to Order view
        toMyOrdersView();


        deleteCartDataList();
        for (CartData cartData : cartDataList) {
            System.out.println(cartData);
        }
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

        myAccountUsername.setText(currentUser.getUsername());
        myAccountUsername.setDisable(true);
        myAccountName.setText(currentUser.getName());
        myAccountAddress.setText(currentUser.getAddress());
        myAccountPhone.setText(currentUser.getPhone());

        // Initialization
        myAccountNameError.setVisible(false);
        myAccountPhoneError.setVisible(false);
        if (currentUser.getAccountType().equals("Guest")) {
            guestBox.setVisible(true);
            guestReturnedItems.setText(String.valueOf(currentUser.getTotalReturnedItems()));

            regularBox.setVisible(false);
            vipBox.setVisible(false);
        } else if (currentUser.getAccountType().equals("Regular")) {
            regularBox.setVisible(true);
            regularReturnedItems.setText(String.valueOf(currentUser.getTotalReturnedItems()));

            guestBox.setVisible(false);
            vipBox.setVisible(false);
        } else if (currentUser.getAccountType().equals("VIP")) {
            vipBox.setVisible(true);
            vipRewardPoints.setText(String.valueOf(currentUser.getRewardPoints()));

            guestBox.setVisible(false);
            regularBox.setVisible(false);
        }

        // Name validation
        myAccountName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                    myAccountName.setText(oldValue);
                    myAccountNameError.setVisible(true);
                } else {
                    myAccountNameError.setVisible(false);
                }
            }
        });

        //    Conditions for myAccountPhone TextField (only digits (10 digits))
        myAccountPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    myAccountPhone.setText(newValue.replaceAll("[\\D]", ""));
                    myAccountPhoneError.setVisible(true);
                } else if (newValue.length() > 10) {
                    myAccountPhone.setText(oldValue);
                    myAccountPhoneError.setVisible(true);
                } else {
                    myAccountPhoneError.setVisible(false);
                }
            }
        });
    }

    public void toMyOrdersView() {
        // load MyOrdersList
        try {
            loadMyOrderList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        toAccountDetailView();
        accountSettingsView.setVisible(false);
        myOrdersView.setVisible(true);

        myOrdersBtn.setStyle("-fx-background-color:#a8ed8a;-fx-text-fill:#1e4622");
        accountSettingsBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
    }

    public void loadMyOrderList() throws IOException {
        topSearchTextField.setText("starting...");

        for (Order order : orderList) {
            if (order.getOwner().getId().equals(currentUser.getId())) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/no1/geniestore/ordercard-view.fxml"));

                AnchorPane pane = loader.load();

                OrderCardController orderCardController = loader.getController();

                orderCardController.setOrderCardData(order);

                myOrderListVBox.getChildren().add(pane);
            }
        }

//        for (Item item : stockList.keySet()) {
//            topSearchTextField.setText("loading...");
//            FXMLLoader loader = new FXMLLoader();
////                loader.getClass().getResource("/com/no1/geniestore/productcard-view.fxml");
//            loader.setLocation(getClass().getResource("/com/no1/geniestore/productcard-view.fxml"));
////                AnchorPane pane = loader.load(getClass().getResource("/com/no1/geniestore/productcard-view.fxml"));
//            AnchorPane pane = loader.load();
//
//            ProductCardController productCardController = loader.getController();
//            productCardController.productHomeView = homeView;
//            productCardController.productProductView = productView;
//            productCardController.productCartView = cartView;
//            productCardController.productAccountDetailView = accountDetailView;
//            productCardController.productCartDataList = cartDataList;
//            productCardController.productCartListVBox = cartListVBox;
//            productCardController.productOrderTotal = orderTotal;
//            productCardController.productOrderSubtotal = orderSubtotal;
//            productCardController.productOrderDiscount = orderDiscount;
//
//            productCardController.setData(item, stockList.get(item));
//
//            productListVBox.getChildren().add(pane);
//        }
    }

    public void myAccountUpdateSave() {

        if (myAccountName.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the your name");
            alert.showAndWait();

            myAccountName.requestFocus();
            return;
        }

        if (myAccountAddress.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the your address");
            alert.showAndWait();

            myAccountAddress.requestFocus();
            return;
        }

        if (myAccountPhone.getText().isEmpty() || !myAccountPhone.getText().matches("\\d{10}")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Valid phone number contains 10 digits");
            alert.showAndWait();

            myAccountPhone.requestFocus();
            return;
        }

        // Update account in back-end list
        updateAccountAdmin(currentUser.getId(), myAccountName.getText(), myAccountAddress.getText(), myAccountPhone.getText(), currentUser.getAccountType(), currentUser.getTotalReturnedItems());


        // Refresh front-end view
        currentUser.setName(myAccountName.getText());
        currentUser.setAddress(myAccountAddress.getText());
        currentUser.setPhone(myAccountPhone.getText());

        // Alert update successfully
        Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
        updateAlert.setHeaderText(null);
        updateAlert.setContentText("Your changes saved");
        updateAlert.showAndWait();

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
        /* Home View initialize */
        try {
            loadProductList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        toHomeView();

        /* My Order View initialize */

        /* Cart View initialize */
        orderTotal.setText("$0.00");
        orderSubtotal.setText("$0.00");
        orderDiscount.setText("$0.00");
    }
}
