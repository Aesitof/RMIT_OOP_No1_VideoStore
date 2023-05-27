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
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.no1.geniestore.accounts.Account.*;
import static com.no1.geniestore.products.Stock.*;
import static com.no1.geniestore.products.Order.*;
public class ManagementSystem {
    public static ArrayList<Account> accountList;
    public static ArrayList<Order> orderList;
    public static HashMap<Item, Integer> itemList; // Total item copies
    private static Account currentUser;

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

//        System.out.println("Hello");
//        for (Item item : stockList.keySet()) {
//            System.out.println(item + "remaining: " + stockList.get(item));
//        }
        accountList = (ArrayList<Account>) new AccountListParser().parse("xml/accounts.xml"); // get successfully
        orderList = (ArrayList<Order>) new OrderListParser().parse("xml/orders.xml"); // get successfully

//        for (Order order : orderList) {
//            System.out.println(order.getOrderID());
//        }

        Item item = new Item("I004-2019", "Parasite", 2019, ItemType.VIDEO_RECORD, Genre.DRAMA, LoanType.TWO_DAY_LOAN, 12.99, "parasite.jpg");
        returnItem("1", "I004-2019");

//          ItemListParser.saveItemFile();
        for (Order order : orderList) {
            System.out.println(order);
        }

        OrderListParser.saveOrderFile();
    }

    public static void readTextFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("txt/amount.txt"));

        itemIdCounter = Integer.parseInt(reader.readLine());
        accountIdCounter = Integer.parseInt(reader.readLine());
        orderIdCounter = Integer.parseInt(reader.readLine());

        // Print the Integer
//        System.out.println(accountIdCounter);

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
            itemStock.add(item); // add new item in itemStock
            itemList.put(item, stock); // add new item and its stock to total item copies
            stockList.put(item, stock); // add stock to current stock available in store
        }
    }

    public void stockArrive(Item item, int amount) {
        itemList.put(item, itemList.get(item) + amount);
    }

    public static void removeItemInItemList(Item item) {
//        Iterator<Item> itemIterator = itemList.keySet().iterator();
//        while (itemIterator.hasNext()) {
//            Item key = itemIterator.next();
//            System.out.println("Map Value:" + itemList.get(key));
//            if (key.getId().equals(itemId)) {
//                itemList.remove(key);
//            }
//        }
        itemList.remove(item); // Remove item including its stock
    }

    public static void removeItemInStockList(Item item) {
        stockList.remove(item); // Remove item including its stock
    }

    public static void updateItem(String itemId, String title, LoanType loanType, double rentalFee, Genre genre, int totalCopies, int remainingCopies) {
//        for (Item i : itemStock) {
//            if (i.getId().equals(itemId)) {
//                i.setTitle(title);
//                i.setLoanType(loanType);
//                i.setRentalFee(rentalFee);
//                i.setGenre(genre);
//                itemList.put(i, totalCopies);
//                stockList.put(i, remainingCopies);
//            }
//        }

        for (Item item : itemList.keySet()) {
            if (item.getId().equals(itemId)) {
                item.setTitle(title);
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
    public void addAccount(Account account) {
        accountList.add(account); // Add accounts to the list
    }

    public void removeAccount(String accountID) { // Remove account using ID
        accountList.removeIf(i -> i.getId().equals(accountID)); // remove from account list
    }

    public boolean createUsername(Account account, String username, String password) {
        for (Account acc : accountList) {
            if (acc.getUsername().equals(username)) {
                return false; // stop if that username is already exist
            }

            // set username and password then add it to the account list
            account.setUsername(username);
            account.setPassword(password);
            addAccount(account);
        }
        return true;
    }

    public static void promote(Account account, int amount) { // Auto promote whenever return item
        if (account.getTotalReturnedItems() > 9) {
            account.setRewardPoints(account.getRewardPoints() + 10 * amount);
        } else if (account.getTotalReturnedItems() == 9) {
            account.setAccountType("Vip");
        } else if (account.getTotalReturnedItems() == 4) {
            account.setAccountType("Regular");
        }
    }

    public String login(String username, String password) {
        if (username.equals("admin") && password.equals("Admin123")) {
            return "adminLogin";
        }
        for (int i = 0; i <= accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username)) {
                if (accountList.get(i).getPassword().equals(password)) {
                    currentUser = accountList.get(i);
                    return "loginSuccess";
                } else
                    return "wrongPassword";
            }
        }
        return "notExist";
    }

//        DISPLAY USERS
    public void displayUsers() {
        StringBuilder str;
        str = new StringBuilder();
        for (Account user : accountList) {
            str.append(user.toString());
            str.append(" ");
        }
    }

//    ORDER METHODS
    public static void returnItem(String orderID, String itemID) {
//        for (Order order : orderList) {
//            System.out.println(order);
//        }
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                order.returnItemInOrder(orderID, itemID);
//                for (Item item : stockList.keySet()) {
//                    if (item.getId().equals(itemID)) {
//                        stockList.put(item, itemList.get(item) + order.getOrder().get(item).getAmount());
//                        break;
//                    }
//                }
//                itemList.put(item, itemList.get(item) + order.getOrder().get(item).getAmount()); // return back to the stock
//                promote(order.getOwner(), order.getOrder().get(item).getAmount());
                break;
            }
        }
//        returnItem(order, item);
//        itemList.put(item, itemList.get(item) + order.get(item).getAmount());
//        promote(order.g);

    }

    public void makeOrder(Account account) {
        Order order = new Order(account); // make a list contain orderDetails
    }

    // DISPLAY ALL ITEMS IN THE STORE HAS
    public void displayAllItems() {
        StringBuilder str;
        str = new StringBuilder();
        for (Item item : itemStock) {
            str.append(item.toString());
            str.append(" ");
        }
    }

    // DISPLAY OUT OF STOCK ITEMS
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


}