package org.roman.Controller;

import io.micrometer.core.annotation.Timed;
import org.roman.Model.RomanResponse;
import org.roman.Service.RomanService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RomanController {

    private final RomanService romanService;
    private static final Logger logger = LoggerFactory.getLogger(RomanController.class);


    public RomanController(RomanService romanService) {
        this.romanService = romanService;
    }

    @GetMapping("/romannumeral")
    @Timed(value = "roman.convert.time", description = "Time taken to convert integer to Roman numeral")
    public RomanResponse getRomanNumeral(@RequestParam("query") int query) {
        logger.info("Received request to convert number: {}", query);
        if (query < 1 || query > 3999) {
            throw new RuntimeException("Input must be between 1 and 3999.");
        }

        String roman = romanService.convertToRoman(query);
        logger.info("Converted {} to {}", query, roman);
        return new RomanResponse(String.valueOf(query), roman);
    }

}
