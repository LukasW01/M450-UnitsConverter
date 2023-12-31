package dev.bene.unit;

public enum Units {
    LENGTH(LengthUnit.class),
    WEIGHT(WeightUnit.class);

    private Units(Class<? extends Enum<?>> enumClass) {}

    public Enum<?>[] getUnitsByType(String type) {
        return switch (type) {
            case "LENGTH" -> LengthUnit.values();
            case "WEIGHT" -> WeightUnit.values();
            default -> null;
        };
    }
}
