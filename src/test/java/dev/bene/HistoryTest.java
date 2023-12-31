package dev.bene;

import dev.bene.converter.Converter;
import dev.bene.history.History;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.*;
import static org.mockito.Mockito.*;

public class HistoryTest {

    private File file;
    private History history;
    private Converter converter;

    public HistoryTest() {
        file = new File("history.csv");
        history = new History();
        converter = new Converter();
    }

    @AfterEach
    public void tearDown()  {
        try {
            Files.deleteIfExists(Path.of("history.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExportCSVEmptyHistory() {
        //clear history then export
        history.getHistory().clear();
        history.exportCSV();

        verify(mock(JOptionPane.class), times(1));
        assertFalse(file.exists());
    }

    @Test
    public void testExportCSV() {
        //add default data
        converter.convertValue(1, "TON", "KILOGRAM", "WEIGHT");

        history.exportCSV();
        assertTrue(file.exists());
    }

    @Test
    public void testLoadHistory() {
        //add default data
        converter.convertValue(1, "TON", "KILOGRAM", "WEIGHT");

        history.loadHistory();
        List<Converter> historyList = history.getHistory();
        assertFalse(historyList.isEmpty());
    }
}
