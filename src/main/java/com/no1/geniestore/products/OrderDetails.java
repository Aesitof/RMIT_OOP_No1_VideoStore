package com.no1.geniestore.products;

import java.util.Date;

public class OrderDetails {
    private Date returnDate;
    private Date loanDate;
    private int amount;

    public OrderDetails(Date returnDate, Date loanDate, int amount) {
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.amount = amount;
    }
    public OrderDetails() {
        this.returnDate = new Date(System.currentTimeMillis());
        this.loanDate = new Date(System.currentTimeMillis());
        this.amount = 0;
    }

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
}

//public interface OrderDetails {
//    public void durationCalculate();
//}
