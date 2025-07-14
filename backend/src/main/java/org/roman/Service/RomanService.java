package org.roman.Service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RomanService {


    private static final Logger logger = LoggerFactory.getLogger(RomanService.class);

    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String convertToRoman(int num) {
        logger.info("Received request to convert number: {}", num);

        if (num < 1 || num > 3999) {
            logger.warn("Invalid number received: {}. Must be between 1 and 3999.", num);
            throw new IllegalArgumentException("Input number must be between 1 and 3999");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < VALUES.length; i++) {
            while (num >= VALUES[i]) {
                num -= VALUES[i];
                sb.append(SYMBOLS[i]);
            }
        }

        String romanNumeral = sb.toString();
        logger.info("Converted number to Roman numeral: {}", romanNumeral);
        return romanNumeral;
    }
}
