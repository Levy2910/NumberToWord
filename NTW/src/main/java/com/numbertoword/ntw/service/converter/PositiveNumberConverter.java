package com.numbertoword.ntw.service.converter;

import com.numbertoword.ntw.service.AbstractNumberConverter;
import org.springframework.stereotype.Component;

@Component("positiveNumber")
public class PositiveNumberConverter extends AbstractNumberConverter {
    @Override
    public String convert(String number) {
        String cleaned = cleanNumber(number);
        if (cleaned.isEmpty()){
            return "zero dollars";
        }
        return handleNormalNumber(cleaned) + " dollars";
    }
}
