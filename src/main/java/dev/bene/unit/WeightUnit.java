package dev.bene.unit;

import java.util.Map;
import static java.util.Map.entry;

    public enum WeightUnit {
    TON,
    KILOGRAM,
    GRAM,
    MILLIGRAM,
    POUND,
    OUNCE;

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(String from, String to) {
        return conversionMap.get(from + "_" + to);
    }

    private static Map<String, Double> createConversionMap() {
        return Map.<String, Double>ofEntries(
                entry("MILLIGRAM_GRAM", 1.0 / 1000),
                entry("MILLIGRAM_KILOGRAM", 1.0 / 1000000),
                entry("MILLIGRAM_TON", 1.0 / 1000000000),
                entry("GRAM_MILLIGRAM", 1000.0),
                entry("GRAM_KILOGRAM", 1.0 / 1000),
                entry("GRAM_TON", 1.0 / 1000000),
                entry("KILOGRAM_MILLIGRAM", 1000000.0),
                entry("KILOGRAM_GRAM", 1000.0),
                entry("KILOGRAM_TON", 1.0 / 1000),
                entry("TON_MILLIGRAM", 1000000000.0),
                entry("TON_GRAM", 1000000.0),
                entry("TON_KILOGRAM", 1000.0),
                entry("POUND_OUNCE", 16.0),
                entry("OUNCE_POUND", 1.0 / 16)
        );
    }
}
