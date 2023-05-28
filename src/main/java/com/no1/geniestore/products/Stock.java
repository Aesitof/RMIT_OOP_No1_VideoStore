package com.no1.geniestore.products;

import java.util.HashMap;

public class Stock {
    public static HashMap<Item, Integer> stockList = new HashMap<>(); // current stock
    public static int itemIdCounter; // count total number of itemId created
}
