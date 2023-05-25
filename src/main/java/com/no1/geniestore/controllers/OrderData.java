package com.no1.geniestore.controllers;

public class OrderData {
    private String orderID;
    private String customerID;
    private String customerName;
    private String date;
    private double discount;
    private double total;

    public OrderData(String orderID, String customerID, String customerName, String date, double discount, double total) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.date = date;
        this.discount = discount;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", date='" + date + '\'' +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}
