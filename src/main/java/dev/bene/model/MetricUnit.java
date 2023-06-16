package dev.bene.model;

import java.util.HashMap;
import java.util.Map;

public enum MetricUnit {

    KM,
    M,
    CM,
    MM;

    public static MetricUnit create(String unit) {
        switch (unit) {
            case "KM":
                return MetricUnit.KM;
            case "M":
                return MetricUnit.M;
            case "CM":
                return MetricUnit.CM;
            case "MM":
                return MetricUnit.MM;
            default:
                return null;
        }
    }

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(MetricUnit from, String to) {
        return conversionMap.get(from.name() + "_" + to);
    }

    private static Map<String, Double> createConversionMap() {
        Map<String, Double> conversionMap = new HashMap<>();
        conversionMap.put("KM_KM", 1.0);
        conversionMap.put("KM_M", 1000.0);
        conversionMap.put("KM_CM", 100000.0);
        conversionMap.put("KM_MM", 1000000.0);
        conversionMap.put("M_M", 1.0);
        conversionMap.put("M_KM", 1.0 / 1000);
        conversionMap.put("M_CM", 100.0);
        conversionMap.put("M_MM", 1000.0);
        conversionMap.put("CM_CM", 1.0);
        conversionMap.put("CM_KM", 1.0 / 100000);
        conversionMap.put("CM_M", 1.0 / 100);
        conversionMap.put("CM_MM", 10.0);
        conversionMap.put("MM_MM", 1.0);
        conversionMap.put("MM_KM", 1.0 / 1000000);
        conversionMap.put("MM_M", 1.0 / 1000);
        conversionMap.put("MM_CM", 1.0 / 10);
        return conversionMap;
    }
}
