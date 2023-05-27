package com.no1.geniestore.controllers;

import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.products.Item;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.no1.geniestore.products.ManagementSystem.currentUser;
import static com.no1.geniestore.products.ManagementSystem.removeAccount;
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

    private double x;
    private double y;

    private static ObservableList<CartData> cartDataList = FXCollections.observableArrayList();


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
            System.out.println("currentUser " + currentUser);
        }

        // write all list to file

        // redirect to Login page
        onSignInBtnClick();
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
//            try {
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
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
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
        try {
            loadProductList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        toHomeView();
        orderTotal.setText("$0.00");
        orderSubtotal.setText("$0.00");
        orderDiscount.setText("$0.00");
    }
}
