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
    private boolean freeItem;
    private int freeItemQty;

//    public CartData() {
//        this.item = new Item()
//    }
    public CartData(Item item, int remaining, int qty, Date returnDate, double itemFee) {
        this.item = item;
        this.remaining = remaining;
        this.qty = qty;
        this.returnDate = returnDate;
        this.itemFee = itemFee;
        this.freeItem = false;
        this.freeItemQty = 0;
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
        if (item.getLoanType().equals(LoanType.TWO_DAY_LOAN)) {
            calendar.add(Calendar.DATE, 2);
        } else {
            calendar.add(Calendar.DATE, 7);
        }
        this.returnDate = calendar.getTime();
        this.itemFee = getItem().getRentalFee();
        this.freeItem = false;
        this.freeItemQty = 0;
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

    public boolean isFreeItem() {
        return freeItem;
    }

    public int getFreeItemQty() {
        return freeItemQty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void resetItemFee() {
        this.itemFee = getItem().getRentalFee() * this.qty;
    }

    public void setFreeItem(boolean freeItem) {
        this.freeItem = freeItem;
    }

    public void setFreeItemQty(int freeItemQty) {
        this.freeItemQty = freeItemQty;
    }

    public double itemDiscount() {
        return getItem().getRentalFee() * this.freeItemQty;
    }

    @Override
    public String toString() {
        return "CartData{" +
                "item=" + item +
                ", remaining=" + remaining +
                ", qty=" + qty +
                ", returnDate=" + returnDate +
                ", itemFee=" + itemFee +
                ", freeItem=" + freeItem +
                ", freeItemQty=" + freeItemQty +
                '}';
    }
}
