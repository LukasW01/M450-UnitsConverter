package dev.bene.model;

import java.util.Map;
import static java.util.Map.entry;

public enum LengthUnit {

    FOOT,
    MILES,
    YARD,
    KILOMETRE,
    METRE,
    CENTIMETRE,
    MILLIMETRE;

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(String from, String to) {
        return conversionMap.get(from + "_" + to);
    }

    public static Map<String, Double> createConversionMap() {
        return Map.ofEntries(
                entry("MILES_MILES", 1.0),
                entry("MILES_YARD", 1760.0),
                entry("MILES_FOOT", 5280.0),
                entry("MILES_KILOMETRE", 1.609344),
                entry("MILES_METRE", 1609.344),
                entry("MILES_CENTIMETRE", 160934.4),
                entry("MILES_MILLIMETRE", 1609344.0),
                entry("YARD_YARD", 1.0),
                entry("YARD_MILES", 1.0 / 1760),
                entry("YARD_FOOT", 3.0),
                entry("YARD_KILOMETRE", 0.0009144),
                entry("YARD_METRE", 0.9144),
                entry("YARD_CENTIMETRE", 91.44),
                entry("YARD_MILLIMETRE", 914.4),
                entry("FOOT_FOOT", 1.0),
                entry("FOOT_MILES", 1.0 / 5280),
                entry("FOOT_YARD", 1.0 / 3),
                entry("FOOT_KILOMETRE", 0.0003048),
                entry("FOOT_METRE", 0.3048),
                entry("FOOT_CENTIMETRE", 30.48),
                entry("FOOT_MILLIMETRE", 304.8),
                entry("KILOMETRE_KILOMETRE", 1.0),
                entry("KILOMETRE_METRE", 1000.0),
                entry("KILOMETRE_CENTIMETRE", 100000.0),
                entry("KILOMETRE_MILLIMETRE", 1000000.0),
                entry("KILOMETRE_FOOT", 3280.84),
                entry("KILOMETRE_YARD", 1093.613),
                entry("KILOMETRE_MILES", 0.621371),
                entry("METRE_METRE", 1.0),
                entry("METRE_CENTIMETRE", 100.0),
                entry("METRE_MILLIMETRE", 1000.0),
                entry("METRE_FOOT", 3.28084),
                entry("METRE_YARD", 1.093613),
                entry("METRE_MILES", 0.000621371),
                entry("METRE_KILOMETRE", 0.001),
                entry("CENTIMETRE_CENTIMETRE", 1.0),
                entry("CENTIMETRE_MILLIMETRE", 10.0),
                entry("CENTIMETRE_FOOT", 0.0328084),
                entry("CENTIMETRE_YARD", 0.0109361),
                entry("CENTIMETRE_MILES", 0.00000621371),
                entry("CENTIMETRE_KILOMETRE", 0.00001),
                entry("CENTIMETRE_METRE", 0.01),
                entry("MILLIMETRE_MILLIMETRE", 1.0),
                entry("MILLIMETRE_FOOT", 0.00328084),
                entry("MILLIMETRE_YARD", 0.00109361),
                entry("MILLIMETRE_MILES", 0.000000621371),
                entry("MILLIMETRE_KILOMETRE", 0.000001),
                entry("MILLIMETRE_METRE", 0.001),
                entry("MILLIMETRE_CENTIMETRE", 0.1)
        );
    }
}
