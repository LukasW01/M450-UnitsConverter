package dev.bene.model;

import java.util.HashMap;
import java.util.Map;

public enum WeightUnit {

    MG,
    G,
    KG,
    T,
    POUND,
    OUNCE;

    public static WeightUnit create(String unit) {
        switch (unit) {
            case "MG":
                return WeightUnit.MG;
            case "G":
                return WeightUnit.G;
            case "KG":
                return WeightUnit.KG;
            case "T":
                return WeightUnit.T;
            case "POUND":
                return WeightUnit.POUND;
            case "OUNCE":
                return WeightUnit.OUNCE;
            default:
                return null;
        }
    }

    private static final Map<String, Double> conversionMap = createConversionMap();

    public static Double getConversionRatio(WeightUnit from, String to) {
        return conversionMap.get(from.name() + "_" + to);
    }

    private static Map<String, Double> createConversionMap() {
        Map<String, Double> conversionMap = new HashMap<>();
        conversionMap.put("MG_G", 0.001);
        conversionMap.put("MG_KG", 0.000001);
        conversionMap.put("MG_T", 0.000000001);
        conversionMap.put("MG_POUND", 0.0000000022046226);
        conversionMap.put("MG_OUNCE", 0.0000000352739619);
        conversionMap.put("G_MG", 1000.0);
        conversionMap.put("G_KG", 0.001);
        conversionMap.put("G_T", 0.000001);
        conversionMap.put("G_POUND", 0.0022046226);
        conversionMap.put("G_OUNCE", 0.0352739619);
        conversionMap.put("KG_MG", 1000000.0);
        conversionMap.put("KG_G", 1000.0);
        conversionMap.put("KG_T", 0.001);
        conversionMap.put("KG_POUND", 2.2046226);
        conversionMap.put("KG_OUNCE", 35.2739619);
        conversionMap.put("T_MG", 1000000000.0);
        conversionMap.put("T_G", 1000000.0);
        conversionMap.put("T_KG", 1000.0);
        conversionMap.put("T_POUND", 2204.6226);
        conversionMap.put("T_OUNCE", 35273.9619);
        conversionMap.put("POUND_MG", 453592.37);
        conversionMap.put("POUND_G", 453.59237);
        conversionMap.put("POUND_KG", 0.45359237);
        conversionMap.put("POUND_T", 0.00045359237);
        conversionMap.put("POUND_OUNCE", 16.0);
        conversionMap.put("OUNCE_MG", 28349.5231);
        conversionMap.put("OUNCE_G", 28.3495231);
        conversionMap.put("OUNCE_KG", 0.0283495231);
        conversionMap.put("OUNCE_T", 0.0000283495231);
        conversionMap.put("OUNCE_POUND", 0.0625);
        return conversionMap;
    }
}
