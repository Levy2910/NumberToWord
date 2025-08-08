package com.numbertoword.ntw.service.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeenConverterTest {

    @Test
    void fromShouldReturnCorrectWordForValidNumbers() {
        assertThat(TeenConverter.from(10)).isEqualTo("ten");
        assertThat(TeenConverter.from(11)).isEqualTo("eleven");
        assertThat(TeenConverter.from(12)).isEqualTo("twelve");
        assertThat(TeenConverter.from(13)).isEqualTo("thirteen");
        assertThat(TeenConverter.from(14)).isEqualTo("fourteen");
        assertThat(TeenConverter.from(15)).isEqualTo("fifteen");
        assertThat(TeenConverter.from(16)).isEqualTo("sixteen");
        assertThat(TeenConverter.from(17)).isEqualTo("seventeen");
        assertThat(TeenConverter.from(18)).isEqualTo("eighteen");
        assertThat(TeenConverter.from(19)).isEqualTo("nineteen");
    }

    @Test
    void fromShouldReturnEmptyStringForInvalidNumbers() {
        assertThat(TeenConverter.from(0)).isEmpty();
        assertThat(TeenConverter.from(9)).isEmpty();
        assertThat(TeenConverter.from(20)).isEmpty();
        assertThat(TeenConverter.from(-1)).isEmpty();
    }

    @Test
    void enumValuesShouldHaveCorrectMappings() {
        assertThat(TeenConverter.TEN.toString()).contains("TEN");
        assertThat(TeenConverter.ELEVEN.name()).isEqualTo("ELEVEN");
        assertThat(TeenConverter.EIGHTEEN.ordinal()).isEqualTo(8);
    }
}
