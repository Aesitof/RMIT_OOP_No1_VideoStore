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

    public static ItemType byName(String name) {
        ItemType returnValue;
        for (ItemType itemType : values()) {
            if (itemType.getName().equals(name)) {
                returnValue = itemType;
                return returnValue;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
