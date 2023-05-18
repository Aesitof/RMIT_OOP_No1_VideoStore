package com.no1.geniestore.products;


import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;

public class Item {
    private final ItemType itemType;
    private final int year;
    private String id;
    private final String title;
    private LoanType loanType;
    private double rentalFee;
    private boolean rentalStatus;
    private Genre genre;
    private String image;

    public Item(ItemType itemType, int year, String id, String title, LoanType loanType, double rentalFee, boolean rentalStatus, Genre genre) {
        this.itemType = itemType;
        this.year = year;
        this.id = id;
        this.title = title;
        this.loanType = loanType;
        this.rentalFee = rentalFee;
        this.rentalStatus = rentalStatus;
        this.genre = genre;
    }
    
    public String getImage(){
        return image;
    }
    
    public void setImage(String image){
        this.image = image;
    }
    
    public ItemType getItemType() {
        return itemType;
    }

    public int getYear() {
        return year;
    }

    public String getId() {
        return id;
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

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public Genre getGenre() {
        return genre;
    }

    public void addItem(){}

    public void removeItem(){}

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


//    public String generateID(int year){} // generate the id based on the year

    public String checkStatus(String title){
        return "Available";
    } // return Available or Not Available

}

