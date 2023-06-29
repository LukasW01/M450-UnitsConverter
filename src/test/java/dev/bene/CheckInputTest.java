package dev.bene;

import javax.swing.text.*;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckInputTest {
    
    private CheckInput checkInput;
    private Document document;
    private AttributeSet attr;
    private StringBuilder sb;

    public CheckInputTest() {
        checkInput = new CheckInput();
        document = new PlainDocument();
        attr = new SimpleAttributeSet();
        sb = new StringBuilder();
    }

    @Test
    public void testInsertReplace() {
        try {
            //with numeric value
            sb.append("123");

            document.insertString(0, sb.toString(), attr);
            checkInput.replace((DocumentFilter.FilterBypass) Mockito.when(Mockito.mock(DocumentFilter.FilterBypass.class).getDocument()).thenReturn(document), 1, 1, "a", attr);

            assertEquals("123", document.getText(0, document.getLength()));

            //with string value
            sb = new StringBuilder();
            sb.append("abc");

            document.remove(0, document.getLength());
            document.insertString(0, sb.toString(), attr);
            checkInput.replace((DocumentFilter.FilterBypass) Mockito.when(Mockito.mock(DocumentFilter.FilterBypass.class).getDocument()).thenReturn(document), 1, 1, "d", attr);

            assertEquals("abc", document.getText(0, document.getLength()));
        } catch (BadLocationException e) {
            System.err.println("BadLocationException" + e);
        }

    }

    @Test
    public void testIsNumeric() {
        assertTrue(checkInput.isNumeric("123"));
        assertTrue(checkInput.isNumeric("12.34"));
        assertTrue(checkInput.isNumeric(".123"));
        assertFalse(checkInput.isNumeric("abc"));
        assertFalse(checkInput.isNumeric("123a"));
        assertFalse(checkInput.isNumeric("12.34.56"));
    }
}
