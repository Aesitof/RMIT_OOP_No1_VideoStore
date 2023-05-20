package com.no1.geniestore.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
    protected static HashMap<Item, Integer> stockList = new HashMap<>();
    protected static ArrayList<Item> itemStock;

    public Stock() {
        itemStock = new ArrayList<Item>();
    }

    // ADD ITEMS TO THE STOCK
//    public void addStock(Item item, Integer stock){
//        if (stockList.get(item) != null) {
//            int countItem = stockList.get(item);
//            countItem = countItem + stock;
//            stockList.put(item, countItem);
//        } else {
//            stockList.put(item, stock);
//            itemStock.add(item);
//        }
//    }

    protected String getTheLatestID(){
            String latestID = "";
            Item latestItem = itemStock.get(itemStock.size() - 1);
            latestID = latestID + latestItem.getId();
            return latestID;
    }
}
