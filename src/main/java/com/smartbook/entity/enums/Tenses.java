package com.smartbook.entity.enums;

public enum Tenses {
    PRESENT_SIMPLE("V_1"),
    PAST_SIMPLE("V_2"),
    PAST_PARTICIPLE("V_3"),
    ING_FORM("V_ING"),
    THIRD_PERSON_SINGULAR("3_PS");

    String name;

    Tenses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
