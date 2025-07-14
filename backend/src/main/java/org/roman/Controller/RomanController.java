package org.roman.Controller;

import io.micrometer.core.annotation.Timed;
import org.roman.Model.RomanResponse;
import org.roman.Service.RomanService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
//@RequestMapping("/api/v1")
public class RomanController {

    private static final Logger logger = LoggerFactory.getLogger(RomanController.class);
    private final RomanService romanService;

    public RomanController(RomanService romanService) {
        this.romanService = romanService;
    }

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
