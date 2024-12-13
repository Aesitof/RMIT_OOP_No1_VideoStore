package com.no1.geniestore.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.controllers.AccountListParser;
import com.no1.geniestore.controllers.ItemData;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.controllers.OrderListParser;
import com.no1.geniestore.model.util.SampleAccountsUtil;
import com.no1.geniestore.model.util.SampleItemsUtil;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.ManagementSystem;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Storage {
    public static String TEXT_DIRECTORY = "txt\\";
    public static String XML_DIRECTORY = "xml\\";
    public static String AMOUNT_FILE_PATH = TEXT_DIRECTORY + "amount.txt";
    public static String ACCOUNTS_FILE_PATH = XML_DIRECTORY + "accounts.xml";
    public static String ITEMS_FILE_PATH = XML_DIRECTORY + "items.xml";
    public static String ORDERS_FILE_PATH = XML_DIRECTORY + "orders.xml";
    
    /**
     * Gets the {@code itemList},
     * either from the {@code ITEMS_FILE_PATH} or sample items and copies.
     */
    public HashMap<Item, Integer> getItemList() {
        HashMap<Item, Integer> itemList = new HashMap<>();
        try {
            ItemListParser itemListParser = new ItemListParser();
            itemList = itemListParser.readItemsCopiesFile(ITEMS_FILE_PATH).orElseGet(SampleItemsUtil::getSampleItemList);
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        
        return itemList;
    }
    
    /**
     * Gets the {@code stockList},
     * either from the {@code ITEMS_FILE_PATH} or sample items and remaining copies.
     */
    public HashMap<Item, Integer> getStockList() {
        HashMap<Item, Integer> stockList = new HashMap<>();
        try {
            ItemListParser itemListParser = new ItemListParser();
            stockList = itemListParser.readItemsStockFile(ITEMS_FILE_PATH).orElseGet(SampleItemsUtil::getSampleStockList);
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return stockList;
    }
    
    /**
     * Gets the {@code ObservableList<ItemData>},
     * either from the {@code ITEMS_FILE_PATH} or sample items and remaining copies.
     */
    public ObservableList<ItemData> getItemDataObservableList() {
        ObservableList<ItemData> itemDataObservableList = FXCollections.observableArrayList();
        try {
            ItemListParser itemListParser = new ItemListParser();
            itemDataObservableList = itemListParser.readItemsDataFromFile(ITEMS_FILE_PATH)
                                                   .orElseGet(() -> FXCollections.observableArrayList(SampleItemsUtil.getSampleItemData()));
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return itemDataObservableList;
    }
    
    /**
     * Gets the list of {@code Account},
     * either from the {@code ACCOUNTS_FILE_PATH} or sample accounts.
     */
    public ArrayList<Account> getAccountList() {
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            accountList = new AccountListParser().readAccountsFile(ACCOUNTS_FILE_PATH).orElseGet(SampleAccountsUtil::getSampleAccountList);
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        
        return accountList;
    }
    
    /**
     * Saves all the current data into the data files, whose paths indicated as static variables.
     */
    public void save() {
        try {
            createFileIfNotExist(ITEMS_FILE_PATH);
            ItemListParser.saveItemFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Unable to convert to " + ITEMS_FILE_PATH + e.getMessage());
        }
        
        try {
            createFileIfNotExist(ACCOUNTS_FILE_PATH);
            AccountListParser.accountsToXML();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Unable to convert to accounts.xml " + e.getMessage());
        }
       
        try {
            createFileIfNotExist(ORDERS_FILE_PATH);
            OrderListParser.saveOrderFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Unable to convert to orders.xml " + e.getMessage());
        }
       
        try {
            createFileIfNotExist(AMOUNT_FILE_PATH);
            ManagementSystem.writeTextFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Finds the indicated file or creates the file if it does not exist.
     *
     * @param filePath Path of the file.
     */
    public File createFileIfNotExist(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("Failed to create a directory " + directory.getPath());
            }
            if (file.createNewFile()) {
                System.out.println("New file created " + file.getPath());
            }
            
            assert file != null : "File is still null after initializing";
            return file;
        } catch (IOException e) {
            throw new IOException("Failed to create storage file" + e.getMessage());
        }
    }
}
