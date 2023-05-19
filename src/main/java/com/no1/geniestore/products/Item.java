package com.no1.geniestore.products;


import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item {
    private final ItemType itemType;
    private int year;
    private String id;
    private final String title;
    private LoanType loanType;
    private double rentalFee;
    private boolean rentalStatus;//Available or not Avalable
    private Genre genre;
//    private Integer totalCopies;
//    private Integer remainingCopies;



    public Item(ItemType itemType, int year, String title, LoanType loanType, double rentalFee, Genre genre, Integer totalCopies, Integer remainingCopies) {
        this.itemType = itemType;
        this.year = year;
        this.id = generateItemID();
        this.title = title;
        this.loanType = loanType;
        this.rentalFee = rentalFee;
        this.genre = genre;
//        this.totalCopies = totalCopies;
//        this.remainingCopies = remainingCopies;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public Genre getGenre() {
        return genre;
    }





    // only loanType, rentalFee, and genre can be updated

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    // Generate ID for Items
    public String latestItemID(){
        // take the latest ID in the item list (cho Anh tao Stock)
        String latestIDCode = null;
        return latestIDCode;
    }

    public String generateItemID(){
        String latestIDCode = latestItemID();
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

        return itemID;
    }

    public String checkStatus(String title){
        return "Available";
    } // return Available or Not Available

    public void stockArrive(Item item, int amount) {
        item.setTotalCopies(item.getTotalCopies() + amount);
    }
}

