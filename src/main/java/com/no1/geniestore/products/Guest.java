package com.no1.geniestore.products;

public class Guest extends Account{

    private final static int itemsReturnedToBeRegular = 3;
    private final static int maxItemToRent = 2;
    private final static String accountType = "Guest";

    public Guest(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, username, password);
    }
}