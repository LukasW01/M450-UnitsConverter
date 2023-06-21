package dev.bene;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterWeightTest {

    @Test
    public void testTinKG() {
        Converter converter = new Converter();
        converter.convertValue(1, "TON", "KILOGRAM", "WEIGHT");

        assertEquals(1000, converter.getOutput());
        assertEquals("input * 1000.0", converter.getFormula());
    }

    @Test
    public void testTinG() {
        Converter converter = new Converter();
        converter.convertValue(1, "TON", "GRAM", "WEIGHT");

        assertEquals(1000000, converter.getOutput());
        assertEquals("input * 1000000.0", converter.getFormula());
    }

    @Test
    public void testPOUNDinOZ() {
        Converter converter = new Converter();
        converter.convertValue(100, "POUND", "OUNCE", "WEIGHT");

        assertEquals(1600, converter.getOutput());
        assertEquals("input * 16.0", converter.getFormula());
    }

}
