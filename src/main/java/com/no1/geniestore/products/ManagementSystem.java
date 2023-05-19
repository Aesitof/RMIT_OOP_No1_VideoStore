package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;

import java.util.ArrayList;
import java.util.HashMap;

import static com.no1.geniestore.products.Stock.itemStock;

public class ManagementSystem {
    private ArrayList<Account> accountList;
    private ArrayList<Order> orderList;//TAO CLASS ORDER BO SUNG
    private HashMap<Item, Integer> itemList;
    private Account currentUser;

    public ManagementSystem() {
        this.accountList = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.itemList = new HashMap<>();
        this.currentUser = null;
    }

//ITEM METHODS
    public void addItem(Item item, Integer stock) {
        if (itemList.get(item) != null) {
            int countItem = itemList.get(item);
            countItem += stock;
            itemList.put(item, countItem);
        } else {
            itemList.put(item, stock);
            itemStock.add(item);
        }
    }
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    //ACCOUNTS METHODS
    public void addAccount(Account account) {
        accountList.add(account);
    }
    public void removeAccount(String accountID) {//Remove using ID
        accountList.removeIf(i -> i.getId().equals(accountID));
    }
    public void promote(Account account) {
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
    public void returnItem(String orderID, Item item, int amount) {
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                order.returnItemInOrder(item, amount);
            }
        }
    }
    public void makeOrder(Account account) {
        Order order = new Order(account);
    }
}
