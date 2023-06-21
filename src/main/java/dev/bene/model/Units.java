package dev.bene.model;

public enum Units {
    LENGTH(LengthUnit.class),
    WEIGHT(WeightUnit.class);

    Units(Class<? extends Enum<?>> enumClass) {}

    public Enum<?>[] getUnitsByType(String type) {
        if (type.equals("LENGTH")) {
            return LengthUnit.values();
        } else if (type.equals("WEIGHT")) {
            return WeightUnit.values();
        } else {
            return null;
        }
    }
}
