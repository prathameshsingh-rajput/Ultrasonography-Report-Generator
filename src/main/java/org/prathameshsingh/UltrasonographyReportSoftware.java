package org.prathameshsingh;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UltrasonographyReportSoftware {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set a cross-platform look and feel (optional)
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

                // Global software colors
                UIManager.put("Panel.background", new java.awt.Color(224, 243, 255));
                UIManager.put("Label.foreground", new java.awt.Color(0, 0, 139));
                UIManager.put("TextField.background", new java.awt.Color(255, 255, 240));
                UIManager.put("TextArea.background", new java.awt.Color(255, 255, 240));
                UIManager.put("Button.background", new java.awt.Color(173, 216, 230));
                UIManager.put("Button.foreground", new java.awt.Color(0, 0, 139));

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

        });
    }
}
