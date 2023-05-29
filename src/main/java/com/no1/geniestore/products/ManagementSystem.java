package com.no1.geniestore.products;

import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.controllers.AccountListParser;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.controllers.OrderListParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.no1.geniestore.accounts.Account.accountIdCounter;
import static com.no1.geniestore.products.Stock.*;
import static com.no1.geniestore.products.Order.orderIdCounter;
public class ManagementSystem {
    public static ArrayList<Account> accountList;
    public static ArrayList<Order> orderList;
    public static HashMap<Item, Integer> itemList; // Total item copies
    public static Account currentUser;
    public static int currentUserRewardPoints;

    public ManagementSystem() {
        accountList = new ArrayList<>();
        orderList = new ArrayList<>();
        itemList = new HashMap<>();
        currentUser = null;
    }

    public static void main() throws ParserConfigurationException, IOException, SAXException, ParseException, TransformerException {
        readTextFile();
        ItemListParser itemListParser = new ItemListParser();
        itemList = itemListParser.parseItemTotal("xml/items.xml");
        stockList = itemListParser.parseStockList("xml/items.xml");
        accountList = (ArrayList<Account>) new AccountListParser().parse("xml/accounts.xml");
        orderList = (ArrayList<Order>) new OrderListParser().parse("xml/orders.xml");

//        TEXT-BASED TESTING
//        for (Order order : orderList) {
//            System.out.println(order);
//        }

//        Account account = new Account();
//        account.setRewardPoints(200);
//        addAccount(account);
//
//        for (Account e : accountList) {
//            System.out.println(e);
//        }

//        Item item = new Item("I004-2019", "Parasite", 2019, ItemType.VIDEO_RECORD, Genre.DRAMA, LoanType.TWO_DAY_LOAN, 12.99, "parasite.jpg");
//        Order order = new Order(new Account());
//        orderList.add(order);
//        order.addItemForRent("I004-2019", new Date(2023, 1, 1), 2, true, 2);
//
//        for (Order e : orderList) {
//            System.out.println(e.returnItemInOrder("I004-2019"));
//        }
//          ItemListParser.saveItemFile();
//        for (Order order : orderList) {
//            System.out.println(order);
//        }

//        OrderListParser.saveOrderFile();
    }

    public static void saveData() throws ParserConfigurationException, IOException, TransformerException {
        // Save items info to file before closing the application
        ItemListParser.saveItemFile();
        AccountListParser.accountsToXML();

        for (Order order : orderList) {
            System.out.println(order);
        }
        OrderListParser.saveOrderFile();
        writeTextFile();
    }

    public static void readTextFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("txt/amount.txt"));

        itemIdCounter = Integer.parseInt(reader.readLine());
        accountIdCounter = Integer.parseInt(reader.readLine());
        orderIdCounter = Integer.parseInt(reader.readLine());
    }

    public static void writeTextFile() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("txt/amount.txt"));
        writer.printf("%d\n%d\n%d", itemIdCounter, accountIdCounter, orderIdCounter );
        writer.close();
    }

//        ITEM METHODS
    public static void addItem(Item item, Integer stock) { // Add Item to the stock
        if (itemList.get(item) != null) { // if already have that item in itemList
            // add to total
            int countItem = itemList.get(item);
            countItem += stock;
            itemList.put(item, countItem);

            // add to current stock
            int countStock = stockList.get(item) + stock;
            stockList.put(item, countStock);
        } else { // if the item is completely brand new
            itemList.put(item, stock); // add new item and its stock to total item copies
            stockList.put(item, stock); // add stock to current stock available in store
        }
    }

    public void stockArrive(Item item, int amount) {
        itemList.put(item, itemList.get(item) + amount);
    }

    public static void removeItemInItemList(Item item) {
        itemList.remove(item); // Remove item including its stock
    }

    public static void removeItemInStockList(Item item) {
        stockList.remove(item); // Remove item including its stock
    }

    public static void updateItem(String itemId, String title, ItemType itemType, LoanType loanType, double rentalFee, Genre genre, int totalCopies, int remainingCopies) {
        for (Item item : itemList.keySet()) {
            if (item.getId().equals(itemId)) {
                item.setTitle(title);
                item.setItemType(itemType);
                item.setLoanType(loanType);
                item.setRentalFee(rentalFee);
                item.setGenre(genre);
                itemList.put(item, totalCopies);
            }
        }

        for (Item item : stockList.keySet()) {
            if (item.getId().equals(itemId)) {
                item.setTitle(title);
                item.setLoanType(loanType);
                item.setRentalFee(rentalFee);
                item.setGenre(genre);
                stockList.put(item, remainingCopies);
            }
        }
    }

