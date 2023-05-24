package com.no1.geniestore.products;

import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.controllers.AccountListParser;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.controllers.OrderListParser;
import org.xml.sax.SAXException;
import com.no1.geniestore.products.Order;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.no1.geniestore.products.Stock.*;

public class ManagementSystem {
    private static ArrayList<Account> accountList;
    public static ArrayList<Order> orderList;//TAO CLASS ORDER BO SUNG
    public static HashMap<Item, Integer> itemList; // Total item copies
    private static Account currentUser;

    public ManagementSystem() {
        accountList = new ArrayList<>();
        orderList = new ArrayList<>();
        itemList = new HashMap<>();
        currentUser = null;
    }

    public static void main() throws ParserConfigurationException, IOException, SAXException, ParseException, TransformerException {
        ItemListParser itemListParser = new ItemListParser();
        itemList = itemListParser.parseItemTotal("xml/items.xml");
        stockList = itemListParser.parseStockList("xml/items.xml");

//        System.out.println("Hello");
//        for (Item item : stockList.keySet()) {
//            System.out.println(item + "remaining: " + stockList.get(item));
//        }
        accountList = (ArrayList<Account>) new AccountListParser().parse("xml/accounts.xml"); // get successfully
        orderList = (ArrayList<Order>) new OrderListParser().parse("xml/orders.xml"); // get successfully

        for (Order order : orderList) {
            System.out.println(order);
        }

//        ItemListParser.saveItemFile();

//        OrderListParser.saveOrderFile();
    }

//    ITEM METHODS
    public void addItem(Item item, Integer stock) { // Add Item to the stock
        if (itemList.get(item) != null) { // if already have that item in itemList
            // add to total
            int countItem = itemList.get(item);
            countItem += stock;
            itemList.put(item, countItem);

            // add to current stock
            int countStock = stockList.get(item) + stock;
            stockList.put(item, countStock);
        } else { // if the item is completely brand new
            itemList.put(item, stock); // add new item and its stock to total item
            itemStock.add(item); // add new item in itemStock using for generate itemID
            stockList.put(item, stock); // add stock to current stock
        }
    }
    public void stockArrive(Item item, int amount) {
        itemList.put(item, itemList.get(item) + amount);
    }
    public void removeItem(Item item) {
        itemList.remove(item);
    }//Remove item including its stock

//    ACCOUNTS METHODS
    public void addAccount(Account account) {
        accountList.add(account);
    }//Add accounts to the list
    public void removeAccount(String accountID) { // Remove account using ID
        accountList.removeIf(i -> i.getId().equals(accountID)); // remove from account list
    }

    public void createUsername(Account account, String username, String password) {
        for (Account acc : accountList) {
            if (acc.getUsername().equals(username)) {
                break; // stop if that username is already exist
            }

//            set username and password then add it to the account list
            account.setUsername(username);
            account.setPassword(password);
            addAccount(account);
        }
    }

    public void promote(Account account, int amount) { // Auto promote whenever return item
        if (account.getTotalReturnedItems() > 9) {
            account.setRewardPoints(account.getRewardPoints() + 10 * amount);
        } else if (account.getTotalReturnedItems() == 9) {
            account.setAccountType("Vip");
        } else if (account.getTotalReturnedItems() == 4) {
            account.setAccountType("Regular");
        }
    }



    public String login(String username, String password) {
        if(username.equals("admin") && password.equals("Admin123") ) {
            return "adminLogin";
        }
        for (int i = 0; i <= accountList.size(); i++) {
            if(accountList.get(i).getUsername().equals(username)) {
                if (accountList.get(i).getPassword().equals(password)) {
                    currentUser = accountList.get(i);
                    return "loginSuccess";
                } else
                    return "wrongPassword";
                }
        }
        return "notExist";
    }
    //    DISPLAY USERS
    public void displayUsers() {
        StringBuilder str;
        str = new StringBuilder();
        for (Account user : accountList) {
            str.append(user.toString());
            str.append(" ");
        }
    }

    //ORDER METHODS
    public void returnItem(String orderID, Item item, int amount) {//Return SINGLE item with SPECIFIED amount
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                order.returnItemInOrder(item, amount);
                itemList.put(item, itemList.get(item) + amount);// return back to the stock
                promote(order.getOwner(), amount);
            }
        }
    }
    public void makeOrder(Account account) {
        Order order = new Order(account);
    }//make a list contain orderDetails

//    DISPLAY ALL ITEMS IN THE STORE HAS
    public void displayAllItems() {
        StringBuilder str;
        str = new StringBuilder();
        for (Item item : itemStock){
            str.append(item.toString());
            str.append(" ");
        }
    }

//    DISPLAY OUT OF STOCK ITEMS
    public void displayOutOfStock() {
        StringBuilder str;
        str = new StringBuilder();
        for (Item item : itemStock) {
            if (stockList.get(item) == 0) {
                str.append(item.toString());
                str.append(" ");
            }
        }
    }

    public static void updateItem(Item item, String title, LoanType loanType, double rentalFee, Genre genre) {
        item.setTitle(title);
        item.setLoanType(loanType);
        item.setRentalFee(rentalFee);
        item.setGenre(genre);
    }


}
