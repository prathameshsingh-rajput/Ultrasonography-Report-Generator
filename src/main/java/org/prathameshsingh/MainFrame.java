package org.prathameshsingh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JLabel hospitalName;
    private JButton minorReportButton;
    private JButton majorReportButton;
    private JButton photoReportButton;
    private JLabel  allRightsReserve ;


    Font buttonFont = new Font("Roboto", Font.BOLD, 20);
    Font componeyNameFont = new Font("Roboto", Font.BOLD, 40);
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


        hospitalName = new JLabel("PSR-SoftHub");
        hospitalName.setFont(componeyNameFont);
        hospitalName.setForeground(UIManager.getColor("Label.foreground"));
        hospitalName.setBounds(620, 150, 500, 50);
        add(hospitalName);

        minorReportButton = new JButton("Minor Report");
        minorReportButton.setFont(buttonFont);
        minorReportButton.setBackground(UIManager.getColor("Button.background"));
        minorReportButton.setForeground(UIManager.getColor("Label.foreground"));
        minorReportButton.setBounds(240, 450, 250,60);
        add(minorReportButton);

        majorReportButton = new JButton("Major Report");
        majorReportButton.setFont(buttonFont);
        majorReportButton.setBackground(UIManager.getColor("Button.background"));
        majorReportButton.setForeground(UIManager.getColor("Label.foreground"));
        majorReportButton.setBounds(600, 450, 250,60);
        add(majorReportButton);

        photoReportButton = new JButton("Photo Report");
        photoReportButton.setFont(buttonFont);
        photoReportButton.setBackground(UIManager.getColor("Button.background"));
        photoReportButton.setForeground(UIManager.getColor("Label.foreground"));
        photoReportButton.setBounds(975, 450, 250,60);
        add(photoReportButton);

//        allRightsReserve = new JLabel("© 2024 Prathamesh Singh Rajput [PSR]. All rights reserved.");
//        allRightsReserve.setFont(rightReserveFont);
//        allRightsReserve.setForeground(new Color(0, 0, 0)); // Set the color to red, for example
//        allRightsReserve.setBounds(470, 520, 700, 30);
//        add(allRightsReserve);

        minorReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MinorReportMainPanel minorReportMainPanel = new MinorReportMainPanel();
                setContentPane(minorReportMainPanel);  // Set the content pane to the new MainPanel
                validate();  // Validate the new content pane
                repaint();   // Repaint the frame
            }
        });

        majorReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MajorReportMainPanel majorReportMainPanel = new MajorReportMainPanel();
                setContentPane(majorReportMainPanel);
                validate();
                repaint();
            }
        });

        photoReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhotoReportMainPanel photoReportMainPanel = new PhotoReportMainPanel();
                setContentPane(photoReportMainPanel);
                validate();
                repaint();
            }
        });

        // Custom frame icon
        ImageIcon icon = new ImageIcon("C:/Users/hp22r/OneDrive/Desktop/Hospital Project/PsrSoftware/src/main/resources/appIcon.png"); // Replace with the path to your icon file
        setIconImage(icon.getImage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }


}

