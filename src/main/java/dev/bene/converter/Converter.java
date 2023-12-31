package dev.bene.converter;

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

        sqlite.connectDB();
    }

    public void convertValue(double input, String from, String to, String unit) {
        this.input = input;
        this.from = from;
        this.to = to;
        this.unit = unit;

        switch (unit) {
            case "LENGTH" -> {
                convertLength(input, from, to);
                setHistory(this);
            }
            case "WEIGHT" -> {
                convertWeight(input, from, to);
                setHistory(this);
            }
            default -> throw new IllegalArgumentException("Unsupported unit type: " + unit);
        }
    }

    private void convertLength(double input, String from, String to) {
        Double conversionRatio = LengthUnit.getConversionRatio(from, to);
        if (conversionRatio == null) {
            throw new IllegalArgumentException("Conversion not possible with these units");
        }
        this.output = input * conversionRatio;
        this.formula = "input * " + conversionRatio;
    }

    private void convertWeight(double input, String from, String to) {
        Double conversionRatio = WeightUnit.getConversionRatio(from, to);
        if (conversionRatio == null) {
            throw new IllegalArgumentException("Conversion not possible with these units");
        }
        this.output = input * conversionRatio;
        this.formula = "input * " + conversionRatio;
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
