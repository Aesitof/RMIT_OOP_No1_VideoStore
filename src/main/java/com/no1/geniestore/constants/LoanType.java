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

    public static LoanType byName(String name) {
        LoanType returnValue = null;
        for (LoanType loanType : values()) {
            if (loanType.getName().equals(name)) {
                returnValue = loanType;
                return returnValue;
            }
        }
        return returnValue;
    }

    @Override
    public String toString() {
        return  getName();
    }
}
