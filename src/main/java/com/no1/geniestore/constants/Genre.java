package com.no1.geniestore.constants;

public enum Genre {
    ACTION("Action"),
    HORROR("Horror"),
    DRAMA("Drama"),
    COMEDY("Comedy");

    private final String name;
    Genre(String name) {
        this.name = name;
    }

    public String getName()  {
        return name;
    }
    @Override
    public String toString() {
        return getName();
    }
}
