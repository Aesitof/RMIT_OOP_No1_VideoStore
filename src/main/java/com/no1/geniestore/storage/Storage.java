package com.no1.geniestore.storage;

import java.io.File;
import java.io.IOException;

import com.no1.geniestore.controllers.AccountListParser;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.controllers.OrderListParser;
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
     * Saves all the current data into the data files, whose paths indicated as static variables.
     */
    public void save() {
        try {
            createFileIfNotExist(ITEMS_FILE_PATH);
            ItemListParser.saveItemFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Unable to convert to items.xml " + e.getMessage());
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