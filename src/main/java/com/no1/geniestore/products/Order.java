package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.constants.LoanType;

import static com.no1.geniestore.products.ManagementSystem.orderList;
import static com.no1.geniestore.products.Stock.itemStock;
import static com.no1.geniestore.products.Stock.stockList;

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

    public void addItemForRent(Item item, Date loanDate, int amount, boolean isDiscountApplied, int discountApplied) {
        if (amount <= stockList.get(item)) { // current stock in store must have enough amount
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

            if (isDiscountApplied) { // only if customer use reward points
                for (int i = 0; i < discountApplied; i++) { // loop for number of discount of that item
                    useRewardPoints(owner);
                }
                order.get(item).setDiscount(item.getRentalFee() * discountApplied); // discount for each item in order
                setTotalDiscount(getTotalDiscount() + item.getRentalFee() * discountApplied); // total discount for that order
            }

            order.put(item, orderDetail); // add order details to order
            setTotal(getTotal() + item.getRentalFee() * amount - order.get(item).getDiscount()); // add fee to order's total
        }
    }

    public void removeItem(Item item) {
        order.remove(item);
    }

    public void returnItemInOrder(String orderID, String itemID) {
        // update amount of item in order has been returned
//        order.get(item).setAmount(order.get(item).getAmount() - amount);
        // add returned item back to current stock available in store
//        stockList.put(item, stockList.get(item) + order.get(item).getAmount()); // return all the same time
        // add amount to total returned items of that customer
//        getOwner().setTotalReturnedItems(getOwner().getTotalReturnedItems() + order.get(item).getAmount());


//        if (order.get(item).getAmount() == 0) { // if all items have been returned
//            order.get(item).setReturned(true); // status: pending -> finished
//        }
        for (Order order : orderList) {
            if (order.getOrderID().equals(orderID)) {
                for (Item item : order.getOrder().keySet()) {
                    if (item.getId().equals(itemID)) {
                        for (Item singleItem : stockList.keySet()) {
                            if (singleItem.getId().equals(itemID)) {
                                stockList.put(singleItem, stockList.get(singleItem) + order.getOrder().get(singleItem).getAmount());
                                break;
                            }
                        }
                    }
                    order.getOwner().setTotalReturnedItems(order.getOwner().getTotalReturnedItems() + order.getOrder().get(item).getAmount());
                    order.getOrder().get(item).setReturned(true);
                }

//                for (Item i : stockList.keySet()){
//                    if (i.getId().equals(item.getId())) {
//                        stockList.put(i, stockList.get(i) + order.getOrder().get(i).getAmount()); // return all at the same time
//                        getOwner().setTotalReturnedItems(getOwner().getTotalReturnedItems() + order.getOrder().get(item).getAmount());
//                        order.getOrder().get(item).setReturned(true);
//                    }
//                }
//                stockList.put(item, stockList.get(item) + order.getOrder().get(item).getAmount()); // return all at the same time
//                getOwner().setTotalReturnedItems(getOwner().getTotalReturnedItems() + order.getOrder().get(item).getAmount());
//                order.getOrder().get(item).setReturned(true);
            }
        }
    }

    public void useRewardPoints(Account account) {
        if (account.getRewardPoints() >= 100) { // if only have enough points
            account.setRewardPoints(account.getRewardPoints() - 100);
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
