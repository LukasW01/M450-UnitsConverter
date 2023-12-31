package dev.bene;

import dev.bene.db.SQLite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTest {

    private final SQLite sqlite;

    public DatabaseTest() {
        sqlite = new SQLite();
    }

    @Test
    public void testDBOperations() {
        sqlite.setHistory(1.0, 1000.0, "KILOMETER", "METERS", "input * 1000.0", "KILOMETERS");
        assertNotNull(sqlite.getHistory());
    }
}
