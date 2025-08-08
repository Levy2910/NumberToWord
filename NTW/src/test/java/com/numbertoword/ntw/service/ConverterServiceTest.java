package com.numbertoword.ntw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ConverterServiceTest {

    @Autowired
    private ConverterService converterService;

    @Test
    void shouldConvertPositiveNumber() {
        String result = converterService.handleConverter("123");
        assertThat(result).isEqualTo("one hundred twenty three dollars");
    }

    @Test
    void shouldConvertNegativeNumber() {
        String result = converterService.handleConverter("-45");
        assertThat(result).isEqualTo("minus forty five dollars");
    }

    @Test
    void shouldConvertDecimalNumber() {
        String result = converterService.handleConverter("12.50");
        assertThat(result).isEqualTo("twelve dollars and fifty cents");
    }

    @Test
    void shouldTrimInputBeforeConversion() {
        String result = converterService.handleConverter("   10   ");
        assertThat(result).isEqualTo("ten dollars");
    }

    @Test
    void shouldThrowForInvalidInput() {
        assertThatThrownBy(() -> converterService.handleConverter("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid input");
    }

    @Test
    void shouldValidatePositiveNumber() {
        assertThat(converterService.validateNumber("123")).isEqualTo("positiveNumber");
    }

    @Test
    void shouldValidateNegativeNumber() {
        assertThat(converterService.validateNumber("-123")).isEqualTo("negativeNumber");
    }

    @Test
    void shouldValidateDecimalNumber() {
        assertThat(converterService.validateNumber("+12.34")).isEqualTo("decimalNumber");
    }

    @Test
    void shouldReturnInvalidForBadFormat() {
        assertThat(converterService.validateNumber("12.345")).isEqualTo("decimalNumber");
    }
}