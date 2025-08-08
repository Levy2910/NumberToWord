package com.numbertoword.ntw.service.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DigitConverterTest {

    @Test
    void fromDigitShouldReturnCorrectWordForValidDigits() {
        assertThat(DigitConverter.fromDigit(1)).isEqualTo("one");
        assertThat(DigitConverter.fromDigit(5)).isEqualTo("five");
        assertThat(DigitConverter.fromDigit(9)).isEqualTo("nine");
    }

    @Test
    void fromDigitShouldReturnEmptyStringForInvalidDigits() {
        assertThat(DigitConverter.fromDigit(0)).isEmpty();
        assertThat(DigitConverter.fromDigit(10)).isEmpty();
        assertThat(DigitConverter.fromDigit(-1)).isEmpty();
    }

    @Test
    void enumValuesShouldMatchExpectedDigitsAndWords() {
        assertThat(DigitConverter.ONE.getDigit()).isEqualTo(1);
        assertThat(DigitConverter.ONE.getWord()).isEqualTo("one");

        assertThat(DigitConverter.SEVEN.getDigit()).isEqualTo(7);
        assertThat(DigitConverter.SEVEN.getWord()).isEqualTo("seven");
    }
}