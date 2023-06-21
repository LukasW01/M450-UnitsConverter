package dev.bene;

import dev.bene.model.LengthUnit;
import dev.bene.model.WeightUnit;
import javax.swing.*;
import org.bson.Document;

public class Converter {
    private double input, output;
    private String from, to, formula;
    private MongoDB mongoDB;

    public Converter(double input, double output, String from, String to, String formula) {
        this.input = input;
        this.output = output;
        this.from = from;
        this.to = to;
        this.formula = formula;
    }

    public Converter() {
        mongoDB = new MongoDB();
    }

    public void convertValue(double input, String from, String to) {
        Double conversionRatioLength = LengthUnit.getConversionRatio(from, to);
        Double conversionRatioWeight = WeightUnit.getConversionRatio(from, to);

        if (conversionRatioLength != null || conversionRatioWeight != null) {
            this.input = input;
            this.from = from;
            this.to = to;
            this.output = conversionRatioLength != null ? input * conversionRatioLength : input * conversionRatioWeight;
            this.formula = conversionRatioLength != null ? "input * " + conversionRatioLength  : "input * " + conversionRatioWeight;

            mongoDB.setHistory(toBSON());
        } else {
            JOptionPane.showMessageDialog(null, "Conversion not possible", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getInput() {
        return input;
    }

    public double getOutput() {
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

    public Document toBSON() {
        return new Document().append("history", true).append("input", input).append("output", output).append("from", from).append("to", to).append("formula", formula);
    }

    public Converter fromBSON(Document doc) {
        return new Converter(
            doc.getDouble("input"), doc.getDouble("output"), doc.getString("from"), doc.getString("to"), doc.getString("formula")
        );
    }
}
