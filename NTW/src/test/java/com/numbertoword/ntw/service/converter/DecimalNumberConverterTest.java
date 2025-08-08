package com.numbertoword.ntw.service.converter;

import com.numbertoword.ntw.service.converter.DecimalNumberConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DecimalNumberConverterTest {

    private DecimalNumberConverter converter;

    @BeforeEach
    void setUp() {
        converter = new DecimalNumberConverter();
    }

    @Test
    void shouldConvertPositiveDecimal() {
        String result = converter.convert("78.90");
        assertThat(result).isEqualTo("seventy eight dollars and ninety cents");
    }

    @Test
    void shouldConvertNegativeDecimal() {
        String result = converter.convert("-45.50");
        assertThat(result).isEqualTo("minus forty five dollars and fifty cents");
    }

    @Test
    void shouldConvertDecimalWithSingleCentDigit() {
        String result = converter.convert("12.5");
        assertThat(result).isEqualTo("twelve dollars and fifty cents");
    }

    @Test
    void shouldConvertDecimalWithZeroCents() {
        String result = converter.convert("100.00");
        assertThat(result).isEqualTo("one hundred dollars");
    }

    @Test
    void shouldThrowExceptionWhenCentsMoreThanTwoDigits() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> converter.convert("45.123"));
        assertThat(ex.getMessage()).contains("Cents must be between 0 and 99");
    }
}