package com.no1.geniestore.constants;

public enum ItemType {
    DVD("DVD"),
    GAME("Game"),
    VIDEO_RECORD("Video Record");

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
