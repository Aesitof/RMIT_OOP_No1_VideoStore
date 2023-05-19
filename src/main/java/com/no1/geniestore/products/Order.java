package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.constants.LoanType;

import java.util.Date;
import java.util.HashMap;



public class Order {
    private final static long weekLoan = 86400000 * 7;
    private final static long dayLoan = 86400000 * 2;
    private String orderID;
    private Account owner;
    HashMap<Item, OrderDetails> order = new HashMap<>();
    private static int idCounter = 0;

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

    public void addItemForRent(Item item, Date loanDate, int amount) {
        long currentTimeMillis = System.currentTimeMillis();

        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setLoanDate(loanDate);
        orderDetail.setAmount(amount);
        long returnDate;
        if (item.getLoanType().equals(LoanType.TWO_DAY_LOAN)) {
            returnDate = orderDetail.getLoanDate().getTime() + dayLoan;
        } else {
            returnDate = orderDetail.getLoanDate().getTime() + weekLoan;
        }
        orderDetail.setReturnDate(new Date(returnDate));//chuyển về lại Date

        order.put(item, orderDetail);
    }
    public void removeItem(Item item) {
        order.remove(item);
    }
    public void returnItemInOrder(Item item, int amount) {
        order.get(item).setAmount(order.get(item).getAmount() - amount);
        item.setRemainingCopies(item.getRemainingCopies() + amount);//trả về kho
        if (order.get(item).getAmount() == 0) {
            order.get(item).setReturned(true);
            getOwner().setTotalReturnedItems(getOwner().getTotalReturnedItems() + 1); //Not sure about this
        }
    }

    public String generateOrderID() {
        idCounter++;
        orderID = "" + idCounter;
        return orderID;
    }

}

//public class Order implements OrderDetails{
//    private HashSet<Item> orderedItems = new HashSet<Item>();
//    private Date returnDate;
//    private Date loanDate;
//    private final static Integer dayLoan = 2;
//    private final static Integer weekLoan = 7;
//
//    public Order(HashSet<Item> orderedItems, Date returnDate, Date loanDate) {
//        this.orderedItems = orderedItems;
//        this.returnDate = returnDate;
//        this.loanDate = loanDate;
//    }
//
//    public void addOrderItem(Item item){
//
//        orderedItems.add(item);
//
//    }
//
//    public Order(HashSet<Item> orderedItems, Date returnDate) {
//        this.orderedItems = orderedItems;
//        this.returnDate = returnDate;
//        this.loanDate = new Date();
//    }
//
//    public Date getReturnDate() {
//        return returnDate;
//    }
//
//    public void setReturnDate(Date returnDate) {
//        this.returnDate = returnDate;
//    }
//
//    public Date getLoanDate() {
//        return loanDate;
//    }
//
//    public void setLoanDate(Date loanDate) {
//        this.loanDate = loanDate;
//    }
//
//    @Override
//    public void durationCalculate() {
//        for(Item orderedItem : orderedItems) {
//            if (orderedItem.getLoanType().equals(LoanType.TWO_DAY_LOAN)) {
//
//            }
//
//        }
//    }
//}
