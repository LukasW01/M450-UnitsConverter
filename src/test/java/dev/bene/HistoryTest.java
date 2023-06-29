package dev.bene;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.mockito.Mockito;
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
        history.addHistory(new Converter(1, 1000, "TON", "KILOGRAM", "input * 1000.0", "WEIGHT"));

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
