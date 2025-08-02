package com.numbertoword.ntw.service.converter;

import com.numbertoword.ntw.service.AbstractNumberConverter;

public class DecimalNumberConverter extends AbstractNumberConverter {
    @Override
    public String convert(String number) {
        if (number.charAt(0) == '-'){
            return "minus " + convertDecimalPositive(number);
        }
        return convertDecimalPositive(number);
    }

    private String convertDecimalPositive(String number) {
        String[] parts = number.split("\\.");
        String dollarPart = cleanNumber(parts[0]);

        String rawCentPart = parts.length > 1 ? parts[1] : "0";

        if (rawCentPart.length() == 1) {
            rawCentPart = rawCentPart + "0"; //
        } else if (rawCentPart.length() > 2) {
            throw new IllegalArgumentException("Cents must be between 0 and 99. Please enter a valid amount.");
        }

        int centValue = Integer.parseInt(rawCentPart);

        if (centValue == 0) {
            return handleNormalNumber(dollarPart) + " dollars";
        }

        return handleNormalNumber(dollarPart) + " dollars and " + handleNormalNumber(String.valueOf(centValue)) + " cents";
    }

}
