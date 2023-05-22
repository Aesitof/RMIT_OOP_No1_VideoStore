package com.no1.geniestore.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.products.Item;

public class CartData {
    private Item item;
    private int remaining;
    private int qty;
    private Date returnDate;
    private double itemFee;

//    public CartData() {
//        this.item = new Item()
//    }
    public CartData(Item item, int remaining, int qty, Date returnDate, double itemFee) {
        this.item = item;
        this.remaining = remaining;
        this.qty = qty;
        this.returnDate = returnDate;
        this.itemFee = itemFee;
    }

    public CartData(Item item, int remaining) {
        this.item = item;
        this.remaining = remaining;
        this.qty = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 2);
        this.returnDate = calendar.getTime();
        this.itemFee = getItem().getRentalFee();
    }

    public Item getItem() {
        return item;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getQty() {
        return qty;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public double getItemFee() {
        return itemFee;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void resetItemFee() {
        this.itemFee = getItem().getRentalFee() * this.qty;
    }

    @Override
    public String toString() {
        return "CartData{" +
                "item=" + item +
                ", remaining=" + remaining +
                ", qty=" + qty +
                ", returnDate=" + returnDate +
                ", itemFee=" + itemFee +
                '}';
    }
}
