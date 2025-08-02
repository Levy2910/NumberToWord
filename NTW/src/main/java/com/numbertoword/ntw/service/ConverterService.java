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
        String type = new PositiveNumberConverter().validateNumber(trimmed);

        switch (type) {
            case "positiveNumber":
                return positiveConverter.convert(trimmed);
            case "negativeNumber":
                return negativeConverter.convert(trimmed);
            case "decimalNumber":
                return decimalConverter.convert(trimmed);
            default:
                throw new IllegalArgumentException("Invalid input: " + number);
        }
    }
}
