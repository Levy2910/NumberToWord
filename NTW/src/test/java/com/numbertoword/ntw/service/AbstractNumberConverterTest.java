package com.numbertoword.ntw.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AbstractNumberConverterTest {

    static class TestConverter extends AbstractNumberConverter { }

    private final TestConverter converter = new TestConverter();

    @Test
    void cleanNumberShouldRemovePlusAndLeadingZeros() {
        assertThat(converter.cleanNumber("+00123")).isEqualTo("123");
        assertThat(converter.cleanNumber("000")).isEqualTo("");
    }

    @Test
    void handleNormalNumberShouldReturnZeroForEmptyString() {
        assertThat(converter.handleNormalNumber("")).isEqualTo("zero");
    }

    @Test
    void handleNormalNumberShouldThrowForTooLargeNumbers() {
        String tooLarge = "1" + "0".repeat(16);
        assertThatThrownBy(() -> converter.handleNormalNumber(tooLarge))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("quadrillion");
    }

    @Test
    void handleNormalNumberShouldConvertSmallNumbers() {
        assertThat(converter.handleNormalNumber("7")).isEqualTo("seven");
        assertThat(converter.handleNormalNumber("45")).isEqualTo("forty five");
        assertThat(converter.handleNormalNumber("300")).isEqualTo("three hundred");
        assertThat(converter.handleNormalNumber("305")).isEqualTo("three hundred five");
    }

    @Test
    void handleNormalNumberShouldConvertThousandsAndMillions() {
        assertThat(converter.handleNormalNumber("1001"))
                .isEqualTo("one thousand one");
        assertThat(converter.handleNormalNumber("2500456"))
                .isEqualTo("two million five hundred thousand four hundred fifty six");
    }

    @Test
    void handleOneSplitOfDigitShouldConvertSingleGroups() {
        assertThat(converter.handleOneSplitOfDigit("7")).isEqualTo("seven");
        assertThat(converter.handleOneSplitOfDigit("45")).isEqualTo("forty five");
        assertThat(converter.handleOneSplitOfDigit("105")).isEqualTo("one hundred five");
        assertThat(converter.handleOneSplitOfDigit("013")).isEqualTo("thirteen");
    }
}