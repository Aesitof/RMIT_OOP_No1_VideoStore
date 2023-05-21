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

    public static Genre byName(String name) {
        Genre returnValue = null;
        for (Genre genre : values()) {
            if (genre.getName().equals(name)) {
                returnValue = genre;
                return returnValue;
            }
        }
        return returnValue;
    }
    @Override
    public String toString() {
        return getName();
    }
}