//        ACCOUNTS METHODS
    public static void addAccount(Account account) {
        accountList.add(account); // Add accounts to the list
    }

    public static void removeAccount(String accountID) { // Remove account using ID
        accountList.removeIf(i -> i.getId().equals(accountID)); // remove from account list
    }

    public static boolean createUsername(Account account) {
        for (Account acc : accountList) {
            if (acc.getUsername().equals(account.getUsername()) || account.getUsername().equals("admin")) {
                return false; // stop if that username is already exist
            }

        }
        addAccount(account);
        return true;
    }

    public static void updateAccountAdmin(String accountId, String newName, String newAddress, String newPhone, String newAccountType, int newTotalReturnedItems) {
        for (Account account : accountList) {
            if (account.getId().equals(accountId)) {
                account.setName(newName);
                account.setAddress(newAddress);
                account.setPhone(newPhone);
                account.setAccountType(newAccountType);
                account.setTotalReturnedItems(newTotalReturnedItems);

//                promote account if applicable
                promote(account);

                break;
            }
        }
    }

    public static String customerChangePassword(String oldPassword, String newPassword, String repeatPassword) {
        if (oldPassword.equals(currentUser.getPassword())) {
            if (newPassword.equals(repeatPassword)) {
                for (Account account : accountList) {
                    if (account.getId().equals(currentUser.getId())) {
                        account.setPassword(newPassword);
                        currentUser.setPassword(newPassword);
                        return "success";
                    }
                }
                return "fail";
            } else {
                return "not match";
            }
        } else {
            return "incorrect";
        }
    }

    public static void promote(Account account) { // Auto promote whenever return item
        if (account.getTotalReturnedItems() >= 10) {
            account.setAccountType("VIP");
        } else if (account.getTotalReturnedItems() >= 4) {
            account.setAccountType("Regular");
        }
    }

    public static void promoteReturn(Account account, int amount, int totalReturnedItemsBefore) { // Auto promote whenever return item
        if (!account.getAccountType().equals("VIP")) {
            if (account.getTotalReturnedItems() >= 10) {
                account.setRewardPoints(account.getRewardPoints() + (amount + totalReturnedItemsBefore - 10) * 10);
                account.setAccountType("VIP");
            } else if (account.getTotalReturnedItems() >= 4) {
                account.setAccountType("Regular");
            }
        } else {
            account.setRewardPoints(account.getRewardPoints() + (amount * 10));
        }
    }

    public static String login(String username, String password) {
        if (username.equals("admin") && password.equals("Admin123")) {
            return "adminLogin";
        }
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username)) {
                if (accountList.get(i).getPassword().equals(password)) {
                    currentUser = accountList.get(i);
                    currentUserRewardPoints = currentUser.getRewardPoints();
                    return "loginSuccess";
                } else
                    return "wrongPassword";
            }
        }
        return "notExist";
    }

//    ORDER METHODS
    public static double returnItem(String orderID, String itemID) { // return the penalty fee
        double latePenaltyFee = 0;
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                latePenaltyFee = order.returnItemInOrder(orderID, itemID);
                break; // break the loop after successfully return
            }
        }

        return latePenaltyFee;
    }

    public void makeOrder(Account account) {
        Order order = new Order(account); // make a list contain orderDetails
    }
}