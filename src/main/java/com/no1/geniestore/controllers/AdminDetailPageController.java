package com.no1.geniestore.controllers;

import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class AdminDetailPageController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane addItemForm;
    @FXML
    private AnchorPane addOrderForm;
    @FXML
    private AnchorPane addCustomerForm;
    @FXML
    private TableColumn<ItemData, String> addItemColCopies;

    @FXML
    private TableColumn<ItemData, Genre> addItemColGenre;

    @FXML
    private TableColumn<ItemData, String> addItemColId;

    @FXML
    private TableColumn<ItemData, String> addItemColTitle;

    @FXML
    private TableColumn<ItemData, LoanType> addItemColLoanType;

    @FXML
    private TableColumn<ItemData, String> addItemColRemaining;

    @FXML
    private TableColumn<ItemData, String> addItemColRentalFee;

    @FXML
    private TableColumn<ItemData, ItemType> addItemColType;

    @FXML
    private TableColumn<ItemData, String> addItemColYear;

    @FXML
    private TextField addItemSearch;

    @FXML
    private TableView<ItemData> addItemTableView;

    @FXML
    private Button close;

    @FXML
    private Spinner<Integer> copies;

    @FXML
    private Button customersBtn;

    @FXML
    private ComboBox<Genre> genreComboBox;

    @FXML
    private Button ordersBtn;

    @FXML
    private Button itemsBtn;

    @FXML
    private ComboBox<LoanType> loanTypeComboBox;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Spinner<Integer> remaining;

    @FXML
    private TextField itemId;

    @FXML
    private TextField itemTitle;

    @FXML
    private Label itemTitleError;

    @FXML
    private TextField rentalFee;

    @FXML
    private Label rentalFeeError;

    @FXML
    private ComboBox<ItemType> typeComboBox;

    @FXML
    private Label username;
    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private Label yearComboBoxError;

    @FXML
    private ImageView addItemImage;
    private Image image;
    @FXML
    private Label imagePath;

    private ObservableList<ItemData> addItemList;

    public void addItemShowListData() {
        addItemColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        addItemColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addItemColYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        addItemColType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        addItemColGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addItemColLoanType.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        addItemColRentalFee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
        addItemColCopies.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        addItemColRemaining.setCellValueFactory(new PropertyValueFactory<>("remainingCopies"));

        addItemTableView.setItems(addItemList);
    }

    public void addItemSelect() {
        ItemData itemData = addItemTableView.getSelectionModel().getSelectedItem();
        int num = addItemTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        itemId.setText(itemData.getId());
        itemTitle.setText(itemData.getTitle());
        yearComboBox.setValue(itemData.getYear());
        typeComboBox.setValue(itemData.getItemType());
        genreComboBox.setValue(itemData.getGenre());
        loanTypeComboBox.setValue(itemData.getLoanType());
        rentalFee.setText(String.valueOf(itemData.getRentalFee()));
        copies.getValueFactory().setValue(itemData.getTotalCopies());
        remaining.getValueFactory().setValue(itemData.getRemainingCopies());

//        String uri = AdminDetailPageController.getResource("/assets/lastchristmas.jpg").toString();
        String uri = "file:images/" + itemData.getImage();
        image = new Image(uri, 200, 200, false, true);
        addItemImage.setImage(image);
        imagePath.setText(itemData.getImage());

//        for (Integer year : yearComboList) {
//            if (year == itemData.getYear()) {
//                yearComboBox.setValue(year);
//            }
//        }
    }

    public void updateItem() {
        for (ItemData item : addItemList) {
            if (item.getId().equals(itemId.getText())) {
                item.setTitle(itemTitle.getText());
                item.setYear(Integer.parseInt(String.valueOf(yearComboBox.getValue())));
                item.setItemType(typeComboBox.getValue());
                item.setGenre(genreComboBox.getValue());
                item.setLoanType(loanTypeComboBox.getValue());
                item.setRentalFee(Double.parseDouble(rentalFee.getText()));
                item.setTotalCopies((int) copies.getValue());
                item.setRemainingCopies((int) remaining.getValue());
                item.setImage(imagePath.getText());

                addItemTableView.refresh();

                Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
                updateAlert.setContentText("Update item successfully");
                updateAlert.show();
            }
        }
    }

    public void addItemInsertImage() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(addItemForm.getScene().getWindow());

        if (file != null) {
            // command to add path of image
            imagePath.setText(file.getName());
            image = new Image(file.toURI().toString(), 200, 200, false, true);
            addItemImage.setImage(image);
        }
    }

    public ArrayList<Integer> yearList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1900; i <= 2023; i++) {
            list.add(i);
        }
        return list;
    }

    public ArrayList<Integer> yearComboList = yearList();

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    private double x;
    private double y;

    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/com/no1/geniestore/homepage-view.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->  {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

                // write from Observable List to XML
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == ordersBtn) {
            addOrderForm.setVisible(true);
            addItemForm.setVisible(false);
            addCustomerForm.setVisible(false);

            ordersBtn.setStyle("-fx-background-color:#1e4622;-fx-text-fill:#fff");
            itemsBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
            customersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
        } else if (event.getSource() == itemsBtn) {
            addOrderForm.setVisible(false);
            addItemForm.setVisible(true);
            addCustomerForm.setVisible(false);

            itemsBtn.setStyle("-fx-background-color:#1e4622;-fx-text-fill:#fff");
            ordersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
            customersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
        } else if (event.getSource() == customersBtn) {
            addOrderForm.setVisible(false);
            addItemForm.setVisible(false);
            addCustomerForm.setVisible(true);

            customersBtn.setStyle("-fx-background-color:#1e4622;-fx-text-fill:#fff");
            itemsBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
            ordersBtn.setStyle("-fx-background-color:transparent;-fx-text-fill:#000");
        }
    }


