// SISTApp.java
// Purpose: Main entry point — launches the SIST application window

import javax.swing.*;

public class SISTApp {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            SISTFrame frame = new SISTFrame();
            frame.setVisible(true);
        });
    }
}