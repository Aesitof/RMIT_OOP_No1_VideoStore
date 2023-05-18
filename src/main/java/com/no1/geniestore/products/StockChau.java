package com.no1.geniestore.products;

import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StockChau {
    private int count;
    private HashMap<String, Integer> itemAndQuantity = new HashMap<>();
    private ArrayList<Item> itemStock;

    public StockChau() {
        itemStock = new ArrayList<Item>();
    }

    // ADD ITEMS TO THE STOCK
//    public void addItem(){
//        Item item = new Item();
//        Scanner scan = new Scanner(System.in);
//
//        ItemType inputItemType = ItemType.valueOf(scan.nextLine());
//        item.setItemType(inputItemType);
//
//        int inputYear = scan.nextInt(); scan.nextLine();
//        item.setYear(inputYear);
//
//
//        String inputTitle = scan.nextLine();
//        item.setTitle(inputTitle);
//
//        LoanType inputLoanType = LoanType.valueOf(scan.nextLine());
//        item.setLoanType(inputLoanType);
//
//        item.setID(generateItemID());
//
//        double inputRentalFee = scan.nextDouble(); scan.nextLine();
//        item.setRentalFee(inputRentalFee);
//
//        // con thang rentalStatus, quay lai sau
//
//        Genre inputGenre = Genre.valueOf(scan.nextLine());
//        item.setGenre(inputGenre);
//
//        itemStock.add(item);
//
//        count = count + 1;
//
//    }

    protected String getTheLatestID(){
            String latestID = "";
            Item latestItem = itemStock.get(itemStock.size() - 1);
            latestID = latestID + latestItem.getID();
            return latestID;
        }




}
