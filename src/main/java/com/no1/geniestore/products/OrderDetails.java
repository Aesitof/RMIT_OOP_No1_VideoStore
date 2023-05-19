package com.no1.geniestore.products;

import java.util.Date;

public class OrderDetails {
    private Date returnDate;
    private Date loanDate;
    private int amount;
    private boolean isReturned;

    public OrderDetails(Date returnDate, Date loanDate, int amount) {
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.amount = amount;
        this.isReturned = false;
    }
    public OrderDetails() {
        this.returnDate = new Date(System.currentTimeMillis());
        this.loanDate = new Date(System.currentTimeMillis());
        this.amount = 0;
        this.isReturned = false;
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

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}


//public interface OrderDetails {
//    public void durationCalculate();
//}
