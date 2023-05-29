package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.constants.LoanType;

import static com.no1.geniestore.products.ManagementSystem.*;
import static com.no1.geniestore.products.Stock.stockList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Order {
    private final static long weekLoan = 86400000 * 7;
    private final static long dayLoan = 86400000 * 2;
    private String orderID;
    private Account owner;
    HashMap<Item, OrderDetails> order = new HashMap<>(); // an order has multiple orderDetails
    public static int orderIdCounter; // total number of orderId created
    private double total = 0;
    private double totalDiscount = 0;

//    Constructor
    public Order() {
        this.orderID = generateOrderID();
        this.owner = null;
    }
    public Order(Account owner) {
        this.orderID = generateOrderID();
        this.owner = owner;
    }

    public Order(String orderID, Account owner, HashMap<Item, OrderDetails> order, double total, double totalDiscount) {
        this.orderID = orderID;
        this.owner = owner;
        this.order = order;
        this.total = total;
        this.totalDiscount = totalDiscount;
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

    public HashMap<Item, OrderDetails> getOrder() {
        return order;
    }


    public void addItemForRent(String itemID, Date loanDate, int amount, boolean isDiscountApplied, int discountApplied) {
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                for (Item item : stockList.keySet()) {
                    if (item.getId().equals(itemID)) {
                        if (amount <= stockList.get(item)) {
                            stockList.put(item, stockList.get(item) - amount); // change the remaining stock

                            OrderDetails orderDetail = new OrderDetails(); // create new item and its amount in order
                            orderDetail.setLoanDate(loanDate);
                            orderDetail.setAmount(amount);
                            long returnDate;

                            if (item.getLoanType().equals(LoanType.TWO_DAY_LOAN)) { // set return date based on loan type
                                returnDate = orderDetail.getLoanDate().getTime() + dayLoan;
                            } else {
                                returnDate = orderDetail.getLoanDate().getTime() + weekLoan;
                            }

                            orderDetail.setReturnDate(new Date(returnDate)); // set return date to order details

                            order.getOrder().put(item, orderDetail); // add order details to order

                            if (isDiscountApplied) { // only if customer use reward points
                                for (Account account : accountList) {
                                    if (order.getOwner().getId().equals(account.getId())) {
                                        useRewardPoints(account);;
                                    }
                                }
//                                for (int i = 0; i < discountApplied; i++) { // loop for number of discount of that item
//                                    useRewardPoints(order.getOwner());
//                                }

                                order.getOrder().get(item).setDiscount(item.getRentalFee() * discountApplied); // discount for each item in order
                                order.setTotalDiscount(getTotalDiscount() + item.getRentalFee() * discountApplied); // total discount for that order
                            }

                            order.getOrder().put(item, orderDetail); // add order details to order
                            setTotal(getTotal() + item.getRentalFee() * amount - order.getOrder().get(item).getDiscount()); // add fee to order's total
                            break; // stop the loop when order details are all written
                        }
                    }
                }
                break; // stop the loop
            }
        }
    }
    public void removeItem(Item item) {
        order.remove(item);
    }

    public double returnItemInOrder(String orderID, String itemID) {
        double lateReturnFee = 0;
        for (Order order : orderList) {
            // find the order want to return
            if (order.getOrderID().equals(orderID)) {
                for (Item item : order.getOrder().keySet()) {
                    // find the singleItem in the order (to setReturned true) + get the Amount to add back to remaining in stockList
                    if (item.getId().equals(itemID)) {
                        // set isReturned = true
                        order.getOrder().get(item).setReturned(true);
                        // add item back to remaining
                        for (Item singleItem : stockList.keySet()) {
                            // find item in stock list to remaining
                            if (singleItem.getId().equals(itemID)) {
                                // return all amount of that item at the same time
                                stockList.put(singleItem, stockList.get(singleItem) + order.getOrder().get(item).getAmount());
//                                order.getOrder().get(item).setReturned(true);
                                break;
                            }
                        }
                        // calculate penalty fee
                        Calendar calendar = Calendar.getInstance();
                        if (calendar.getTimeInMillis() > order.getOrder().get(item).getReturnDate().getTime())  {
                            int dayLate = (int) ((calendar.getTimeInMillis() - order.getOrder().get(item).getReturnDate().getTime()) / 1000 / 60 / 60 / 24);
                            lateReturnFee = order.getOrder().get(item).getAmount() * item.getRentalFee() * 3.0 / 10 * dayLate;
                        }
                        order.setTotal(order.getTotal() + lateReturnFee);
                        // update owner's total returned items
                        for (Account account : accountList) {
                            if (order.getOwner().getId().equals(account.getId())) {
                                int returnedItem = order.getOrder().get(item).getAmount();
                                int totalReturnedItemsBefore = account.getTotalReturnedItems();
                               account.setTotalReturnedItems(account.getTotalReturnedItems() + order.getOrder().get(item).getAmount());
//                                System.out.println(returnedItem);
                                promoteReturn(account, returnedItem, totalReturnedItemsBefore);
                            }
                        }
                    }

                }
                break;
            }
        }
        return lateReturnFee; // use for show penalty fee dialog
    }

    public static void useRewardPoints(Account account) {
        for (Account e : accountList) {
            if (account.getId().equals(e.getId())) {
                if (account.getRewardPoints() >= 100) { // if only have enough points
                    account.setRewardPoints(account.getRewardPoints() - 100);
                    break;
                }
            }
        }
    }
    public String generateOrderID() {
        orderIdCounter++; // raise value of number of orderId by 1
        orderID = String.valueOf(orderIdCounter);
        return orderID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", owner=" + owner +
                ", order=" + order +
                ", total=" + total +
                ", totalDiscount=" + totalDiscount +
                '}';
    }
}
