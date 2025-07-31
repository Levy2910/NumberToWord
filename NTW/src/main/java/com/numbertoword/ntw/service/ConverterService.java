package com.numbertoword.ntw.service;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public String handleConverter(String number) {

        // Remove unnecessary spaces
        String trimedNumber = number.trim();

        // Validate the input; if not valid, throw an error.

        String type = validateNumber(trimedNumber);
        if (type.equals("invalid")) {
            throw new IllegalArgumentException("Invalid input: " + number);
        }
        if (type.equals("positiveNumber")) {
            return handlePositiveNumber(trimedNumber);
        } else if (type.equals("negativeNumber")) {
            return handleNegativeNumber(trimedNumber);
        }
        return handleDecimalNumber(trimedNumber);
    }

    private String handleDecimalNumber(String trimedNumber) {
        String[] splitedNumber = trimedNumber.split("\\.");
        String cleanedFirstHalf = cleanNumber(splitedNumber[0]);
        String cleanedSecondHalf = cleanNumber(splitedNumber[1]);
        return handleNormalNumber(cleanedFirstHalf) + " dollars and " + handleNormalNumber(cleanedSecondHalf) + " cents";
    }


    private String handleNegativeNumber(String trimedNumber) {
        trimedNumber = trimedNumber.substring(1);
        String cleanedNumber = cleanNumber(trimedNumber);
        return "minus " + handleNormalNumber(cleanedNumber) + " dollars ";
    }

    private String handlePositiveNumber(String trimedNumber) {
        return handleNormalNumber(trimedNumber)+ " dollars ";
    }

    private String handleNormalNumber(String trimedNumber) {
        String cleanedNumber = cleanNumber(trimedNumber);
        if (cleanedNumber.length() > 12) {
            throw new IllegalArgumentException("trillion not supported " + trimedNumber);
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

    private String handleOneSplitOfDigit(String trimedNumber) {
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

    private String cleanNumber(String number) {
        if (number.charAt(0) == '+') {
            number = number.substring(1);
        }
        while (number.length() != 1 && number.charAt(0) == '0') {
            number = number.substring(1);
        }
        return number;
    }

    private String validateNumber(String number) {
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
