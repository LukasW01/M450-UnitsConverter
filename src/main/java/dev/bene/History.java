package dev.bene;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.FindIterable;
import dev.bene.db.MongoDB;
import dev.bene.db.SQLite;
import org.bson.Document;

public class History {
    private final List<Converter> history;
    //private final MongoDB mongoDB;
    private final SQLite sqlite;
    private final Converter converter;

    public History() {
        history = new ArrayList<>();
        //mongoDB = new MongoDB();
        sqlite = new SQLite();
        converter = new Converter();

        loadHistory();
    }

    public List<Converter> getHistory() {
        return history;
    }

    public void addHistory(Converter converter) {
        history.add(converter);
    }

    public void addHistory(ResultSet resultSet) {
        history.add(converter.getHistory(resultSet));
    }

    public void loadHistory() {
        //FindIterable<Document> historyList = mongoDB.getHistory();
        ResultSet resultSet = sqlite.getHistory();

        //for (Document doc : historyList) {
        //    addHistory(converter.fromBSON(doc));
        //}
        try {
            while (resultSet.next()) {
                addHistory(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error getting documents: " + e);
        }
    }

    public void exportCSV() {
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(null, "History is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            FileWriter writer = getFileWriter();
            writer.close();
            JOptionPane.showMessageDialog(null, "History exported to history.csv", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error writing to file", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
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
