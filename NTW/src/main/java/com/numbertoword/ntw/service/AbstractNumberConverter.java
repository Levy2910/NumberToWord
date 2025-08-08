package com.numbertoword.ntw.service;

import com.numbertoword.ntw.service.enums.DigitConverter;
import com.numbertoword.ntw.service.enums.TeenConverter;
import com.numbertoword.ntw.service.enums.TenConverter;

public class AbstractNumberConverter implements NumberConverter{
    @Override
    public String convert(String number) {
        return "";
    }
    protected String cleanNumber(String number) {
        if (number.charAt(0) == '+') {
            number = number.substring(1);
        }
        while (!number.isEmpty() && number.charAt(0) == '0') {
            number = number.substring(1);
        }
        return number;
    }
    protected String handleNormalNumber(String cleanedNumber) {
        if (cleanedNumber.isEmpty()) {
            return "zero";
        }

        if (cleanedNumber.length() > 15) {
            throw new IllegalArgumentException("Numbers larger than quadrillion are not supported: " + cleanedNumber);
        }


        final String[] GROUP_NAMES = {"", "thousand", "million", "billion", "trillion", "quadrillion"};

        StringBuilder result = new StringBuilder();
        int groupIndex = 0;


        while (!cleanedNumber.isEmpty()) {
            int end = cleanedNumber.length();
            int start = Math.max(end - 3, 0);
            String groupValue = handleOneSplitOfDigit(cleanedNumber.substring(start, end));

            if (!groupValue.isBlank()) {
                if (!GROUP_NAMES[groupIndex].isEmpty()) {
                    groupValue += " " + GROUP_NAMES[groupIndex];
                }

                if (result.length() > 0) {
                    result.insert(0, " ");
                }
                result.insert(0, groupValue);
            }

            cleanedNumber = cleanedNumber.substring(0, start);
            groupIndex++;
        }

        return result.toString().trim();
    }


    protected String handleOneSplitOfDigit(String chunk) {
        StringBuilder result = new StringBuilder();

        // "7" -> "007", "45" -> "045"
        chunk = String.format("%3s", chunk).replace(' ', '0');

        int hundredsDigit = Character.getNumericValue(chunk.charAt(0));
        int tensDigit = Character.getNumericValue(chunk.charAt(1));
        int unitsDigit = Character.getNumericValue(chunk.charAt(2));

        // Handle hundreds
        if (hundredsDigit != 0) {
            result.append(DigitConverter.fromDigit(hundredsDigit)).append(" hundred");
            if (tensDigit != 0 || unitsDigit != 0) {
                result.append(" ");
            }
        }

        // Handle tens & units
        if (tensDigit == 1) { // teens
            int teenValue = tensDigit * 10 + unitsDigit;
            result.append(TeenConverter.from(teenValue));
        } else {
            if (tensDigit != 0) {
                result.append(TenConverter.from(tensDigit));
                if (unitsDigit != 0) {
                    result.append(" ");
                }
            }
            if (unitsDigit != 0) {
                result.append(DigitConverter.fromDigit(unitsDigit));
            }
        }

        return result.toString().trim();
    }



}
