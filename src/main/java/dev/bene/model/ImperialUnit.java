package dev.bene.model;

import java.util.HashMap;
import java.util.Map;

public enum ImperialUnit {

    FOOT,
    MILES,
    YARD;

    public static ImperialUnit create(String unit) {
        switch (unit) {
            case "MILES":
                return ImperialUnit.MILES;
            case "YARD":
                return ImperialUnit.YARD;
            case "FOOT":
                return ImperialUnit.FOOT;
            default:
                return null;
        }
    }

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(ImperialUnit from, String to) {
        return conversionMap.get(from.name() + "_" + to);
    }

    private static Map<String, Double> createConversionMap() {
        Map<String, Double> conversionMap = new HashMap<>();
        conversionMap.put("MILES_MILES", 1.0);
        conversionMap.put("MILES_FOOT", 5280.0);
        conversionMap.put("MILES_YARD", 1760.0);
        conversionMap.put("YARD_YARD", 1.0);
        conversionMap.put("YARD_FOOT", 3.0);
        conversionMap.put("YARD_MILES", 1.0 / 1760);
        conversionMap.put("FOOT_FOOT", 1.0);
        conversionMap.put("FOOT_YARD", 1.0 / 3);
        conversionMap.put("FOOT_MILES", 1.0 / 5280);
        return conversionMap;
    }
}
