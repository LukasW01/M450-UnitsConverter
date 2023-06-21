package dev.bene;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterLengthTest {

    @Test
    public void testKMinM() {
        Converter converter = new Converter();
        converter.convertValue(100, "KILOMETRE", "METRE", "LENGTH");

        assertEquals(100000, converter.getOutput());
        assertEquals("input * 1000.0", converter.getFormula());
    }

    @Test
    public void testKMinCM() {
        Converter converter = new Converter();
        converter.convertValue(100, "KILOMETRE", "CENTIMETRE", "LENGTH");

        assertEquals(10000000, converter.getOutput());
        assertEquals("input * 100000.0", converter.getFormula());
    }

    @Test
    public void testKMinMM() {
        Converter converter = new Converter();
        converter.convertValue(100, "KILOMETRE", "MILLIMETRE", "LENGTH");

        assertEquals(100000000, converter.getOutput());
        assertEquals("input * 1000000.0", converter.getFormula());
    }

    @Test
    public void testMILES_YARDS() {
        Converter converter = new Converter();
        converter.convertValue(100, "MILES", "YARD", "LENGTH");

        assertEquals(176000, converter.getOutput());
        assertEquals("input * 1760.0", converter.getFormula());
    }

    @Test
    public void testMILES_FOOT() {
        Converter converter = new Converter();
        converter.convertValue(100, "MILES", "FOOT", "LENGTH");

        assertEquals(528000, converter.getOutput());
        assertEquals("input * 5280.0", converter.getFormula());
    }

    @Test
    public void testMILES_INCH() {
        Converter converter = new Converter();
        converter.convertValue(100, "MILES", "INCH", "LENGTH");

        assertEquals(6336000, converter.getOutput());
        assertEquals("input * 63360.0", converter.getFormula());
    }

}
