package dev.bene;

import dev.bene.model.ImperialUnit;
import dev.bene.model.MetricUnit;
import dev.bene.model.WeightUnit;

import javax.swing.*;

public class Converter {
    private double Input, Output;
    private String From, To, Formula;
    private History history;

    public Converter(double Input, double Output, String From, String To, String Formula) {
        this.Input = Input;
        this.Output = Output;
        this.From = From;
        this.To = To;
        this.Formula = Formula;
    }

    public Converter() {
        history = new History();
    }

    public void convertValue(double Input, String From, String To) {
        MetricUnit metricUnit = MetricUnit.create(From);
        if (metricUnit == null) {
            ImperialUnit imperialUnit = ImperialUnit.create(From);
            if (imperialUnit == null) {
                WeightUnit weightUnit = WeightUnit.create(From);
                if (weightUnit == null) {
                    JOptionPane.showMessageDialog(null, "Invalid Unit(s)", "Error", JOptionPane.ERROR_MESSAGE);
                } else convertWeight(weightUnit,To,Input);
            } else convertImperial(imperialUnit,To,Input);
        } else convertMetric(metricUnit,To,Input);
    }

    public void convertMetric(MetricUnit metricUnit, String to, double input) {
        Double conversionRatio = MetricUnit.getConversionRatio(metricUnit, to);
        if (conversionRatio == null) {
            JOptionPane.showMessageDialog(null, "Invalid Unit(s)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.Output = input * conversionRatio;
        this.Formula = "input * " + conversionRatio;

        history.addHistory(this);
    }

    public void convertImperial(ImperialUnit imperialUnit, String to, double input) {
        Double conversionRatio = ImperialUnit.getConversionRatio(imperialUnit, to);
        if (conversionRatio == null) {
            JOptionPane.showMessageDialog(null, "Invalid Unit(s)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.Output = input * conversionRatio;
        this.Formula = "input * " + conversionRatio;

        history.addHistory(this);
    }

    public void convertWeight(WeightUnit weightUnit, String to, double input) {
        Double conversionRatio = WeightUnit.getConversionRatio(weightUnit, to);
        if (conversionRatio == null) {
            JOptionPane.showMessageDialog(null, "Invalid Unit(s)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.Output = input * conversionRatio;
        this.Formula = "input * " + conversionRatio;

        history.addHistory(this);
    }

    public double getInput() {
        return Input;
    }

    public double getOutput() {
        return Output;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public String getFormula() {
        return Formula;
    }
}
