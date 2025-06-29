package org.prathameshsingh;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JMenuBar menuBar;

    public MainFrame() {
        // Full screen Frame
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize CardLayout and mainPanel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add panels to mainPanel
        mainPanel.add(new LoginPanel(this), "LoginPanel");
        mainPanel.add(new MinorReportMainPanel(), "MinorReportMainPanel");
        mainPanel.add(new MajorReportMainPanel(), "MajorReportMainPanel");
        mainPanel.add(new PhotoReportMainPanel(), "PhotoReportMainPanel");

        setContentPane(mainPanel);

        // Custom frame icon
        ImageIcon icon = new ImageIcon("C:/Users/hp22r/Desktop/Hospital Project/PsrSoftware/src/main/resources/myAppIcon.png");
        setIconImage(icon.getImage());

        // Create menu bar
        menuBar = createMenuBar();
        menuBar.setPreferredSize(new Dimension(screenSize.width, 36)); // Uniform height
        setJMenuBar(null); // Initially hidden
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0xECEFF1)); // Light grey-blue
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        Font menuFont = new Font("Roboto", Font.BOLD, 17);
        Color menuTextColor = new Color(0x37474F);

        JMenu menu = new JMenu("Menu");
        menu.setFont(menuFont);
        menu.setForeground(menuTextColor);

        JMenu view = new JMenu("View");
        view.setFont(menuFont);
        view.setForeground(menuTextColor);

        JMenu report = new JMenu("Report");
        report.setFont(menuFont);
        report.setForeground(menuTextColor);

        JMenu about = new JMenu("About");
        about.setFont(menuFont);
        about.setForeground(menuTextColor);

        // Menu items
        JMenuItem minorReportPanelItem = createMenuItem("Minor Report", "MinorReportMainPanel", KeyEvent.VK_M, KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        JMenuItem majorReportPanelItem = createMenuItem("Major Report", "MajorReportMainPanel", KeyEvent.VK_J, KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
        JMenuItem photoReportPanelItem = createMenuItem("Photo Report", "PhotoReportMainPanel", KeyEvent.VK_P, KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
        JMenuItem exitItem = createMenuItem("Exit", "LoginPanel", KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem ReportFolderItem = createFolderMenuItem("USG Report Folder", "D:/USG Reports");
        JMenuItem minorReportFolderItem = createFolderMenuItem("Minor Report Folder", "D:/USG Reports/Minor Report");
        JMenuItem majorReportFolderItem = createFolderMenuItem("Major Report Folder", "D:/USG Reports/Major Report");
        JMenuItem photoReportFolderItem = createFolderMenuItem("Photo Report Folder", "D:/USG Reports/Photo Report");

        menu.add(minorReportPanelItem);
        menu.add(majorReportPanelItem);
        menu.add(photoReportPanelItem);
        menu.addSeparator();
        menu.add(exitItem);

        view.add(ReportFolderItem);
        view.addSeparator();
        view.add(minorReportFolderItem);
        view.add(majorReportFolderItem);
        view.add(photoReportFolderItem);

        about.add(createAboutMenuItem());


        menuBar.add(menu);
        menuBar.add(report);
        menuBar.add(view);
        menuBar.add(about);

        menuBar.add(Box.createHorizontalStrut(270));


        JLabel titleLabel = new JLabel("  Ultrasonography Report Generator  ");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x1976D2));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        menuBar.add(titleLabel);

        return menuBar;
    }

    private JMenuItem createMenuItem(String title, String panelName, int mnemonic, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Roboto", Font.PLAIN, 16));
        menuItem.setForeground(new Color(0x37474F));
        menuItem.setBackground(Color.WHITE);
        menuItem.setOpaque(true);
        menuItem.setMargin(new Insets(4, 10, 4, 10));
        menuItem.setMnemonic(mnemonic);
        menuItem.setAccelerator(accelerator);
        menuItem.addActionListener(e -> showPanel(panelName));
        return menuItem;
    }

    private JMenuItem createFolderMenuItem(String title, String folderPath) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Roboto", Font.PLAIN, 16));
        menuItem.setForeground(new Color(0x37474F));
        menuItem.setBackground(Color.WHITE);
        menuItem.setOpaque(true);
        menuItem.setMargin(new Insets(4, 10, 4, 10));
        menuItem.addActionListener(e -> openFolder(folderPath));
        return menuItem;
    }

    private JMenuItem createAboutMenuItem() {
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setFont(new Font("Roboto", Font.PLAIN, 16));
        aboutItem.setForeground(new Color(0x37474F));
        aboutItem.setBackground(Color.WHITE);
        aboutItem.setOpaque(true);
        aboutItem.setMargin(new Insets(4, 10, 4, 10));
        aboutItem.addActionListener(e -> showAboutDialog());
        return aboutItem;
    }

    private void showAboutDialog() {
        JPanel aboutPanel = new JPanel(new BorderLayout(5, 5));
        aboutPanel.setBackground(Color.WHITE);

        JEditorPane infoPane = new JEditorPane("text/html",
                "<html><br><center>Ultrasonography Report Generator 2024.8.9 (Ultimate Edition) <br>" +
                        "Build #IU-746.24082003.05, built on August 14, 2024 <br>" +
                        "Software Version: 2.4<br><br> " +
                        "Subscription is activated until August 14, 2025.<br><br> " +
                        "Powered by <a href=\"https://prathameshsingh.me\">Connect Developer</a><br>" +
                        "Copyright ©2024-2025 PSR-SoftHub</center></html>");
        infoPane.setEditable(false);
        infoPane.setFont(new Font("Roboto", Font.BOLD, 15));
        infoPane.setBackground(Color.WHITE);
        infoPane.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ImageIcon aboutIcon = new ImageIcon("C:/Users/hp22r/Desktop/Hospital Project/PsrSoftware/src/main/resources/check.png");
        Image aboutImage = aboutIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        aboutIcon = new ImageIcon(aboutImage);

        aboutPanel.add(infoPane, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, aboutPanel, "About", JOptionPane.INFORMATION_MESSAGE, aboutIcon);
    }

    private void openFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            try {
                Desktop.getDesktop().open(folder);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening folder: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Folder does not exist: " + folderPath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
        if ("LoginPanel".equals(panelName)) {
            setJMenuBar(null); // Hide menu bar
            ((LoginPanel) mainPanel.getComponent(0)).resetFields();
        } else {
            setJMenuBar(menuBar); // Show menu bar
        }
    }

    public void showMainPanel() {
        setJMenuBar(menuBar);
        showPanel("MinorReportMainPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
