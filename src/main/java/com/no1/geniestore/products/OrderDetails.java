package com.no1.geniestore.products;

import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;

public class OrderDetails {
    private Date returnDate;
    private Date loanDate;
    private int amount;
    private boolean isReturned;
    private double discount;

//    Constructor
    public OrderDetails(Date returnDate, Date loanDate, int amount, double discount) {
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.amount = amount;
        this.isReturned = false;
        this.discount = discount;
    }

//    Default Constructor
    public OrderDetails() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
//        this.returnDate = new Date(System.currentTimeMillis());
//        this.loanDate = new Date(System.currentTimeMillis());
        this.returnDate = calendar.getTime();
        this.loanDate = calendar.getTime();
        this.amount = 0;
        this.isReturned = false;
        this.discount = 0;
    }

//    Getter and Setter
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
