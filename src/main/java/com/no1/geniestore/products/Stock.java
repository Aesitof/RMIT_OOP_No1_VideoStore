package com.no1.geniestore.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
    public static HashMap<Item, Integer> stockList = new HashMap<>(); // current stock
    protected static ArrayList<Item> itemStock = new ArrayList<>(); // List of items using to generate itemID
    public static int itemIdCounter;


    //    get latest itemID by itemStock
    protected String getTheLatestID() {
        String latestID = "";
        latestID += itemIdCounter;
        itemIdCounter++;
        return latestID;
    }
}
