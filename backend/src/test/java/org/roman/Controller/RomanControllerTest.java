package org.roman.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roman.Service.RomanService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class RomanControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RomanService romanService;

    @InjectMocks
    private RomanController romanController;

    private static final String ROMAN_NUMERAL_API = "/romannumeral";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(romanController).build();
    }

    @Test
    public void testGetRomanNumeral_Success() throws Exception {
        when(romanService.convertToRoman(10)).thenReturn("X");

        mockMvc.perform(get(ROMAN_NUMERAL_API)
                        .param("query", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                    .andExpect(jsonPath("$.output").value("X"))
                    .andExpect(jsonPath("$.input").value("10"));

        verify(romanService, times(1)).convertToRoman(10);
    }

    @Test
    public void testGetRomanNumeral_InvalidInput() throws Exception {
        when(romanService.convertToRoman(-1)).thenThrow(new IllegalArgumentException("Input number must be between 1 and 3999"));

        mockMvc.perform(get(ROMAN_NUMERAL_API)
                        .param("query", "-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Input number must be between 1 and 3999"));

        verify(romanService, times(1)).convertToRoman(-1);
    }
}
