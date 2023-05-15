package com.no1.geniestore.products;

import com.no1.geniestore.constants.LoanType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Order implements OrderDetails{
    private HashSet<Item> orderedItems = new HashSet<Item>();
    private Date returnDate;
    private Date loanDate;
    private final static Integer dayLoan = 2;
    private final static Integer weekLoan = 7;

    public Order(HashSet<Item> orderedItems, Date returnDate, Date loanDate) {
        this.orderedItems = orderedItems;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
    }

    public void addOrderItem(Item item){

        orderedItems.add(item);

    }

    public Order(HashSet<Item> orderedItems, Date returnDate) {
        this.orderedItems = orderedItems;
        this.returnDate = returnDate;
        this.loanDate = new Date();
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

    @Override
    public void durationCalculate() {
        for(Item orderedItem : orderedItems) {
            if (orderedItem.getLoanType().equals(LoanType.TWO_DAY_LOAN)) {

            }

        }
    }
}
