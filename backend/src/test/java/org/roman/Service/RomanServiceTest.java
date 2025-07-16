package org.roman.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class RomanServiceTest {

    private final RomanService romanService = new RomanService();

    @Test
    void testValidConversions() {
        assertEquals("I", romanService.convertToRoman(1));
        assertEquals("IV", romanService.convertToRoman(4));
        assertEquals("IX", romanService.convertToRoman(9));
        assertEquals("XL", romanService.convertToRoman(40));
        assertEquals("XC", romanService.convertToRoman(90));
        assertEquals("CD", romanService.convertToRoman(400));
        assertEquals("CM", romanService.convertToRoman(900));
        assertEquals("MCMXCIV", romanService.convertToRoman(1994));
        assertEquals("MMMCMXCIX", romanService.convertToRoman(3999)); // max
    }

    @Test
    void testNumberTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            romanService.convertToRoman(0);
        });
        assertEquals("Input number must be between 1 and 3999", exception.getMessage());
    }

    @Test
    void testNumberTooLarge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            romanService.convertToRoman(4000);
        });
        assertEquals("Input number must be between 1 and 3999", exception.getMessage());
    }
}
