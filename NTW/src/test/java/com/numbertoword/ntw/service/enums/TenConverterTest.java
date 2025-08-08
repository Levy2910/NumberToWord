package com.numbertoword.ntw.service.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TenConverterTest {

    @Test
    void fromShouldReturnCorrectWordForValidDigits() {
        assertThat(TenConverter.from(2)).isEqualTo("twenty");
        assertThat(TenConverter.from(3)).isEqualTo("thirty");
        assertThat(TenConverter.from(4)).isEqualTo("forty");
        assertThat(TenConverter.from(5)).isEqualTo("fifty");
        assertThat(TenConverter.from(6)).isEqualTo("sixty");
        assertThat(TenConverter.from(7)).isEqualTo("seventy");
        assertThat(TenConverter.from(8)).isEqualTo("eighty");
        assertThat(TenConverter.from(9)).isEqualTo("ninety");
    }

    @Test
    void fromShouldReturnEmptyStringForInvalidDigits() {
        assertThat(TenConverter.from(0)).isEmpty();
        assertThat(TenConverter.from(1)).isEmpty();
        assertThat(TenConverter.from(10)).isEmpty();
        assertThat(TenConverter.from(-3)).isEmpty();
    }

    @Test
    void enumValuesShouldHaveCorrectNamesAndOrder() {
        assertThat(TenConverter.TWENTY.name()).isEqualTo("TWENTY");
        assertThat(TenConverter.NINETY.ordinal()).isEqualTo(7);
    }
}
