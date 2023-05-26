package com.no1.geniestore.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
    public static HashMap<Item, Integer> stockList = new HashMap<>(); // current stock
    protected static ArrayList<Item> itemStock = new ArrayList<>(); // List of items using to generate itemID
    public static int removeItem = 0;


    //    get latest itemID by itemStock
    protected String getTheLatestID() {
//        String latestID = "";
//        if (itemStock.size() == 0) {
//            return "10";
//        } else {
//            Item latestItem = itemStock.get(itemStock.size() - 1);
//            return latestID + latestItem.getId();
//        }
        String latestID = "";
        latestID += stockList.size() + removeItem;
        return latestID;
    }
}
