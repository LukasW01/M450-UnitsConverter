package dev.bene;

import dev.bene.db.MongoDB;
import dev.bene.db.SQLite;
import dev.bene.unit.LengthUnit;
import dev.bene.unit.WeightUnit;
import javax.swing.*;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {
    private Double input, output;
    private String from, to, formula, unit;
    //private MongoDB mongoDB;
    private SQLite sqlite;

    public Converter(double input, double output, String from, String to, String formula, String unit) {
        this.input = input;
        this.output = output;
        this.from = from;
        this.to = to;
        this.formula = formula;
        this.unit = unit;
    }

    public Converter() {
        sqlite = new SQLite();
        //mongoDB = new MongoDB();

        sqlite.connectDB();
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

            //mongoDB.setHistory(toBSON());
            sqlite.setHistory(input, output, from, to, formula, unit);
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

    public void setHistory(Converter converter) {
        sqlite.setHistory(
                converter.getInput() != null ? converter.getInput() : Double.valueOf(0),
                converter.getOutput() != null ? converter.getOutput() : Double.valueOf(0),
                converter.getFrom() != null ? converter.getFrom() : "",
                converter.getTo() != null ? converter.getTo() : "",
                converter.getFormula() != null ? converter.getFormula() : "",
                converter.getUnit() != null ? converter.getUnit() : ""
        );
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

    public Converter getHistory(ResultSet resultSet) {
        try {
            return new Converter(
                    resultSet.getDouble("input"),
                    resultSet.getDouble("output"),
                    resultSet.getString("from_unit"),
                    resultSet.getString("to_unit"),
                    resultSet.getString("formula"),
                    resultSet.getString("unit")
            );
        } catch (SQLException e) {
            System.out.println("Error getting documents: " + e);
            return null;
        }
    }
}
