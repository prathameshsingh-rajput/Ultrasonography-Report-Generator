package org.prathameshsingh;

import com.formdev.flatlaf.FlatLightLaf; // FlatLaf import

import javax.swing.*;
import java.awt.*;

public class UltrasonographyReportSoftware {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Apply modern FlatLaf
                UIManager.setLookAndFeel(new FlatLightLaf());

                UIManager.put("Panel.background", new Color(182, 228, 241));       // #ECEFF1
                UIManager.put("Label.foreground", new Color(38, 50, 56));          // #263238

                UIManager.put("TextField.background", Color.WHITE);
                UIManager.put("TextField.foreground", new Color(38, 50, 56));      // #263238
                UIManager.put("TextField.border", BorderFactory.createLineBorder(new Color(176, 190, 197))); // #B0BEC5

                UIManager.put("PasswordField.background", Color.WHITE);
                UIManager.put("PasswordField.foreground", new Color(38, 50, 56));  // #263238
                UIManager.put("TextArea.background", Color.WHITE);
                UIManager.put("TextArea.foreground", new Color(38, 50, 56));       // #263238

                UIManager.put("Button.background", new Color(25, 118, 210));       // #1976D2
                UIManager.put("Button.foreground", Color.WHITE);
                UIManager.put("Button.select", new Color(21, 101, 192));           // #1565C0

                UIManager.put("ComboBox.background", new Color(236, 239, 241));    // #CFD8DC
                UIManager.put("ComboBox.foreground", new Color(38, 50, 56));       // #263238
                UIManager.put("ComboBox.selectionBackground", new Color(144, 202, 249)); // #90CAF9

                UIManager.put("MenuBar.background", new Color(236, 239, 241));     // #ECEFF1
                UIManager.put("MenuBar.foreground", new Color(55, 71, 79));        // #37474F

                UIManager.put("Menu.background", new Color(236, 239, 241));        // #ECEFF1
                UIManager.put("Menu.foreground", new Color(55, 71, 79));           // #37474F
                UIManager.put("Menu.selectionBackground", new Color(179, 229, 252)); // #B3E5FC
                UIManager.put("Menu.selectionForeground", new Color(38, 50, 56));  // #263238

                UIManager.put("MenuItem.background", Color.WHITE);
                UIManager.put("MenuItem.foreground", new Color(55, 71, 79));       // #37474F
                UIManager.put("MenuItem.selectionBackground", new Color(179, 229, 252)); // #B3E5FC
                UIManager.put("MenuItem.selectionForeground", new Color(38, 50, 56)); // #263238


            } catch (Exception e) {
                e.printStackTrace();
            }

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
