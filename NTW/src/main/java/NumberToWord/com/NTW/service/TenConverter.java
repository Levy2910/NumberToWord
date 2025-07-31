package NumberToWord.com.NTW.service;

public enum TenConverter {
    TWENTY(2, "twenty"),
    THIRTY(3, "thirty"),
    FORTY(4, "forty"),
    FIFTY(5, "fifty"),
    SIXTY(6, "sixty"),
    SEVENTY(7, "seventy"),
    EIGHTY(8, "eighty"),
    NINETY(9, "ninety");

    private final int digit;
    private final String word;

    TenConverter(int digit, String word) {
        this.digit = digit;
        this.word = word;
    }

    public static String from(int digit) {
        for (TenConverter t : values()) {
            if (t.digit == digit) return t.word;
        }
        return "";
    }
}
