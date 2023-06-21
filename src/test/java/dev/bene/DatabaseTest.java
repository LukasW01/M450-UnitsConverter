package dev.bene;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {
    @Test
    public void testDBOperations() {
        MongoDB mongoDB = new MongoDB();
        mongoDB.setHistory(new Document("test", "test"));
        assertTrue(mongoDB.getHistory().into(new ArrayList<>()).size() > 0);
    }
}
