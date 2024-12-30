package org.prathameshsingh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private MainFrame mainFrame;
    private Image backgroundImage;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        // Load custom fonts
        Font robotoBold = new Font("Roboto", Font.BOLD, 24);
        Font robotoPlain = new Font("Roboto", Font.BOLD, 20);
        Font inputFont = new Font("Verdana", Font.PLAIN, 20);

        // Load background image
        try {
            BufferedImage img = ImageIO.read(new File("C:/Users/hp22r/Desktop/Hospital Project/PsrSoftware/src/main/resources/login_Page_bg.jpg"));
            backgroundImage = img;
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel titleLabel = new JLabel("Login to PSR-SoftHub");
        titleLabel.setFont(robotoBold);
        titleLabel.setForeground(new Color(0, 0, 139));
        titleLabel.setBounds(105, 190, 300, 50);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(robotoPlain);
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setBounds(100, 250, 120, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(inputFont);
        usernameField.setBounds(210, 250, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(robotoPlain);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(100, 300, 120, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(inputFont);
        passwordField.setBounds(210, 300, 150, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(robotoBold);
        loginButton.setBounds(210, 350, 150, 40);
        loginButton.setBorderPainted(false);
        add(loginButton);

        messageLabel = new JLabel("");
        messageLabel.setFont(robotoPlain);
        messageLabel.setForeground(Color.RED);
        messageLabel.setBounds(100, 350, 300, 30);
        add(messageLabel);

        JLabel copyrightLabel = new JLabel("© 2025 PSR-SoftHub [Rajput Prathameshsingh]. All rights reserved.", SwingConstants.CENTER);
        copyrightLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
        copyrightLabel.setForeground(Color.BLACK);
        copyrightLabel.setBounds(80, 500, 400, 30);
        add(copyrightLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("navoday")) {  // Replace with your actual credentials checking logic
                    mainFrame.showMainPanel();
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid Username or password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image to cover the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }
}
