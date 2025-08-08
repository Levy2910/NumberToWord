package com.numbertoword.ntw.service.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NegativeNumberConverterTest {

    private NegativeNumberConverter converter;

    @BeforeEach
    void setUp() {
        converter = new NegativeNumberConverter();
    }

    @Test
    void shouldConvertNegativeWholeNumber() {
        String result = converter.convert("-123");
        assertThat(result).isEqualTo("minus one hundred twenty three dollars");
    }

    @Test
    void shouldConvertNegativeZero() {
        String result = converter.convert("-0");
        assertThat(result).isEqualTo("minus zero dollar");
    }

    @Test
    void shouldConvertNegativeNumberWithLeadingZeros() {
        String result = converter.convert("-00045");
        assertThat(result).isEqualTo("minus forty five dollars");
    }
}