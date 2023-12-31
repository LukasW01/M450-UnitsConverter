package dev.bene;

import dev.bene.validation.CheckInput;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckInputTest {

    private final CheckInput checkInput;

    public CheckInputTest() {
        checkInput = new CheckInput();
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
