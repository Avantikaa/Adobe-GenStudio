package org.roman.Model;

public class RomanResponse {
    private String input;
    private String output;

    public RomanResponse(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}