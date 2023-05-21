package com.no1.geniestore.constants;

public enum LoanType {
    TWO_DAY_LOAN("2 days"),
    ONE_WEEK_LOAN("7 days");

    private final String name;

    LoanType(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  getName();
    }
}
