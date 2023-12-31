package dev.bene;

import dev.bene.converter.Converter;
import dev.bene.history.History;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.mockito.Mockito.*;

public class HistoryTest {

    private final File file;
    private final History history;
    private final Converter converter;

    public HistoryTest() {
        file = new File("history.csv");
        history = new History();
        converter = new Converter();
    }

    @BeforeEach
    public void addDefaultData() {
        converter.convertValue(1, "TON", "KILOGRAM", "WEIGHT");
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

        assertFalse(file.exists());
    }

    @Test
    public void testExportCSV() {
        history.exportCSV();
        assertTrue(file.exists());
    }

    @Test
    public void testLoadHistory() {
        history.loadHistory();
        List<Converter> historyList = history.getHistory();
        assertFalse(historyList.isEmpty());
    }
}
