package com.smartbook.entity.enums;

public enum Dialect {
    GB("en-gb"),
    US("en-us");

    private String name;

    Dialect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
