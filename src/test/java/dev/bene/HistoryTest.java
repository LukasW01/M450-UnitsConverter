package dev.bene;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.List;

public class HistoryTest {

    private File file;
    private History history;
    private Converter converter;

    public HistoryTest() {
        file = new File("history.csv");
        history = new History();
        converter = new Converter();
    }

    @Test
    public void testExportCSV() {
        history.addHistory(new Converter(1, 1000, "TON", "KILOGRAM", "input * 1000.0", "WEIGHT"));

        history.exportCSV();
        assertTrue(file.exists());
    }

    @Test
    public void testLoadHistory() {
        converter.convertValue(1,"TON", "KILOGRAM", "WEIGHT");

        history.loadHistory();
        List<Converter> historyList = history.getHistory();
        assertFalse(historyList.isEmpty());
    }
}