//    protected String errorMessage = String.format("-fx-text-fill: RED;");
//    protected String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Parse from XML to ObservableList of ItemData, can use thread to be faster
        try {
            addItemList = new ItemListParser().parse("xml/items.xml");
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        addItemShowListData();

//        addItemImage.setImage(new Image(getClass().getResource("/assets/lastchristmas.jpg").toString(), 200.0, 200.0, false, true));

        // Item ID
        itemId.setDisable(true);

        //Item Title
        itemTitleError.setVisible(false);
        itemTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    itemTitleError.setVisible(true);
                } else {
                    itemTitleError.setVisible(false);
                }
            }
        });

        rentalFeeError.setVisible(false);
        yearComboBoxError.setVisible(false);
//        yearComboBox.getItems().addAll(yearComboList);
        for (int i = 1900; i <= 2023; i++) {
            yearComboBox.getItems().add(i);
        }
        yearComboBox.setEditable(true);

        SpinnerValueFactory<Integer> copiesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);

        copiesValueFactory.setValue(1);

        SpinnerValueFactory<Integer> remainingValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);

        remainingValueFactory.setValue(1);

        copies.setValueFactory(copiesValueFactory);
        remaining.setValueFactory(remainingValueFactory);

//        Get value from 2 spinners
//        itemId.setText(Integer.toString(copies.getValue()));

//        copies.valueProperty().addListener(new ChangeListener<Integer>() {
//            @Override
//            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
//                itemId.setText(Integer.toString(copies.getValue()));
//            }
//        });

        //    Conditions for rentalFee TextField
        rentalFee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("(\\d+(\\.\\d*)?)?")) {
                    rentalFee.setText(oldValue);
                    rentalFeeError.setVisible(true);
                } else {
                    rentalFeeError.setVisible(false);
                }

//                try {
//                    Double.parseDouble(newValue);
//                    rentalFeeError.setVisible(false);
//                } catch (NumberFormatException exception) {
//                    rentalFee.setText(newValue.replaceAll("[\\D]", ""));
//                        rentalFeeError.setVisible(true);
//                }
            }
        });

        yearComboBox.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                int yearValue = 0;
                try {
                    yearValue = Integer.parseInt(newValue);
                } catch (NumberFormatException e) {}

                if(yearValue > 2023 || !newValue.matches("\\d*")) {
                    yearComboBox.getEditor().setText(oldValue);
                    yearComboBoxError.setVisible(true);
                } else {
                    yearComboBoxError.setVisible(false);
                }

            }
        });

        // Type content
        typeComboBox.getItems().addAll(ItemType.DVD, ItemType.GAME, ItemType.VIDEO_RECORD);

        // Genre content
        genreComboBox.getItems().addAll(Genre.ACTION, Genre.DRAMA, Genre.COMEDY, Genre.HORROR);

        // Loan type content
        loanTypeComboBox.getItems().addAll(LoanType.ONE_WEEK_LOAN, LoanType.TWO_DAY_LOAN);

    }
}
