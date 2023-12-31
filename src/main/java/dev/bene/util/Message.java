package dev.bene.util;

import javax.swing.*;
import java.awt.*;

public class Message {

    public static void jOptionPane(String message, String title, int messageType) {
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println(message);
        } else {
            JOptionPane.showMessageDialog(null, message, title, messageType);
        }
    }
}
