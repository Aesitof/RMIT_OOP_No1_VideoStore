package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;

public class Regular extends Account {
    private static final String accountType = "Regular";
    private static final int itemsReturnedToBeVip = 5;
    public Regular(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, username, password);
    }
}


