package com.no1.geniestore.products;

import com.no1.geniestore.accounts.Account;

import java.util.HashMap;

public class ManageAccount extends Account {
        public static HashMap<String, String> accountList = new HashMap<>();
        public void addAccount(Account account) {
                accountList.put(account.getUsername(), account.getPassword());
        }
        public void deleteAccount(Account account) {
                accountList.remove(account.getUsername());
        }

}
