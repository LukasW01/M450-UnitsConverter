package dev.bene;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Converter> history;

    public History() {
        history = new ArrayList<Converter>();
    }

    public List<Converter> getHistory() {
        return history;
    }

    public void addHistory(Converter converter) {
        history.add(converter);
    }

    public void exportCSV() {
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(null, "History is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            FileWriter writer = new FileWriter("history.csv");
            writer.write("Input,Output,From,To,Formula\n");
            for (Converter converter : history) {
                writer.write(
                   converter.getInput() + "," + converter.getOutput() + "," + converter.getFrom() + "," + converter.getTo() + "," + converter.getFormula() + "\n"
                );
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "History exported to history.csv", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error writing to file", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }

    public void importCSV()  {
        if (new File("history.csv").exists()) {
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("history.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Converter converter = new Converter(
                        Double.parseDouble(values[0]),
                        Double.parseDouble(values[1]),
                        values[2],
                        values[3],
                        values[4]
                );
                history.add(converter);
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
