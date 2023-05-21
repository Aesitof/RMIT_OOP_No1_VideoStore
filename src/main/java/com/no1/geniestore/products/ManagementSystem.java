package com.no1.geniestore.products;

import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.accounts.Account;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.no1.geniestore.products.Stock.*;

public class ManagementSystem {
    private static ArrayList<Account> accountList;
    private static ArrayList<Order> orderList;//TAO CLASS ORDER BO SUNG
    private static HashMap<Item, Integer> itemList; // Total item copies
    private static Account currentUser;

    public ManagementSystem() {
        this.accountList = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.itemList = new HashMap<>();
        this.currentUser = null;
    }

    public static void main() throws ParserConfigurationException, IOException, SAXException {
        itemList = new ItemListParser().parseItemTotal("xml/items.xml");
        stockList = new ItemListParser().parseStockList("xml/items.xml");
    }

    //ITEM METHODS
    public void addItem(Item item, Integer stock) {//Add Item to the stock
        if (itemList.get(item) != null) { // if already have that item in itemList
            // add to total
            int countItem = itemList.get(item);
            countItem += stock;
            itemList.put(item, countItem);

            // add to current stock
            int countStock = stockList.get(item) + stock;
            stockList.put(item, countStock);
        } else { // if the item is completely brand new
            itemList.put(item, stock);
            itemStock.add(item); // add new item in itemStock using for generate id
            stockList.put(item, stock); // add stock to current stock
        }
    }
    public void stockArrive(Item item, int amount) {
        itemList.put(item, itemList.get(item) + amount);
    }
    public void removeItem(Item item) {
        itemList.remove(item);
    }//Remove item including it's stock

    //ACCOUNTS METHODS
    public void addAccount(Account account) {
        accountList.add(account);
    }//Add accounts to the list
    public void removeAccount(String accountID) {//Remove account using ID
        accountList.removeIf(i -> i.getId().equals(accountID));
    }//Remove accounts from the list

    public void promote(Account account) {//Auto promote whenever return item
        if (account.getAccountType().equals("Guest")
            && account.getTotalReturnedItems() == 3) {
            account.setAccountType("Regular");
        } else if (account.getAccountType().equals("Regular")
            && account.getTotalReturnedItems() == 8) {
            account.setAccountType("Vip");
        } else {
            account.setRewardPoints(account.getRewardPoints() + 10);//If a Vip, then +10 points
        }
    }

    //ORDER METHODS
    public void returnItem(String orderID, Item item, int amount) {//Return SINGLE item with SPECIFIED amount
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                order.returnItemInOrder(item, amount);
                itemList.put(item, itemList.get(item) + amount);// return back to the stock
                promote(order.getOwner());
            }
        }
    }
    public void makeOrder(Account account) {
        Order order = new Order(account);
    }//make a list contain orderDetails

//    DISPLAY ALL ITEMS IN THE STORE HAS
    public void displayAllItems() {
        for (Item item : itemStock){
            item.toString();
        }
    }

//    DISPLAY OUT OF STOCK ITEMS
    public void displayOutOfStock() {
        for (Item item : itemStock) {
            if (stockList.get(item) == 0) {
                item.toString();
            }
        }
    }
}
