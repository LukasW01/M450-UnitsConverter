package dev.bene.db;

import java.sql.*;

public class SQLite {

    private static Statement statement;

    public SQLite() {
        connectDB();
    }

    public void connectDB() {
        try {
            statement = DriverManager.getConnection("jdbc:sqlite:unit-converter.db").createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS history (id INTEGER PRIMARY KEY AUTOINCREMENT, input REAL, output REAL, from_unit TEXT, to_unit TEXT, formula TEXT, unit TEXT)");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
        }
    }

    public void setHistory(Double input, Double output, String from, String to, String formula, String unit) {
        try {
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement("INSERT INTO history (input, output, from_unit, to_unit, formula, unit) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setDouble(1, input);
            preparedStatement.setDouble(2, output);
            preparedStatement.setString(3, from);
            preparedStatement.setString(4, to);
            preparedStatement.setString(5, formula);
            preparedStatement.setString(6, unit);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting document: " + e);
        }
    }

    public ResultSet getHistory() {
        try {
            return statement.executeQuery("SELECT * FROM history");
        } catch (SQLException e) {
            System.out.println("Error getting documents: " + e);
            return null;
        }
    }

}
