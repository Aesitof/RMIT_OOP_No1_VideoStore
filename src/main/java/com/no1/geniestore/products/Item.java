package com.no1.geniestore.products;


import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;

import java.util.ArrayList;
import java.util.Scanner;

public class Item extends StockChau{
    // ATTRIBUTES OF EACH ITEM
    private ItemType itemType;
    private int year;
    private String id;
    private String title;
    private LoanType loanType;
    private double rentalFee;
    private boolean rentalStatus;//Available or not Avalable
    private Genre genre;
    private ArrayList<Item> itemStock;
//    private int totalCopies;
//    private Integer remainingCopies; // tam thoi khong nhac toi
//    private List<Item> itemList = new ArrayList<>(); //Quăng vô managesystem

    // CONSTRUCTORS
    public Item(ItemType itemType, int year, String id, String title, LoanType loanType, double rentalFee, boolean rentalStatus, Genre genre) {
        this.itemType = itemType;
        this.year = year;
        this.id = id;
        this.title = title;
        this.loanType = loanType;
        this.rentalFee = rentalFee;
        this.rentalStatus = rentalStatus;
        this.genre = genre;
//        this.totalCopies = totalCopies;
    }

    // DEFAULT CONSTRUCTORS
    public Item(){
        this.itemType = null;
        this.year = 0;
        this.id = null;
        this.title = null;
        this.loanType = null;
        this.rentalFee = 0;
        this.rentalStatus = false;
        this.genre = null;
//        this.totalCopies = 0;
    }


    // BASIC GETTERS AND SETTERS
    public ItemType getItemType() {
        return itemType;
    }

    public int getYear() {
        return year;
    }

    public String getId() {return id; }

    public String getTitle() {
        return title;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public String getID(){ return id;}

    public double getRentalFee() {
        return rentalFee;
    }

    // de sau
    /*public boolean isRentalStatus() {
        return rentalStatus;
    } */

    public Genre getGenre() {
        return genre;
    }

    // de sau
    /*public void removeItem(String id){
        itemList.removeIf(item -> item.id.equals(id));
    }*/

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

//    public void setTotalCopies(int totalCopies) {
//        this.totalCopies = totalCopies;
//    }

    public void setItemType(ItemType itemType){
        this.itemType = itemType;
    }

    public void setID(String id) {this.id = id;}

    public void setTitle(String title) {
        this.title = title;
    }



    // Generate ID for Items

    public String generateItemID(){

        // need to create a situation when a there is no item yet in the stock

        String latestIDCode = getTheLatestID();
        int code = Integer.parseInt(latestIDCode);
        code = code + 1;

        String itemID = "I";

        if (code >= 1 && code <= 9){
//            itemID = itemID + "00" + code + "-" + getYear();
            itemID = String.format("%s%s%s%s%s", itemID, "00", code, "-", getYear());
        }
        else if (code >= 10 && code <= 99){
//            itemID = itemID + "0" + code + "-" + getYear();
            itemID = String.format("%s%s%s%s%s", itemID, "0", code, "-", getYear());
        }
        else if (code >= 100 && code <= 999){
//            itemID = itemID + "" + code + "-" + getYear();
            itemID = String.format("%s%s%s%s%s", itemID, "", code, "-", getYear());
        }
        setID(itemID);
        return latestIDCode;
    }

    public void addItem(){ //
        Item item = new Item();
        Scanner scan = new Scanner(System.in);

        ItemType inputItemType = ItemType.valueOf(scan.nextLine());
        item.setItemType(inputItemType);

        int inputYear = scan.nextInt(); scan.nextLine();
        item.setYear(inputYear);


        String inputTitle = scan.nextLine();
        item.setTitle(inputTitle);

        LoanType inputLoanType = LoanType.valueOf(scan.nextLine());
        item.setLoanType(inputLoanType);

        item.setID(generateItemID());

        double inputRentalFee = scan.nextDouble(); scan.nextLine();
        item.setRentalFee(inputRentalFee);

        // con thang rentalStatus, quay lai sau

        Genre inputGenre = Genre.valueOf(scan.nextLine());
        item.setGenre(inputGenre);

        itemStock.add(item);


    }

    public String checkStatus(String title){
        return "Available";
    } // return Available or Not Available


}

