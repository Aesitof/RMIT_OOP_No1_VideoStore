package com.no1.geniestore.controllers;

import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.products.Item;

public class ItemData {
    private String id;
    private String title;
    private int year;
    private ItemType itemType;
    private Genre genre;
    private LoanType loanType;
    private double rentalFee;
    private String image;
    private int totalCopies;
    private int remainingCopies;

    public ItemData(String id, String title, int year, ItemType itemType, Genre genre, LoanType loanType, double rentalFee, String image, int totalCopies, int remainingCopies) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.itemType = itemType;
        this.genre = genre;
        this.loanType = loanType;
        this.rentalFee = rentalFee;
        this.image = image;
        this.totalCopies = totalCopies;
        this.remainingCopies = remainingCopies;
    }
    
    public ItemData(Item item, int totalCopies, int remainingCopies) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.year = item.getYear();
        this.itemType = item.getItemType();
        this.genre = item.getGenre();
        this.loanType = item.getLoanType();
        this.rentalFee = item.getRentalFee();
        this.image = item.getImage();
        this.totalCopies = totalCopies;
        this.remainingCopies = remainingCopies;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Genre getGenre() {
        return genre;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public String getImage() {
        return image;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getRemainingCopies() {
        return remainingCopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setRemainingCopies(int remainingCopies) {
        this.remainingCopies = remainingCopies;
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", itemType=" + itemType +
                ", genre=" + genre +
                ", loanType=" + loanType +
                ", rentalFee=" + rentalFee +
                ", image='" + image + '\'' +
                ", totalCopies=" + totalCopies +
                ", remainingCopies=" + remainingCopies +
                '}';
    }
}
