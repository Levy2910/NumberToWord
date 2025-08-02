package com.numbertoword.ntw.service;

import com.numbertoword.ntw.service.converter.DecimalNumberConverter;
import com.numbertoword.ntw.service.converter.NegativeNumberConverter;
import com.numbertoword.ntw.service.converter.PositiveNumberConverter;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    private final NumberConverter positiveConverter = new PositiveNumberConverter();
    private final NumberConverter negativeConverter = new NegativeNumberConverter();
    private final NumberConverter decimalConverter = new DecimalNumberConverter();

    public String handleConverter(String number) {
        String trimmed = number.trim();
        String type = validateNumber(trimmed);

        return switch (type) {
            case "positiveNumber" -> positiveConverter.convert(trimmed);
            case "negativeNumber" -> negativeConverter.convert(trimmed);
            case "decimalNumber" -> decimalConverter.convert(trimmed);
            default -> throw new IllegalArgumentException("Invalid input: " + number);
        };
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
