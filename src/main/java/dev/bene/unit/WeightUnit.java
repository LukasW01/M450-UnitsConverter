package dev.bene.unit;

import java.util.Map;
import static java.util.Map.entry;

public enum WeightUnit {

    MILLIGRAM,
    GRAM,
    KILOGRAM,
    TON,
    POUND,
    OUNCE;

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(String from, String to) {
        return conversionMap.get(from + "_" + to);
    }

    private static Map<String, Double> createConversionMap() {
        return Map.ofEntries(
                entry("MILLIGRAM_MILLIGRAM", 1.0),
                entry("MILLIGRAM_GRAM", 1.0 / 1000),
                entry("MILLIGRAM_KILOGRAM", 1.0 / 1000000),
                entry("MILLIGRAM_TON", 1.0 / 1000000000),
                entry("MILLIGRAM_POUND", 1.0 / 453592),
                entry("MILLIGRAM_OUNCE", 1.0 / 28350),
                entry("GRAM_GRAM", 1.0),
                entry("GRAM_MILLIGRAM", 1000.0),
                entry("GRAM_KILOGRAM", 1.0 / 1000),
                entry("GRAM_TON", 1.0 / 1000000),
                entry("GRAM_POUND", 1.0 / 454),
                entry("GRAM_OUNCE", 1.0 / 28.35),
                entry("KILOGRAM_KILOGRAM", 1.0),
                entry("KILOGRAM_MILLIGRAM", 1000000.0),
                entry("KILOGRAM_GRAM", 1000.0),
                entry("KILOGRAM_TON", 1.0 / 1000),
                entry("KILOGRAM_POUND", 1.0 / 0.454),
                entry("KILOGRAM_OUNCE", 1.0 / 0.02835),
                entry("TON_TON", 1.0),
                entry("TON_MILLIGRAM", 1000000000.0),
                entry("TON_GRAM", 1000000.0),
                entry("TON_KILOGRAM", 1000.0),
                entry("TON_POUND", 1.0 / 0.000454),
                entry("TON_OUNCE", 1.0 / 0.00002835),
                entry("POUND_POUND", 1.0),
                entry("POUND_MILLIGRAM", 453592.0),
                entry("POUND_GRAM", 454.0),
                entry("POUND_KILOGRAM", 0.454),
                entry("POUND_TON", 0.000454),
                entry("POUND_OUNCE", 16.0)
        );
    }
}
