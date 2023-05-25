package com.no1.geniestore.controllers;

import com.no1.geniestore.constants.ItemType;

public class RentalData {
    private String orderId;
    private String rentId;
    private String rentTitle;
    private ItemType rentItemType;
    private int rentQty;
    private double rentFee;
    private String rentDueDate;
    private String rentStatus;

    public RentalData(String orderId, String rentId, String rentTitle, ItemType rentItemType, int rentQty, double rentFee, String rentDueDate, String rentStatus) {
        this.orderId = orderId;
        this.rentId = rentId;
        this.rentTitle = rentTitle;
        this.rentItemType = rentItemType;
        this.rentQty = rentQty;
        this.rentFee = rentFee;
        this.rentDueDate = rentDueDate;
        this.rentStatus = rentStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRentId() {
        return rentId;
    }

    public String getRentTitle() {
        return rentTitle;
    }

    public ItemType getRentItemType() {
        return rentItemType;
    }

    public int getRentQty() {
        return rentQty;
    }

    public double getRentFee() {
        return rentFee;
    }

    public String getRentDueDate() {
        return rentDueDate;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    @Override
    public String toString() {
        return "RentalData{" +
                "rentId='" + rentId + '\'' +
                ", rentTitle='" + rentTitle + '\'' +
                ", rentItemType=" + rentItemType +
                ", rentQty=" + rentQty +
                ", rentFee=" + rentFee +
                ", rentDueDate='" + rentDueDate + '\'' +
                ", rentStatus='" + rentStatus + '\'' +
                '}';
    }
}
