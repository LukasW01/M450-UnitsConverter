package dev.bene;

import dev.bene.converter.Converter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterLengthTest {

    private final Converter converter;

    public ConverterLengthTest() {
        converter = new Converter();
    }

    @Test
    public void testKMinM() {
        converter.convertValue(100, "KILOMETRE", "METRE", "LENGTH");

        assertEquals(100000, converter.getOutput());
        assertEquals("input * 1000.0", converter.getFormula());
    }

    @Test
    public void testKMinCM() {
        converter.convertValue(100, "KILOMETRE", "CENTIMETRE", "LENGTH");

        assertEquals(10000000, converter.getOutput());
        assertEquals("input * 100000.0", converter.getFormula());
    }

    @Test
    public void testKMinMM() {
        converter.convertValue(100, "KILOMETRE", "MILLIMETRE", "LENGTH");

        assertEquals(100000000, converter.getOutput());
        assertEquals("input * 1000000.0", converter.getFormula());
    }

    @Test
    public void testMILES_YARDS() {
        converter.convertValue(100, "MILES", "YARD", "LENGTH");

        assertEquals(176000, converter.getOutput());
        assertEquals("input * 1760.0", converter.getFormula());
    }

    @Test
    public void testMILES_FOOT() {
        converter.convertValue(100, "MILES", "FOOT", "LENGTH");

        assertEquals(528000, converter.getOutput());
        assertEquals("input * 5280.0", converter.getFormula());
    }

    @Test
    public void testMILES_INCH() {
        converter.convertValue(100, "MILES", "INCH", "LENGTH");

        assertEquals(6336000, converter.getOutput());
        assertEquals("input * 63360.0", converter.getFormula());
    }

}
