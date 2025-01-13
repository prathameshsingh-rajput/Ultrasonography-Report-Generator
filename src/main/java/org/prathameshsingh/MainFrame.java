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
        setTitle("Ultrasonography Report Generator");
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

        // Set the content pane to the main panel with CardLayout
        setContentPane(mainPanel);

        // Custom frame icon
        ImageIcon icon = new ImageIcon("C:/Users/hp22r/Desktop/Hospital Project/PsrSoftware/src/main/resources/myAppIcon.png"); // Replace with the path to your icon file
        setIconImage(icon.getImage());

        // Create menu bar
        menuBar = createMenuBar();
        setJMenuBar(null); // Initially do not show menu bar
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new java.awt.Color(248, 253, 255));
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 2, 1, 5));

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Roboto", Font.BOLD, 17));
        menu.setForeground(new java.awt.Color(0, 0, 139));

        JMenu view = new JMenu("View");
        view.setFont(new Font("Roboto", Font.BOLD, 17));
        view.setForeground(new java.awt.Color(0, 0, 139));

        JMenu report = new JMenu("Report");
        report.setFont(new Font("Roboto", Font.BOLD, 17));
        report.setForeground(new java.awt.Color(0, 0, 139));

        JMenu about = new JMenu("About");
        about.setFont(new Font("Roboto", Font.BOLD, 17));
        about.setForeground(new java.awt.Color(0, 0, 139));

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

        about.add(createAboutMenuItem()); // Add action listener to About menu

        menuBar.add(menu);
        menuBar.add(report);
        menuBar.add(view);
        menuBar.add(about);

        return menuBar;
    }

    private JMenuItem createMenuItem(String title, String panelName, int mnemonic, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Roboto", Font.PLAIN, 16));
        menuItem.setForeground(new java.awt.Color(0, 0, 139));
        menuItem.setBackground(Color.WHITE);
        menuItem.setOpaque(true);
        menuItem.setPreferredSize(new Dimension(150, 40));
        menuItem.setMnemonic(mnemonic);
        menuItem.setAccelerator(accelerator);
        menuItem.addActionListener(e -> showPanel(panelName));
        return menuItem;
    }

    private JMenuItem createFolderMenuItem(String title, String folderPath) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Roboto", Font.PLAIN, 16));
        menuItem.setForeground(new java.awt.Color(0, 0, 139));
        menuItem.setBackground(Color.WHITE);
        menuItem.setOpaque(true);
        menuItem.setPreferredSize(new Dimension(170, 40));
        menuItem.addActionListener(e -> openFolder(folderPath));
        return menuItem;
    }

    private JMenuItem createAboutMenuItem() {
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setFont(new Font("Roboto", Font.PLAIN, 16));
        aboutItem.setForeground(new java.awt.Color(0, 0, 139));
        aboutItem.setBackground(Color.WHITE);
        aboutItem.setOpaque(true);
        aboutItem.setPreferredSize(new Dimension(150, 40));
        aboutItem.addActionListener(e -> showAboutDialog());
        return aboutItem;
    }

    private void showAboutDialog() {
        // Create a panel for the JOptionPane
        JPanel aboutPanel = new JPanel(new BorderLayout(5, 5));
        aboutPanel.setBackground(Color.WHITE);

        // User information with clickable link
        JEditorPane infoPane = new JEditorPane("text/html",
                "<html><br><center>Ultrasonography Report Generator 2024.8.9 (Ultimate Edition) <br>" +
                        "Build #IU-746.24082003.05, built on August 14, 2024 <br>" +
                        "Software Version: 2.4<br><br> " +
                        "Subscription is activated until August 14, 2025.<br><br> " +
                        "Powered by <a href=\"https://linktr.ee/rajput_prathameshsingh?utm_source=linktree_profile_share&ltsid=4a7912cf-bdab-48ea-ba66-7930442a7fdf\">Connect Developer</a><br>" +
                        "Copyright ©2024-2025 PSR-SoftHub</center></html>");
        infoPane.setEditable(false);
        infoPane.setFont(new Font("Roboto", Font.BOLD, 15));
        infoPane.setBackground(Color.WHITE);

        // Add a hyperlink listener to handle clicks
        infoPane.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Icon
        ImageIcon aboutIcon = new ImageIcon("C:/Users/hp22r/Desktop/Hospital Project/PsrSoftware/src/main/resources/check.png"); // Replace with the path to your icon file
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
            ((LoginPanel) mainPanel.getComponent(0)).resetFields(); // Reset fields in login panel
        } else {
            setJMenuBar(menuBar); // Show menu bar for other panels
        }
    }


    public void showMainPanel() {
        setJMenuBar(menuBar); // Show menu bar when main panel is shown
        showPanel("MinorReportMainPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
