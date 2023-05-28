package com.no1.geniestore.controllers;

import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.ManagementSystem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.no1.geniestore.controllers.HomePageController;

import static com.no1.geniestore.products.ManagementSystem.currentUser;
import static com.no1.geniestore.products.Stock.stockList;

public class ProductCardController implements Initializable {

    public AnchorPane productHomeView;
    public AnchorPane productCartView;
    public AnchorPane productProductView;
    public AnchorPane productAccountDetailView;

    @FXML
    private Button addToCartBtn;

    @FXML
    private Label productCardGenre;
    @FXML
    private Text productCardGenreText;

    @FXML
    private ImageView productCardImageView;

    @FXML
    private Label productCardLoanType;

    @FXML
    private Text productCardRemaining;

    @FXML
    private Label productCardRentalFee;

    @FXML
    private Label productCardTitle;

    @FXML
    private Label productCardType;

    @FXML
    private Label productCardYear;

    public ObservableList<CartData> productCartDataList;

    public VBox productCartListVBox;

    public Text productOrderTotal;

    public Text productOrderSubtotal;

    public Text productOrderDiscount;

    private Image image;

    private Alert alert;

    private Item item;
    private int remainingCopies;

    public void setData(Item item, int remainingCopies) {
        this.item = item;
        this.remainingCopies = remainingCopies;

        productCardTitle.setText(item.getTitle());
        productCardType.setText(item.getItemType().toString());
        productCardYear.setText(String.valueOf(item.getYear()));
        if(item.getGenre() != null) {
            productCardGenre.setText(item.getGenre().toString());
        } else {
            productCardGenreText.setVisible(false);
            productCardGenre.setVisible(false);
        }

        productCardRentalFee.setText("$" + item.getRentalFee());
        productCardLoanType.setText(item.getLoanType().toString());

        if (remainingCopies > 0) {
            productCardRemaining.setText(remainingCopies + " items left");
        } else {
            productCardRemaining.setText("Out of stock");
            productCardRemaining.setFill(Color.RED);
            addToCartBtn.setDisable(true);
        }

        String uri = "file:images/" + item.getImage();
        image = new Image(uri, 188, 198, false, true);
        productCardImageView.setImage(image);

    }

    public void addToCart() throws IOException, NullPointerException {
        // Guest cannot by 2 day items
        if (item.getLoanType().equals(LoanType.TWO_DAY_LOAN) && currentUser.getAccountType().equals("Guest")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Your account level is not eligible to borrow 2-day items");
            alert.showAndWait();
            return;
        }


        CartData cartData = new CartData(item, remainingCopies);

        for (CartData c : productCartDataList) {
            if (c.getItem().equals(cartData.getItem())) {
                // If the item is already in the cart, increase qty by 1 (removed this method)

//                c.setQty(c.getQty() + 1);
//                c.resetItemFee();
//                for (Node anchorPane : productCartListVBox.getChildren()) {
//                    FXMLLoader childLoader = new FXMLLoader();
//                    try {
//                        childLoader = (FXMLLoader) ( ((AnchorPane) anchorPane) ) .getUserData();
//                    } catch (NullPointerException exception) {
//
//                    }
//
//                    CartCardController cController = childLoader.getController();
//                    if (cController.getCartData().getItem().equals(cartData.getItem())) {
//                        cController.getCartCardQty().getValueFactory().increment(1);
//                        cController.getCartCardItemFee().setText("$" + c.getItemFee());
//                        break;
//                    }
//                }

                // If the item is already in the cart, direct to cart view
                toCartView();
                return;
            }
        }

        productCartDataList.add(cartData);

        // Load the cartCard object
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/no1/geniestore/cartcard-view.fxml"));;
        AnchorPane pane = loader.load();
        pane.setUserData(loader);

        CartCardController cartCardController = loader.getController();

        cartCardController.setData(cartData);

        cartCardController.cartData = cartData;
        cartCardController.cartCartDataList = productCartDataList;
        cartCardController.cartOrderTotal = productOrderTotal;
        cartCardController.cartOrderSubtotal = productOrderSubtotal;
        cartCardController.cartOrderDiscount = productOrderDiscount;
        cartCardController.cartCartListVBox = productCartListVBox;

        productCartListVBox.getChildren().add(pane); // add the object to the VBox
        cartCardController.updateOrderSubtotal();



        // Content in toCartView() method
        toCartView();
    }

    public void toCartView() {
        productHomeView.setVisible(false);
        productProductView.setVisible(false);
        productCartView.setVisible(true);
        productAccountDetailView.setVisible(false);
    }

    public void loadCartList() throws IOException {
//        topSearchTextField.setText("starting...");


        for (CartData cartData : productCartDataList) {
//            topSearchTextField.setText("loading...");
//            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/no1/geniestore/cartcard-view.fxml"));;
            AnchorPane pane = loader.load();

            CartCardController cartCardController = loader.getController();

            cartCardController.setData(cartData);

            productCartListVBox.getChildren().add(pane);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
