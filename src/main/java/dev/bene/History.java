package dev.bene;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class History {
    private final List<Converter> history;
    private final MongoDB mongoDB;
    private final Converter converter;

    public History() {
        history = new ArrayList<>();
        mongoDB = new MongoDB();
        converter = new Converter();

        loadHistory();
    }

    public List<Converter> getHistory() {
        return history;
    }

    public void addHistory(Converter converter) {
        history.add(converter);
    }

    public void loadHistory() {
        FindIterable<Document> historyList = mongoDB.getHistory();

        if (historyList != null) {
            for (Document doc : historyList) {
                addHistory(converter.fromBSON(doc));
            }
        }
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
}
