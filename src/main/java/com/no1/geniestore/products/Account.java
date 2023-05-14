package com.no1.geniestore.products;


abstract public class Account {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String username;
    private String password;

    public Account(String id, String name, String address, String phone, String username, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String createUsername() {
        // the username contains up to 15 characters,
    }

    //Generate ID Cho Accounts

    public String latestAccountID(){
        // take the latest ID in an account list
        String latestIDCode = null;
        return latestIDCode;
    }

    public String generateAccountID(){
        String latestIDCode = latestAccountID();
        int code = Integer.parseInt(latestIDCode);
        code = code + 1;
        String accountID = "C";

        if (code >= 1 && code <= 9){
            accountID = accountID + "00" + code;
        }
        else if (code >= 10 && code <= 99){
            accountID = accountID + "0" + code;
        }
        else if (code >= 100 && code <= 999){
            accountID = accountID + "" + code;
        }
        return accountID;
    }
}
