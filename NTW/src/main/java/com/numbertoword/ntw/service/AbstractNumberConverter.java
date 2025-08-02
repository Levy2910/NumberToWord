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
        if (cleanedNumber.isEmpty()){
            return "zero";
        }
        if (cleanedNumber.length() > 12) {
            throw new IllegalArgumentException("trillion not supported " + cleanedNumber);
        }
        StringBuilder result = new StringBuilder();
        int length = cleanedNumber.length();

        if (length >= 10) {
            String firstGroup = handleOneSplitOfDigit(cleanedNumber.substring(length - 3));
            String secondGroup = handleOneSplitOfDigit(cleanedNumber.substring(length - 6, length - 3));
            String thirdGroup = handleOneSplitOfDigit(cleanedNumber.substring(length - 9, length - 6));
            String fourthGroup = handleOneSplitOfDigit(cleanedNumber.substring(0, length - 9));
            if (!fourthGroup.isBlank()) result.append(fourthGroup).append(" billion ");
            if (!thirdGroup.isBlank()) result.append(thirdGroup).append(" million ");
            if (!secondGroup.isBlank()) result.append(secondGroup).append(" thousand ");
            result.append(firstGroup);
            return result.toString().trim();
        }

        if (length >= 7) {
            String firstGroup = handleOneSplitOfDigit(cleanedNumber.substring(length - 3));
            String secondGroup = handleOneSplitOfDigit(cleanedNumber.substring(length - 6, length - 3));
            String thirdGroup = handleOneSplitOfDigit(cleanedNumber.substring(0, length - 6));
            if (!thirdGroup.isBlank()) result.append(thirdGroup).append(" million ");
            if (!secondGroup.isBlank()) result.append(secondGroup).append(" thousand ");
            result.append(firstGroup);
            return result.toString().trim();
        }

        if (length >= 4) {
            String firstGroup = handleOneSplitOfDigit(cleanedNumber.substring(length - 3));
            String secondGroup = handleOneSplitOfDigit(cleanedNumber.substring(0, length - 3));
            if (!secondGroup.isBlank()) result.append(secondGroup).append(" thousand ");
            result.append(firstGroup);
            return result.toString().trim();
        }

        return handleOneSplitOfDigit(cleanedNumber);
    }

    protected String handleOneSplitOfDigit(String trimedNumber) {
        StringBuilder buildString = new StringBuilder();
        int len = trimedNumber.length();
        if (len == 3 && trimedNumber.charAt(0) != '0') {
            String convertHundred = DigitConverter.fromDigit(Character.getNumericValue(trimedNumber.charAt(0)));
            buildString.append(convertHundred).append(" hundred ");
        }

        int startIndex = len == 3 ? 1 : 0;

        if (trimedNumber.length() - startIndex == 2) {
            char tens = trimedNumber.charAt(startIndex);
            char units = trimedNumber.charAt(startIndex + 1);

            if (tens == '1') {
                int teenValue = Integer.parseInt(trimedNumber.substring(startIndex));
                buildString.append(TeenConverter.from(teenValue));
                return buildString.toString().trim();
            }

            if (tens != '0') {
                buildString.append(TenConverter.from(Character.getNumericValue(tens))).append(" ");
            }

            if (units != '0') {
                buildString.append(DigitConverter.fromDigit(Character.getNumericValue(units)));
            }
        } else if (trimedNumber.length() - startIndex == 1 && trimedNumber.charAt(startIndex) != '0') {
            buildString.append(DigitConverter.fromDigit(Character.getNumericValue(trimedNumber.charAt(startIndex))));
        }

        return buildString.toString().trim();
    }


}
