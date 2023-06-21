package dev.bene;

import javax.swing.text.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckInputTest {

    @Test
    public void testInsertString() throws BadLocationException {
        CheckInput checkInput = new CheckInput();
        Document document = new PlainDocument();
        AttributeSet attr = new SimpleAttributeSet();

        StringBuilder sb = new StringBuilder();
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
            public void insertString(int offset, String text, AttributeSet attrs) throws BadLocationException {
                document.insertString(offset, text, attrs);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attrs);
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
            public void insertString(int offset, String text, AttributeSet attrs) throws BadLocationException {
                document.insertString(offset, text, attrs);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attrs);
            }
        }, 3, "4", attr);

        assertEquals("abc", document.getText(0, document.getLength()));
    }

    @Test
    public void testReplace() throws BadLocationException {
        CheckInput checkInput = new CheckInput();
        Document document = new PlainDocument();
        AttributeSet attrs = new SimpleAttributeSet();

        StringBuilder sb = new StringBuilder();
        sb.append("123");
        document.insertString(0, sb.toString(), attrs);

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
            public void insertString(int offset, String text, AttributeSet attrs) throws BadLocationException {
                document.insertString(offset, text, attrs);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attrs);
            }
        }, 1, 1, "a", attrs);

        assertEquals("123", document.getText(0, document.getLength()));

        sb = new StringBuilder();
        sb.append("abc");
        document.remove(0, document.getLength());
        document.insertString(0, sb.toString(), attrs);

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
            public void insertString(int offset, String text, AttributeSet attrs) throws BadLocationException {
                document.insertString(offset, text, attrs);
            }

            @Override
            public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                document.remove(offset, length);
                document.insertString(offset, text, attrs);
            }
        }, 1, 1, "d", attrs);

        assertEquals("abc", document.getText(0, document.getLength()));
    }

    @Test
    public void testIsNumeric() {
        CheckInput checkInput = new CheckInput();

        assertTrue(checkInput.isNumeric("123"));
        assertTrue(checkInput.isNumeric("12.34"));
        assertTrue(checkInput.isNumeric(".123"));
        assertFalse(checkInput.isNumeric("abc"));
        assertFalse(checkInput.isNumeric("123a"));
        assertFalse(checkInput.isNumeric("12.34.56"));
    }
}
