package dev.bene;

import javax.swing.text.*;
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
    public void testInsertString() throws BadLocationException {
        sb.append("123");
        document.insertString(0, sb.toString(), attr);

        checkInput.insertString(new DocumentFilter.FilterBypass() {
            @Override
            public Document getDocument() {
                return document;
            }

            @Override
            public void remove(int offset, int length) throws BadLocationException {
                document.remove(offset, length);
            }

            @Override
            public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
                document.insertString(offset, text, attr);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attr) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attr);
            }
        }, 3, "4", attr);

        assertEquals("1234", document.getText(0, document.getLength()));

        sb = new StringBuilder();
        sb.append("abc");
        document.remove(0, document.getLength());
        document.insertString(0, sb.toString(), attr);

        checkInput.insertString(new DocumentFilter.FilterBypass() {
            @Override
            public Document getDocument() {
                return document;
            }

            @Override
            public void remove(int offset, int length) throws BadLocationException {
                document.remove(offset, length);
            }

            @Override
            public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
                document.insertString(offset, text, attr);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attr) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attr);
            }
        }, 3, "4", attr);

        assertEquals("abc", document.getText(0, document.getLength()));
    }

    @Test
    public void testReplace() throws BadLocationException {
        sb.append("123");
        document.insertString(0, sb.toString(), attr);

        checkInput.replace(new DocumentFilter.FilterBypass() {
            @Override
            public Document getDocument() {
                return document;
            }

            @Override
            public void remove(int offset, int length) throws BadLocationException {
                document.remove(offset, length);
            }

            @Override
            public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
                document.insertString(offset, text, attr);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attr) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attr);
            }
        }, 1, 1, "a", attr);

        assertEquals("123", document.getText(0, document.getLength()));

        sb = new StringBuilder();
        sb.append("abc");
        document.remove(0, document.getLength());
        document.insertString(0, sb.toString(), attr);

        checkInput.replace(new DocumentFilter.FilterBypass() {
            @Override
            public Document getDocument() {
                return document;
            }

            @Override
            public void remove(int offset, int length) throws BadLocationException {
                document.remove(offset, length);
            }

            @Override
            public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
                document.insertString(offset, text, attr);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attr) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attr);
            }
        }, 1, 1, "d", attr);

        assertEquals("abc", document.getText(0, document.getLength()));
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
