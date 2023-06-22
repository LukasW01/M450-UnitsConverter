package dev.bene;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {

    private MongoDB mongoDB;

    public DatabaseTest() {
        mongoDB = new MongoDB();
    }

    @Test
    public void testDBOperations() {
        mongoDB.setHistory(new Document("test", "test"));
        assertTrue(mongoDB.getHistory().into(new ArrayList<>()).size() > 0);
    }
}
