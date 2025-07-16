package org.roman.model;


/**
 * Response DTO (Data Transfer Object) representing the result of converting
 * an input number to its Roman numeral representation.
 * Fields:
 * - input: The original input string (usually a number as string).
 * - output: The resulting Roman numeral string.
 */
public class RomanResponse {
    private String input;
    private String output;

    public RomanResponse(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return this.input;
    }

    public String getOutput() {
        return this.output;
    }
}