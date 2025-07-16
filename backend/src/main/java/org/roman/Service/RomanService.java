package org.roman.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RomanService {

    private static final Logger logger = LoggerFactory.getLogger(RomanService.class);

    // Lookup table for thousands place: possible values are 0–3 (since max 3999)
    private static String[] thousands = {"", "M", "MM", "MMM"};
    // Lookup table for hundreds place: 0–9
    private static String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    // Lookup table for tens place: 0–9
    private static String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    // Lookup table for ones place: 0–9
    private static String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};


    public String convertToRoman(int num) {
        logger.info("Received request to convert number: {}", num);

        if (num < 1 || num > 3999) {
            logger.warn("Invalid number received: {}. Must be between 1 and 3999.", num);
            throw new IllegalArgumentException("Input number must be between 1 and 3999");
        }

        /*
        * Thousands place: num / 1000 gives 0–3
        * Hundreds place: get the digit in hundreds place
        * (num % 1000) gives last three digits, then divide by 100
        * Tens place: get the digit in tens place
        * (num % 100) gives last two digits, then divide by 10
        * Ones place: last digit
        * Concatenate all parts to get the final Roman numeral
        */

        String romanNumeral = thousands[num / 1000]
                + hundreds[(num % 1000) / 100]
                + tens[(num % 100) / 10]
                + ones[num % 10];

        logger.info("Converted number to Roman numeral: {}", romanNumeral);
        return romanNumeral;
    }
}
