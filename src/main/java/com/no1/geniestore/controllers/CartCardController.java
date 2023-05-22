package com.no1.geniestore.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static com.no1.geniestore.controllers.HomePageController.*;

public class CartCardController implements Initializable {
    @FXML
    private Label cartCardGenre;

    @FXML
    private Text cartCardGenreText;

    @FXML
    private ImageView cartCardImageView;

    @FXML
    private Text cartCardItemFee;

    @FXML
    private Label cartCardLoanType;

    @FXML
    private Spinner<Integer> cartCardQty;

    @FXML
    private Label cartCardReturnDate;

    @FXML
    private Label cartCardTitle;

    @FXML
    private Label cartCardType;

    @FXML
    private Label cartCardYear;

    @FXML
    private ImageView deleteBtn;

    public CartData cartData;
    public ObservableList<CartData> cartCartDataList;

    public VBox cartCartListVBox;

    public Text cartOrderTotal;

    public Text cartOrderSubtotal;

    public Text cartOrderDiscount;
    private Image image;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

    public CartData getCartData() {
        return cartData;
    }

    public Spinner<Integer> getCartCardQty() {
        return cartCardQty;
    }

    public Text getCartCardItemFee() {
        return cartCardItemFee;
    }

    SpinnerValueFactory.IntegerSpinnerValueFactory qtyValueFactory;

    public void setData(CartData cartData) {
        this.cartData = cartData;

        if (cartData.getItem().getGenre() != null) {
            cartCardGenre.setText(cartData.getItem().getGenre().toString());
        } else {
            cartCardGenreText.setVisible(false);
            cartCardGenre.setVisible(false);
        }

        qtyValueFactory.setMax(cartData.getRemaining());

        cartCardLoanType.setText(cartData.getItem().getLoanType().toString());
        cartCardQty.getValueFactory().setValue(cartData.getQty());
        cartCardReturnDate.setText(dateFormat.format(cartData.getReturnDate()));
        cartCardTitle.setText(cartData.getItem().getTitle());
        cartCardType.setText(cartData.getItem().getItemType().toString());
        cartCardYear.setText(String.valueOf(cartData.getItem().getYear()));
        cartCardItemFee.setText("$" + cartData.getItemFee());

        String uri = "file:images/" + cartData.getItem().getImage();
        image = new Image(uri, 188, 198, false, true);
        cartCardImageView.setImage(image);
    }

    public void updateOrderSubtotal() {
        double subTotal = 0.0;
        double total = 0.0;
        double discount = 0.0;
        for (CartData c : cartCartDataList) {
            subTotal += c.getItemFee();
        }

        total = subTotal - discount;

        cartOrderSubtotal.setText("$" + String.format("%.2f", subTotal));
        cartOrderTotal.setText("$" + String.format("%.2f", total));
    }

    public void delete() {
        cartCartListVBox.getChildren().remove(deleteBtn.getParent());
        for (CartData c : cartCartDataList) {
            if (c.getItem().equals(cartData.getItem())) {
                cartCartDataList.remove(c);
                break;
            }
        }

        for (CartData c : cartCartDataList) {
            System.out.println(c);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qtyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        cartCardQty.setValueFactory(qtyValueFactory);
        cartCardQty.setEditable(false);

        cartCardQty.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                cartCardItemFee.setText("$" + cartData.getItem().getRentalFee() * newValue);
                for (CartData c : cartCartDataList) {
                    if (c.getItem().equals(cartData.getItem())) {
                        c.setQty(newValue);
                        c.resetItemFee();
                        updateOrderSubtotal();
                    }
                }
            }
        });
    }
}
