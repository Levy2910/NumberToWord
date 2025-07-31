package NumberToWord.com.NTW.service;

public enum DigitConverter {
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine");

    private final int digit;
    private final String word;

    DigitConverter(int digit, String word) {
        this.digit = digit;
        this.word = word;
        System.out.println("Loaded DigitConverter: " + word);
    }


    public int getDigit() {
        return digit;
    }

    public String getWord() {
        return word;
    }

    public static String fromDigit(int d) {
        for (DigitConverter value : values()) {
            if (value.getDigit() == d) {
                return value.getWord();
            }
        }
        return "";
    }
}
