package dev.bene;

import org.bson.Document;
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
        history.exportCSV();
        assert file.exists();
    }

    @Test
    public void testHistory() {
        history.addHistory(new Converter(1, 1000, "KILOMETER", "METERS", "input * 1000.0", "KILOMETERS"));
        history.loadHistory();

        List<Converter> historyList = history.getHistory();
        assertNotNull(historyList);
    }
}
