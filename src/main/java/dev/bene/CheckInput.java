package dev.bene;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CheckInput extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)  {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.insert(offset, text);

            if (isNumeric(sb.toString())) {
                super.insertString(fb, offset, text, attr);
            }
        } catch (BadLocationException e) {
            System.err.println("BadLocationException" + e);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)  {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.replace(offset, offset + length, text);

            if (isNumeric(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        } catch (BadLocationException e) {
            System.err.println("BadLocationException" + e);
        }
    }

    private boolean isNumeric(String text) {
        return text.matches("[0-9]*\\.?[0-9]*");
    }
}
