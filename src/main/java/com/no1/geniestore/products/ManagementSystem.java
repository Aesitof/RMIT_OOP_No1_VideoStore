package com.no1.geniestore.products;

import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;

import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {
    private List<Item> itemList = new ArrayList<>();
    private ArrayList<Account> accountList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<Order>();//TAO CLASS ORDER BO SUNG


    private void addItem(Item item) {
        itemList.add(item);
    }
    private  void removeItem(Item item) {
        itemList.remove(item);
    }

}
