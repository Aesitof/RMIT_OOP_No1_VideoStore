package com.no1.geniestore.products;

import java.util.HashMap;

public class ManageAccount {
        public static HashMap<String, String> accountList = new HashMap<>();
        public void addAccount(Account account) {
                accountList.put(account.getUsername(), account.getPassword());
        }
        public void deleteAccount(Account account) {
                accountList.remove(account.getUsername());
        }
}
