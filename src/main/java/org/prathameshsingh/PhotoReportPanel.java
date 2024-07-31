package org.prathameshsingh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PhotoReportPanel extends JPanel {
    private JTextField patientNameField;
    private JTextField ageField;
    private JTextField refferdBy;
    private JButton selectImagesButton;
    private JPanel imagePanel;
    private List<ImageIcon> selectedImages;
    JButton generateReportButton;
    JButton viewReportButton;

    String drName = "Dr. Xyz";

    Font labelFont = new Font("Roboto", Font.BOLD, 15);
    Font inputFont = new Font("Verdana", Font.PLAIN, 13);

    public PhotoReportPanel() {
        setLayout(null);


        JLabel patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setFont(labelFont);
        patientNameLabel.setBounds(20, 20, 150, 30);
        add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setFont(inputFont);
        patientNameField.setBounds(165, 20, 350, 30);
        add(patientNameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        ageLabel.setBounds(720, 20, 50, 30);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setFont(inputFont);
        ageField.setBounds(770, 20, 50, 30);
        ageField.setInputVerifier(new AgeInputVerifier()); // Set input verifier
        add(ageField);

        JLabel sexLabel = new JLabel("Sex:  Female");
        sexLabel.setFont(labelFont);
        sexLabel.setBounds(850, 20, 150, 30);
        add(sexLabel);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(labelFont);
        dateLabel.setBounds(1200, 20, 50, 30);
        add(dateLabel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        JLabel currentDateLabel = new JLabel(currentDate);
        currentDateLabel.setFont(labelFont);
        currentDateLabel.setBounds(1250, 20, 100, 30);
        add(currentDateLabel);

        JLabel doctorAndDateLabel = new JLabel("Doctor Name :    " + drName);
        doctorAndDateLabel.setFont(labelFont);
        doctorAndDateLabel.setBounds(20, 60, 450, 30);
        add(doctorAndDateLabel);

        JLabel refferedByLabel = new JLabel("Referred by:");
        refferedByLabel.setFont(labelFont);
        refferedByLabel.setBounds(720, 60, 150, 30);
        add(refferedByLabel);

        refferdBy = new JTextField("Self");
        refferdBy.setFont(inputFont);
        refferdBy.setBounds(870, 60, 250, 30);
        add(refferdBy);

        selectImagesButton = new JButton("Select Images");
        selectImagesButton.setFont(labelFont);
        selectImagesButton.setBounds(20, 110, 180, 40);
        add(selectImagesButton);

        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(2, 4, 5, 10));
        imagePanel.setBounds(20, 150, 1000, 450);
        add(imagePanel);

        selectedImages = new ArrayList<>();

        selectImagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImages();
            }
        });

        generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(labelFont);
        generateReportButton.setBounds(600, 630, 180, 50);
        generateReportButton.setVisible(false); // Initially hidden
        add(generateReportButton);

        // View Report Button
        viewReportButton = new JButton("View Report");
        viewReportButton.setFont(labelFont);
        viewReportButton.setBounds(800, 630, 180, 50);
        viewReportButton.setVisible(false); // Initially hidden
        add(viewReportButton);

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhotoReportMainPanel photoReportMainPanel = (PhotoReportMainPanel) SwingUtilities.getAncestorOfClass(PhotoReportMainPanel.class, PhotoReportPanel.this);
                if (photoReportMainPanel != null) {
                    photoReportMainPanel.generatePDF();
                }
            }
        });

        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhotoReportMainPanel photoReportMainPanel = (PhotoReportMainPanel) SwingUtilities.getAncestorOfClass(PhotoReportMainPanel.class, PhotoReportPanel.this);
                if (photoReportMainPanel != null) {
                    photoReportMainPanel.viewPDFReport();
                }
            }
        });
    }

    private void selectImages() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            if (files.length != 6 && files.length != 8) {
                JOptionPane.showMessageDialog(this, "Please select exactly 6 or 8 images.", "Invalid Number of Images", JOptionPane.ERROR_MESSAGE);
                return;
            }

            selectedImages.clear();
            for (File file : files) {
                selectedImages.add(new ImageIcon(file.getAbsolutePath()));
            }

            displaySelectedImages();
        }
    }

    private void displaySelectedImages() {
        imagePanel.removeAll();

        for (ImageIcon imageIcon : selectedImages) {
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH)));
            imagePanel.add(imageLabel);
        }

        generateReportButton.setVisible(true); // Make button visible after images are selected
        viewReportButton.setVisible(true);

        validate();
        repaint();
    }

    // Input verifier for age field
    private class AgeInputVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText().trim();

            if (text.matches("\\d+") && Integer.parseInt(text) <= 149 && Integer.parseInt(text) >= 18) {
                return true;
            } else {
                JOptionPane.showMessageDialog(PhotoReportPanel.this, "Please enter a valid age.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    /////////////////////////////////
    public String getPatientName() {
        return patientNameField.getText();
    }

    public String getAge() {
        return ageField.getText();
    }

    public String getSex() {
        return "Female";
    }

    public String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

    public String getDoctorName() {
        return drName;
    }

    public String getReferredBy() { return refferdBy.getText(); }

    public List<ImageIcon> getSelectedImages() {
        return new ArrayList<>(selectedImages);
    }

}
