package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;

import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {
    private List<Item> itemList = new ArrayList<>();
    private ArrayList<Account> accountList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<Order>();//TAO CLASS ORDER BO SUNG


    public void addItem(Item item) {
        itemList.add(item);
    }
    public void removeItem(Item item) {
        itemList.remove(item);
    }

}
