package com.no1.geniestore.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
    protected static HashMap<Item, Integer> stockList = new HashMap<>(); // current stock
    protected static ArrayList<Item> itemStock = new ArrayList<>(); // List of items using to generate itemID

//    get latest itemID by itemStock
    protected String getTheLatestID(){
            String latestID = "";
            Item latestItem = itemStock.get(itemStock.size() - 1);
            latestID = latestID + latestItem.getId();
            return latestID;
    }
}
