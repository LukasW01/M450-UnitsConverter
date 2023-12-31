package dev.bene;

import dev.bene.converter.Converter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterWeightTest {

    private Converter converter;

    public ConverterWeightTest() {
        converter = new Converter();
    }

    @Test
    public void testTinKG() {
        converter.convertValue(1, "TON", "KILOGRAM", "WEIGHT");

        assertEquals(1000, converter.getOutput());
        assertEquals("input * 1000.0", converter.getFormula());
    }

    @Test
    public void testTinG() {
        converter.convertValue(1, "TON", "GRAM", "WEIGHT");

        assertEquals(1000000, converter.getOutput());
        assertEquals("input * 1000000.0", converter.getFormula());
    }

    @Test
    public void testPOUNDinOZ() {
        converter.convertValue(100, "POUND", "OUNCE", "WEIGHT");

        assertEquals(1600, converter.getOutput());
        assertEquals("input * 16.0", converter.getFormula());
    }

}
