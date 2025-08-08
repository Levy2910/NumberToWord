package com.numbertoword.ntw.service.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositiveNumberConverterTest {

    private PositiveNumberConverter converter;

    @BeforeEach
    void setUp() {
        converter = new PositiveNumberConverter();
    }

    @Test
    void shouldConvertPositiveWholeNumber() {
        String result = converter.convert("123");
        assertThat(result).isEqualTo("one hundred twenty three dollars");
    }

    @Test
    void shouldConvertPositiveNumberWithPlusSign() {
        String result = converter.convert("+45");
        assertThat(result).isEqualTo("forty five dollars");
    }

    @Test
    void shouldConvertZero() {
        String result = converter.convert("0");
        assertThat(result).isEqualTo("zero dollars");
    }

    @Test
    void shouldConvertNumberWithLeadingZeros() {
        String result = converter.convert("00056");
        assertThat(result).isEqualTo("fifty six dollars");
    }
}