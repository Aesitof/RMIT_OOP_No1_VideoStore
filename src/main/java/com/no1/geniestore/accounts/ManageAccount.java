//package com.no1.geniestore.accounts;
//
//import com.no1.geniestore.accounts.Account;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class ManageAccount extends Account {
//    public static HashMap<String, String> accountList = new HashMap<>(); // store username, password
//    public static ArrayList<Account> account = new ArrayList<>();
//
//    public void addAccount(Account account) {
//        accountList.put(account.getUsername(), account.getPassword());
//    }
//
//    public void deleteAccount(Account account) {
//        accountList.remove(account.getUsername());
//    }
//
////    DISPLAY USERS
//    public void displayUsers() {
//        StringBuilder str;
//        str = new StringBuilder();
//        for (Account user : account) {
//            str.append(user.toString());
//            str.append(" ");
//        }
//    }
//
//}
