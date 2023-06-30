package dev.bene;

import dev.bene.unit.LengthUnit;
import dev.bene.unit.WeightUnit;
import javax.swing.*;
import org.bson.Document;

public class Converter {
    private Double input, output;
    private String from, to, formula, unit;
    private MongoDB mongoDB;

    public Converter(double input, double output, String from, String to, String formula, String unit) {
        this.input = input;
        this.output = output;
        this.from = from;
        this.to = to;
        this.formula = formula;
        this.unit = unit;
    }

    public Converter() {
        mongoDB = new MongoDB();
    }

    public void convertValue(double input, String from, String to, String unit) {
        Double conversionRatioLength = LengthUnit.getConversionRatio(from, to);
        Double conversionRatioWeight = WeightUnit.getConversionRatio(from, to);

        if (conversionRatioLength != null || conversionRatioWeight != null) {
            this.input = input;
            this.from = from;
            this.to = to;
            this.unit = unit;
            this.output = conversionRatioLength != null ? input * conversionRatioLength : input * conversionRatioWeight;
            this.formula = conversionRatioLength != null ? "input * " + conversionRatioLength  : "input * " + conversionRatioWeight;

            mongoDB.setHistory(toBSON());
        } else {
            JOptionPane.showMessageDialog(null, "Conversion not possible with these units", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Double getInput() {
        return input;
    }

    public Double getOutput() {
        return output;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getFormula() {
        return formula;
    }

    public String getUnit() {
        return unit;
    }

    public Document toBSON() {
        return new Document()
                .append("history", true)
                .append("input", input != null ? input : Double.valueOf(0))
                .append("output", output != null ? output : Double.valueOf(0))
                .append("from", from != null ? from : "")
                .append("to", to != null ? to : "")
                .append("formula", formula != null ? formula : "")
                .append("unit", unit != null ? unit : "");
    }

    public Converter fromBSON(Document doc) {
        if (!(doc == null)) {
            return new Converter(
                    doc.getDouble("input"),
                    doc.getDouble("output"),
                    doc.getString("from"),
                    doc.getString("to"),
                    doc.getString("formula"),
                    doc.getString("unit")
            );
        }
        return null;
    }
}
