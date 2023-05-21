package com.no1.geniestore.products;


import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import  com.no1.geniestore.products.Stock;

public class Item extends Stock {
    private String id;
    private final String title;
    private int year;
    private final ItemType itemType;
    private Genre genre;
    private LoanType loanType;
    private double rentalFee;
    private String image;

    
    public Item(String title, int year, ItemType itemType, Genre genre, LoanType loanType, double rentalFee){
        this.itemType = itemType;
        this.year = year;
        this.id = generateItemID();
        this.title = title;
        this.loanType = loanType;
        this.rentalFee = rentalFee;
        this.genre = genre;
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

    public String generateItemID() {
        String latestIDCode = getTheLatestID();
        int code = Integer.parseInt(latestIDCode);
        code = code + 1;

        String itemID = "I";

        if (code >= 1 && code <= 9) {
//            itemID = itemID + "00" + code + "-" + getYear();
            itemID = String.format("%s%s%s%s%s", itemID, "00", code, "-", getYear());
        } else if (code >= 10 && code <= 99) {
//            itemID = itemID + "0" + code + "-" + getYear();
            itemID = String.format("%s%s%s%s%s", itemID, "0", code, "-", getYear());
        } else if (code >= 100 && code <= 999) {
//            itemID = itemID + "" + code + "-" + getYear();
            itemID = String.format("%s%s%s%s%s", itemID, "", code, "-", getYear());
        }

        return itemID;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", itemType=" + itemType +
                ", genre=" + genre +
                ", rentalFee=" + rentalFee +
                ", loanType=" + loanType +
                '}';
    }
}

