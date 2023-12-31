package dev.bene.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {

    private static Statement statement;

    public SQLite() {
        connectDB();
    }

    public void connectDB() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:unit-converter.db");

            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS history (id INTEGER PRIMARY KEY AUTOINCREMENT, input REAL, output REAL, from_unit TEXT, to_unit TEXT, formula TEXT, unit TEXT)");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
        }
    }

    public void setHistory(Double input, Double output, String from, String to, String formula, String unit) {
        try {
            statement.executeUpdate("INSERT INTO history (input, output, from_unit, to_unit, formula, unit) VALUES ('" + input + "', '" + output + "', '" + from + "', '" + to + "', '" + formula + "', '" + unit + "')");
        } catch (SQLException e) {
            System.out.println("Error inserting document: " + e);
        }
    }

    public ResultSet getHistory() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM history");
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error getting documents: " + e);
            return null;
        }
    }

}
