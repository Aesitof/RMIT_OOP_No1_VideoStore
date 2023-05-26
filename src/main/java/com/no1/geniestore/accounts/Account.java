package com.no1.geniestore.accounts;

public class Account {
    private String id;
    private String username;
    private String name;
    private String accountType;
    private String address;
    private String phone;
    private String password;
    private int totalReturnedItems;
    private int rewardPoints;
    public static int accountIdCounter;

//    Constructor method
    public Account(String name, String address, String phone, String username, String password, String accountType) {
        this.id = generateAccountID();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.totalReturnedItems = 0;
        this.rewardPoints = 0;
        this.accountType = accountType;
    }

    public Account(String id, String username, String name, String accountType, String address, String phone, String password, int totalReturnedItems, int rewardPoints) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.accountType = accountType;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.totalReturnedItems = totalReturnedItems;
        this.rewardPoints = rewardPoints;
    }

    //    Default Constructor
    public Account() {
        this.id = generateAccountID();
        this.name = "default";
        this.address = "default";
        this.phone = "default";
        this.username = "default";
        this.password = "default";
        this.totalReturnedItems = 0;
        this.rewardPoints = 0;
        this.accountType = "Guest";
    }

//    Getter and Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalReturnedItems() {
        return totalReturnedItems;
    }

    public void setTotalReturnedItems(int totalReturnedItems) {
        this.totalReturnedItems = totalReturnedItems;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String generateAccountID(){
        int code = accountIdCounter + 1;
        accountIdCounter++;
        String accountID = "C";

        if (code >= 1 && code <= 9) {
            accountID = String.format("%s%s%s", accountID, "00", code);
        } else if (code >= 10 && code <= 99){
            accountID = String.format("%s%s%s", accountID, "0", code);
        } else if (code >= 100 && code <= 999){
            accountID = String.format("%s%s%s", accountID, "", code);
        }
        return accountID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", accountType='" + accountType + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", totalReturnedItems=" + totalReturnedItems +
                ", rewardPoints=" + rewardPoints +
                '}';
    }
}
