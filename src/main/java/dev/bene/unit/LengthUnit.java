package dev.bene.unit;

import java.util.Map;
import static java.util.Map.entry;

public enum LengthUnit {

    MILES,
    YARD,
    FOOT,
    INCH,
    KILOMETRE,
    METRE,
    CENTIMETRE,
    MILLIMETRE;

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(String from, String to) {
        return conversionMap.get(from + "_" + to);
    }

    public static Map<String, Double> createConversionMap() {
        return Map.<String, Double>ofEntries(
                entry("FOOT_MILES", 1.0 / 5280),
                entry("FOOT_YARD", 1.0 / 3),
                entry("FOOT_INCH", 12.0),
                entry("FOOT_KILOMETRE", 1.0 / 3280.84),
                entry("FOOT_METRE", 1.0 / 3.28084),
                entry("FOOT_CENTIMETRE", 30.48),
                entry("FOOT_MILLIMETRE", 304.8),
                entry("MILES_FOOT", 5280.0),
                entry("MILES_YARD", 1760.0),
                entry("MILES_INCH", 63360.0),
                entry("MILES_KILOMETRE", 1.60934),
                entry("MILES_METRE", 1609.34),
                entry("MILES_CENTIMETRE", 160934.0),
                entry("MILES_MILLIMETRE", 1609340.0),
                entry("YARD_FOOT", 3.0),
                entry("YARD_MILES", 1.0 / 1760),
                entry("YARD_INCH", 36.0),
                entry("YARD_KILOMETRE", 1.0 / 1093.61),
                entry("YARD_METRE", 1.0 / 1.09361),
                entry("YARD_CENTIMETRE", 91.44),
                entry("YARD_MILLIMETRE", 914.4),
                entry("INCH_FOOT", 1.0 / 12),
                entry("INCH_MILES", 1.0 / 63360),
                entry("INCH_YARD", 1.0 / 36),
                entry("INCH_KILOMETRE", 1.0 / 39370.1),
                entry("INCH_METRE", 1.0 / 39.3701),
                entry("INCH_CENTIMETRE", 2.54),
                entry("INCH_MILLIMETRE", 25.4),
                entry("KILOMETRE_FOOT", 3280.84),
                entry("KILOMETRE_MILES", 0.621371),
                entry("KILOMETRE_YARD", 1093.61),
                entry("KILOMETRE_INCH", 39370.1),
                entry("KILOMETRE_METRE", 1000.0),
                entry("KILOMETRE_CENTIMETRE", 100000.0),
                entry("KILOMETRE_MILLIMETRE", 1000000.0),
                entry("METRE_FOOT", 3.28084),
                entry("METRE_MILES", 1.0 / 1609.34),
                entry("METRE_YARD", 1.0 / 1.09361),
                entry("METRE_INCH", 39.3701),
                entry("METRE_KILOMETRE", 1.0 / 1000),
                entry("METRE_CENTIMETRE", 100.0),
                entry("METRE_MILLIMETRE", 1000.0),
                entry("CENTIMETRE_FOOT", 1.0 / 30.48),
                entry("CENTIMETRE_MILES", 1.0 / 160934),
                entry("CENTIMETRE_YARD", 1.0 / 91.44),
                entry("CENTIMETRE_INCH", 1.0 / 2.54),
                entry("CENTIMETRE_KILOMETRE", 1.0 / 100000),
                entry("CENTIMETRE_METRE", 1.0 / 100),
                entry("CENTIMETRE_MILLIMETRE", 10.0),
                entry("MILLIMETRE_FOOT", 1.0 / 304.8),
                entry("MILLIMETRE_MILES", 1.0 / 1609340),
                entry("MILLIMETRE_YARD", 1.0 / 914.4),
                entry("MILLIMETRE_INCH", 1.0 / 25.4),
                entry("MILLIMETRE_KILOMETRE", 1.0 / 1000000),
                entry("MILLIMETRE_METRE", 1.0 / 1000),
                entry("MILLIMETRE_CENTIMETRE", 1.0 / 10)
        );
    }
}
