package com.no1.geniestore.products;

public class Vip extends Account{
    public int rewardPoint;
    private static final String accountType = "Vip";

    public Vip(String id, String name, String address, String phone, String username, String password, int rewardPoint) {
        super(id, name, address, phone, username, password);
        this.rewardPoint = rewardPoint;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }
}
