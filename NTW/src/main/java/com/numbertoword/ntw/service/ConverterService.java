package com.numbertoword.ntw.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConverterService {

    private final Map<String, NumberConverter> converters;

    public ConverterService(Map<String, NumberConverter> converters) {
        this.converters = converters;
    }

    public String handleConverter(String number) {
        String trimmed = number.trim();
        String type = validateNumber(trimmed);

        NumberConverter converter = converters.get(type);
        if (converter == null) {
            throw new IllegalArgumentException("Invalid input: " + number);
        }

        return converter.convert(trimmed);
    }

    public String validateNumber(String number) {
        if (number == null || number.isEmpty()) return "invalid";
        if (number.matches("[+-]?\\d+\\.\\d+")) {
            return "decimalNumber";
        } else if (number.matches("-\\d+")) {
            return "negativeNumber";
        } else if (number.matches("\\+?\\d+")) {
            return "positiveNumber";
        } else {
            return "invalid";
        }
    }
}
