package org.roman.controller;

import org.roman.model.RomanResponse;
import org.roman.service.RomanService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
public class RomanController {

    private static final Logger logger = LoggerFactory.getLogger(RomanController.class);
    private final RomanService romanService;

    public RomanController(RomanService romanService) {
        this.romanService = romanService;
    }

    /**
     * Handles GET requests to convert an integer value to its Roman numeral representation.
     *
     * @param query the integer value to convert to Roman numeral
     * @return {@link ResponseEntity} containing the {@link RomanResponse} on success,
     *         or an error message in case of failure
     */

    @GetMapping(value = "/romannumeral", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRomanNumeral(@RequestParam("query") int query) {
        logger.info("Received API request to convert: {}", query);
        try {
            String roman = romanService.convertToRoman(query);
            logger.info("Converted {} to {}", query, roman);
            RomanResponse response =  new RomanResponse(String.valueOf(query), roman);
            logger.info("Returning Roman numeral response: {}", roman);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid input: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected error while converting number to Roman: ", ex);
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
