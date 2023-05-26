package com.no1.geniestore.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
    public static HashMap<Item, Integer> stockList = new HashMap<>(); // current stock
    protected static ArrayList<Item> itemStock = new ArrayList<>(); // List of items
    public static int itemIdCounter; // count total number of itemId created

    protected String getTotalNumItem() {
        String latestID = String.valueOf(itemIdCounter);
        itemIdCounter++; // add number of itemId by 1
        return latestID;
    }
}
