package dev.bene;

import dev.bene.db.MongoDB;
import dev.bene.db.SQLite;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseTest {

    //private MongoDB mongoDB;
    private SQLite sqlite;


    public DatabaseTest() {
        //mongoDB = new MongoDB();
        sqlite = new SQLite();
    }


    /*
    @Test
    public void testDBOperations() {
        mongoDB.setHistory(new Converter(1, 1000, "KILOMETER", "METERS", "input * 1000.0", "KILOMETERS").toBSON());
        assertNotNull(mongoDB.getHistory());
    }
     */

    @Test
    public void testDBOperations() {
        sqlite.setHistory(1.0, 1000.0, "KILOMETER", "METERS", "input * 1000.0", "KILOMETERS");
        assertNotNull(sqlite.getHistory());
    }
}
