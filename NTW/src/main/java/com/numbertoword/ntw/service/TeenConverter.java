package com.numbertoword.ntw.service;

public enum TeenConverter {
    TEN(10, "ten"),
    ELEVEN(11, "eleven"),
    TWELVE(12, "twelve"),
    THIRTEEN(13, "thirteen"),
    FOURTEEN(14, "fourteen"),
    FIFTEEN(15, "fifteen"),
    SIXTEEN(16, "sixteen"),
    SEVENTEEN(17, "seventeen"),
    EIGHTEEN(18, "eighteen"),
    NINETEEN(19, "nineteen");

    private final int value;
    private final String word;

    TeenConverter(int value, String word) {
        this.value = value;
        this.word = word;
    }

    public static String from(int number) {
        for (TeenConverter t : values()) {
            if (t.value == number) return t.word;
        }
        return "";
    }
}
