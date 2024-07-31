package org.prathameshsingh;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JLabel hospitalName;
    private JButton minorReportButton;
    private JButton majorReportButton;
    private JButton photoReportButton;
    private JLabel allRightsReserve;

    Font buttonFont = new Font("Roboto", Font.BOLD, 20);
    Font componeyNameFont = new Font("Roboto", Font.BOLD, 40);
    Font robotoBold = new Font("Roboto", Font.BOLD, 26);

//    Font rightReserveFont = new Font("Roboto", Font.PLAIN, 11);

    public MainFrame() {
        setLayout(null);

        setTitle("Ultrasonography Report Generator");
        // Full screen Frame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width, screenSize.height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(false);

        // Initially set the content pane to the login panel
        setContentPane(new LoginPanel(this));

        // Custom frame icon
        ImageIcon icon = new ImageIcon("C:/Users/hp22r/OneDrive/Desktop/Hospital Project/PsrSoftware/src/main/resources/appIcon.png"); // Replace with the path to your icon file
        setIconImage(icon.getImage());
    }

    public void showMainPanel() {
        getContentPane().removeAll();
        setLayout(null);

        hospitalName = new JLabel("PSR-SoftHub");
        hospitalName.setFont(componeyNameFont);
        hospitalName.setForeground(UIManager.getColor("Label.foreground"));
        hospitalName.setBounds(620, 150, 500, 50);
        add(hospitalName);

        minorReportButton = new JButton("Minor Report");
        minorReportButton.setFont(robotoBold);
        minorReportButton.setBackground(new Color(30, 144, 255));
        minorReportButton.setForeground(Color.WHITE);
        minorReportButton.setBorderPainted(false);
        minorReportButton.setBounds(240, 450, 250, 60);
        add(minorReportButton);

        majorReportButton = new JButton("Major Report");
        majorReportButton.setFont(robotoBold);
        majorReportButton.setBackground(new Color(30, 144, 255));
        majorReportButton.setForeground(Color.WHITE);
        majorReportButton.setBorderPainted(false);
        majorReportButton.setBounds(600, 450, 250, 60);
        add(majorReportButton);

        photoReportButton = new JButton("Photo Report");
        photoReportButton.setFont(robotoBold);
        photoReportButton.setBackground(new Color(30, 144, 255));
        photoReportButton.setForeground(Color.WHITE);
        photoReportButton.setBorderPainted(false);
        photoReportButton.setBounds(975, 450, 250, 60);
        add(photoReportButton);

//        allRightsReserve = new JLabel("© 2024 Prathamesh Singh Rajput [PSR]. All rights reserved.");
//        allRightsReserve.setFont(rightReserveFont);
//        allRightsReserve.setForeground(new Color(0, 0, 0)); // Set the color to red, for example
//        allRightsReserve.setBounds(470, 520, 700, 30);
//        add(allRightsReserve);

        minorReportButton.addActionListener(e -> {
            MinorReportMainPanel minorReportMainPanel = new MinorReportMainPanel();
            setContentPane(minorReportMainPanel);  // Set the content pane to the new MainPanel
            validate();  // Validate the new content pane
            repaint();   // Repaint the frame
        });

        majorReportButton.addActionListener(e -> {
            MajorReportMainPanel majorReportMainPanel = new MajorReportMainPanel();
            setContentPane(majorReportMainPanel);
            validate();
            repaint();
        });

        photoReportButton.addActionListener(e -> {
            PhotoReportMainPanel photoReportMainPanel = new PhotoReportMainPanel();
            setContentPane(photoReportMainPanel);
            validate();
            repaint();
        });

        validate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
