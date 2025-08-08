package com.numbertoword.ntw.service.converter;

import com.numbertoword.ntw.service.AbstractNumberConverter;
import org.springframework.stereotype.Component;

@Component("negativeNumber")
public class NegativeNumberConverter extends AbstractNumberConverter {
    @Override
    public String convert(String number) {
        String cleaned = cleanNumber(number.substring(1));
        if (cleaned.isEmpty()){
            return "minus zero dollar";
        }
        return "minus " + handleNormalNumber(cleaned) + " dollars";
    }
}
