package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.constants.LoanType;
import static com.no1.geniestore.products.Stock.stockList;
import com.no1.geniestore.products.ManagementSystem;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;

public class Order {
    private final static long weekLoan = 86400000 * 7;
    private final static long dayLoan = 86400000 * 2;
    private String orderID;
    private Account owner;
    HashMap<Item, OrderDetails> order = new HashMap<>();//One order has multiple orderDetails
    private static int idCounter = 0;
    private double total = 0;
    private double totalDiscount = 0;

//    Constructor
    public Order(Account owner) {
        this.orderID = generateOrderID();
        this.owner = owner;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void addItemForRent(Item item, Date loanDate, int amount, boolean isDiscountApplied) {
//        long currentTimeMillis = System.currentTimeMillis();
        if (amount <= stockList.get(item)) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setLoanDate(loanDate);
            orderDetail.setAmount(amount);
            long returnDate;

            if (item.getLoanType().equals(LoanType.TWO_DAY_LOAN)) { // set return date based on loan type
                returnDate = orderDetail.getLoanDate().getTime() + dayLoan;
            } else {
                returnDate = orderDetail.getLoanDate().getTime() + weekLoan;
            }

            orderDetail.setReturnDate(new Date(returnDate)); // set return date to order details

            if (isDiscountApplied) {
                useRewardPoints(owner);
                order.get(item).setDiscount(item.getRentalFee()); // discount for each item in order
                setTotalDiscount(getTotalDiscount() + item.getRentalFee()); // total discount for that order
            }
            order.put(item, orderDetail);
            setTotal(getTotal() + item.getRentalFee() * amount - order.get(item).getDiscount()); // add fee to total

//             change the remaining stock
            int stockRemaining = stockList.get(item) - amount;
            stockList.put(item,stockRemaining);
        }

    }

    public void removeItem(Item item) {
        order.remove(item);
    }

    public void returnItemInOrder(Item item, int amount) {
        order.get(item).setAmount(order.get(item).getAmount() - amount);

//        add returned item back to stock
        stockList.put(item, stockList.get(item) + amount);
//        add amount to total returned items
        getOwner().setTotalReturnedItems(getOwner().getTotalReturnedItems() + amount);

//        if all items have been returned
        if (order.get(item).getAmount() == 0) {
            order.get(item).setReturned(true); // status: pending -> finished
        }
    }

    public void useRewardPoints(Account account) {
        if (account.getRewardPoints() >= 100) {
            account.setRewardPoints(account.getRewardPoints() - 100);
        }
    }
    public String generateOrderID() {
        idCounter++;
        orderID = "" + idCounter;
        return orderID;
    }


}
