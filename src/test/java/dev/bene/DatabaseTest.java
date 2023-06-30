package dev.bene;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTest {

    private MongoDB mongoDB;

    public DatabaseTest() {
        mongoDB = new MongoDB();
    }

    @Test
    public void testDBOperations() {
        mongoDB.setHistory(new Converter(1, 1000, "KILOMETER", "METERS", "input * 1000.0", "KILOMETERS").toBSON());
        assertNotNull(mongoDB.getHistory());
    }
}
