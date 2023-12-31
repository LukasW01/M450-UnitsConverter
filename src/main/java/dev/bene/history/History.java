package dev.bene.history;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dev.bene.converter.Converter;
import dev.bene.db.SQLite;
import dev.bene.util.Message;

public class History {
    private final List<Converter> history;
    private final SQLite sqlite;
    private final Converter converter;

    public History() {
        history = new ArrayList<>();
        sqlite = new SQLite();
        converter = new Converter();

        loadHistory();
    }

    public List<Converter> getHistory() {
        return history;
    }

    public void addHistory(ResultSet resultHistory) {
        history.add(converter.getHistory(resultHistory));
    }

    public void loadHistory() {
        ResultSet resultHistory = sqlite.getHistory();

        try {
            while (resultHistory.next()) {
                addHistory(resultHistory);
            }
        } catch (SQLException e) {
            System.err.println("Error getting documents: " + e);
        }
    }

    public void exportCSV() {
        if (history.isEmpty()) {
            Message.jOptionPane("History is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            FileWriter writer = getFileWriter();
            writer.close();
            Message.jOptionPane("History exported to history.csv", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            Message.jOptionPane("Error writing to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private FileWriter getFileWriter() throws IOException {
        FileWriter writer = new FileWriter("history.csv");
        writer.write("Input,Output,From,To,Formula,Unit\n");
        for (Converter converter : history) {
            writer.write(
               converter.getInput() + "," +
                   converter.getOutput() + "," +
                   converter.getFrom() + "," +
                   converter.getTo() + "," +
                   converter.getFormula() + "," +
                   converter.getUnit() + "\n"
            );
        }
        return writer;
    }
}
