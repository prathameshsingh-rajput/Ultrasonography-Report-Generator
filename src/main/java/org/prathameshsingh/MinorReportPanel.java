package org.prathameshsingh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class MinorReportPanel extends JPanel {
    public JTextField patientNameField;
    private JTextField ageField;
    private JButton generateReportButton;
    private JButton viewButton;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JTextField weeksField;
    private JTextField daysField;
    private JTextField agaWeeksField;
    private JTextField agaDaysField;
    private JComboBox<String> eddDayComboBox;
    private JComboBox<String> eddMonthComboBox;
    private JComboBox<String> eddYearComboBox;
    private JComboBox<String> edd2DayComboBox;
    private JComboBox<String> edd2MonthComboBox;
    private JComboBox<String> edd2YearComboBox;
    private JTextField liverFetusNote;
    private JTextField liverFetusNumField;
    private JTextField foetalAndSacField;
    private JTextField osField;
    private JTextField osNumField;
    private JTextField fetalCardiacField;
    private JTextField gsNumField;
    private JTextField gsWeeksField;
    private JTextField gsDaysField;
    private JTextField crlNumField;
    private JTextField crlWeeksField;
    private JTextField crlDaysField;
    private JComboBox<String> amnioticFComboBox;
    private JTextField impressAndCommField;
    private JTextArea additionalNoteField;

    private File selectedImageFile;
    String drName = "Dr. Mayuri Dhoran";

    public MinorReportPanel() {
        setLayout(null); // To Use absolute positioning

        Font labelFont = new Font("Roboto", Font.BOLD, 15);
        Font inputFont = new Font("Verdana", Font.PLAIN, 13);
        Font robotoBold = new Font("Roboto", Font.BOLD, 18);


        JLabel patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setFont(labelFont);
        patientNameLabel.setBounds(20, 20, 150, 30);
        add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setFont(inputFont);
        patientNameField.setBounds(165, 20, 350, 30);
        patientNameField.setBorder(null);
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

        // Separator line
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setBounds(0, 100, 1600, 1);
        add(separator);

        JLabel lmpLabel = new JLabel("LMP:");
        lmpLabel.setFont(labelFont);
        lmpLabel.setBounds(20, 120, 50, 30);
        add(lmpLabel);

        // Day selection
        dayComboBox = new JComboBox<>(getDayStrings());
        dayComboBox.setFont(inputFont);
        dayComboBox.setBounds(80, 120, 50, 30);
        add(dayComboBox);

        // Month selection
        monthComboBox = new JComboBox<>(getMonthStrings());
        monthComboBox.setFont(inputFont);
        monthComboBox.setBounds(140, 120, 100, 30);
        add(monthComboBox);

        // Year selection
        yearComboBox = new JComboBox<>(getYearStrings());
        yearComboBox.setFont(inputFont);
        yearComboBox.setBounds(250, 120, 70, 30);
        add(yearComboBox);

        // GA components
        JLabel gaLabel = new JLabel("GA:");
        gaLabel.setFont(labelFont);
        gaLabel.setBounds(390, 120, 40, 30);
        add(gaLabel);

        weeksField = new JTextField();
        weeksField.setFont(inputFont);
        weeksField.setBounds(430, 120, 30, 30);
        add(weeksField);

        JLabel wLabel = new JLabel("W");
        wLabel.setFont(labelFont);
        wLabel.setBounds(460, 120, 20, 30);
        add(wLabel);

        daysField = new JTextField();
        daysField.setFont(inputFont);
        daysField.setBounds(480, 120, 30, 30);
        add(daysField);

        JLabel dLabel = new JLabel("D");
        dLabel.setFont(labelFont);
        dLabel.setBounds(510, 120, 20, 30);
        add(dLabel);

        // AGA components
        JLabel agaLabel = new JLabel("AGA:");
        agaLabel.setFont(labelFont);
        agaLabel.setBounds(580, 120, 60, 30);
        add(agaLabel);

        agaWeeksField = new JTextField();
        agaWeeksField.setFont(inputFont);
        agaWeeksField.setBounds(630, 120, 30, 30);
        add(agaWeeksField);

        JLabel agawLabel = new JLabel("W");
        agawLabel.setFont(labelFont);
        agawLabel.setBounds(660, 120, 20, 30);
        add(agawLabel);

        agaDaysField = new JTextField();
        agaDaysField.setFont(inputFont);
        agaDaysField.setBounds(680, 120, 30, 30);
        add(agaDaysField);

        JLabel agadLabel = new JLabel("D");
        agadLabel.setFont(labelFont);
        agadLabel.setBounds(710, 120, 20, 30);
        add(agadLabel);

        // EDD Label and Date Input using JComboBox
        JLabel eddLabel = new JLabel("EDD:");
        eddLabel.setFont(labelFont);
        eddLabel.setBounds(800, 120, 50, 30);
        add(eddLabel);

        // Day selection
        eddDayComboBox = new JComboBox<>(getDayStrings());
        eddDayComboBox.setFont(inputFont);
        eddDayComboBox.setBounds(860, 120, 50, 30);
        add(eddDayComboBox);

        // Month selection
        eddMonthComboBox = new JComboBox<>(getMonthStrings());
        eddMonthComboBox.setFont(inputFont);
        eddMonthComboBox.setBounds(920, 120, 100, 30);
        add(eddMonthComboBox);

        // Year selection
        eddYearComboBox = new JComboBox<>(getYearStringsEdd());
        eddYearComboBox.setFont(inputFont);
        eddYearComboBox.setBounds(1030, 120, 70, 30);
        add(eddYearComboBox);

        //Four Lines Data
        liverFetusNote = new JTextField("Single live intrauterine fetus noted.");
        liverFetusNote.setFont(inputFont);
        liverFetusNote.setBounds(20, 170, 307, 30);
        add(liverFetusNote);

        liverFetusNumField = new JTextField("FHS-178/MIN");
        liverFetusNumField.setFont(inputFont);
        liverFetusNumField.setBounds(327, 170, 130, 30);
        add(liverFetusNumField);

        foetalAndSacField = new JTextField("Foetal pole and yolk sac are seen well.");
        foetalAndSacField.setFont(inputFont);
        foetalAndSacField.setBounds(550, 170, 350, 30);
        add(foetalAndSacField);

        osField = new JTextField("Internal Os is Closed.");
        osField.setFont(inputFont);
        osField.setBounds(990, 170, 180, 30);
        add(osField);

        osNumField = new JTextField("Cx = 3.3 cm");
        osNumField.setFont(inputFont);
        osNumField.setBounds(1170, 170, 130, 30);
        add(osNumField);

        fetalCardiacField = new JTextField("Foetal cardiac activity & movements appeared normal.");
        fetalCardiacField.setFont(inputFont);
        fetalCardiacField.setBounds(20, 220, 480, 30);
        add(fetalCardiacField);

        // EDD2 Label and Date Input using JComboBox
        JLabel edd2Label = new JLabel("EDD:");
        edd2Label.setFont(labelFont);
        edd2Label.setBounds(550, 220, 50, 30);
        add(edd2Label);

        // Day selection
        edd2DayComboBox = new JComboBox<>(getDayStrings());
        edd2DayComboBox.setFont(inputFont);
        edd2DayComboBox.setBounds(610, 220  , 50, 30);
        add(edd2DayComboBox);

        // Month selection
        edd2MonthComboBox = new JComboBox<>(getMonthStrings());
        edd2MonthComboBox.setFont(inputFont);
        edd2MonthComboBox.setBounds(670, 220, 100, 30);
        add(edd2MonthComboBox);

        // Year selection
        edd2YearComboBox = new JComboBox<>(getYearStringsEdd());
        edd2YearComboBox.setFont(inputFont);
        edd2YearComboBox.setBounds(780, 220, 70, 30);
        add(edd2YearComboBox);

        // Fetal Biometry Component
        JLabel fetalBioLabel = new JLabel("Fetal Biometry:");
        fetalBioLabel.setFont(labelFont);
        fetalBioLabel.setBounds(20, 280, 150, 30);
        add(fetalBioLabel);

        JLabel GSLabel = new JLabel("GS:");
        GSLabel.setFont(labelFont);
        GSLabel.setBounds(250, 310, 50, 30);
        add(GSLabel);

        gsNumField = new JTextField();
        gsNumField.setFont(inputFont);
        gsNumField.setBounds(300, 310, 60, 30);
        add(gsNumField);

        JLabel gsNumFieldLabel = new JLabel("mm");
        gsNumFieldLabel.setFont(labelFont);
        gsNumFieldLabel.setBounds(370, 310, 60, 30);
        add(gsNumFieldLabel);

        gsWeeksField = new JTextField();
        gsWeeksField.setFont(inputFont);
        gsWeeksField.setBounds(300, 370, 30, 30);
        add(gsWeeksField);

        JLabel gswLabel = new JLabel("W");
        gswLabel.setFont(labelFont);
        gswLabel.setBounds(330, 370, 20, 30);
        add(gswLabel);

        gsDaysField = new JTextField();
        gsDaysField.setFont(inputFont);
        gsDaysField.setBounds(350, 370, 30, 30);
        add(gsDaysField);

        JLabel gsdLabel = new JLabel("D");
        gsdLabel.setFont(labelFont);
        gsdLabel.setBounds(380, 370, 20, 30);
        add(gsdLabel);

        JLabel CRLLabel = new JLabel("CRL:");
        CRLLabel.setFont(labelFont);
        CRLLabel.setBounds(650, 310, 50, 30);
        add(CRLLabel);

        crlNumField = new JTextField();
        crlNumField.setFont(inputFont);
        crlNumField.setBounds(700, 310, 60, 30);
        add(crlNumField);

        JLabel crlNumFieldLabel = new JLabel("mm");
        crlNumFieldLabel.setFont(labelFont);
        crlNumFieldLabel.setBounds(770, 310, 60, 30);
        add(crlNumFieldLabel);

        crlWeeksField = new JTextField();
        crlWeeksField.setFont(inputFont);
        crlWeeksField.setBounds(700, 370, 30, 30);
        add(crlWeeksField);

        JLabel crlWLabel = new JLabel("W");
        crlWLabel.setFont(labelFont);
        crlWLabel.setBounds(730, 370, 20, 30);
        add(crlWLabel);

        crlDaysField = new JTextField();
        crlDaysField.setFont(inputFont);
        crlDaysField.setBounds(750, 370, 30, 30);
        add(crlDaysField);

        JLabel crlDLabel = new JLabel("D");
        crlDLabel.setFont(labelFont);
        crlDLabel.setBounds(780, 370, 20, 30);
        add(crlDLabel);

        // Amniotic Fluid Label and selection
        JLabel amnioticFLabel = new JLabel("Amniotic Fluid:");
        amnioticFLabel.setFont(labelFont);
        amnioticFLabel.setBounds(1020, 280, 170, 30);
        add(amnioticFLabel);

        amnioticFComboBox = new JComboBox<>(getAmnioticFluidStrings());
        amnioticFComboBox.setFont(inputFont);
        amnioticFComboBox.setBounds(1170, 280, 170, 30);
        add(amnioticFComboBox);

        // Impression and Comments
        JLabel impressAndComm = new JLabel("Impression and Comments:");
        impressAndComm.setFont(labelFont);
        impressAndComm.setBounds(20, 450, 250, 30);
        add(impressAndComm);

        impressAndCommField = new JTextField("SINGLE LIVE INTRAUTERINE FETUS OF 08 WKS 05 DAYS.");
        impressAndCommField.setFont(inputFont);
        impressAndCommField.setBounds(270, 450, 520, 30);
        add(impressAndCommField);

        // Textarea for additional notes and its label
        JLabel additionalInfoLabel = new JLabel("Additional Note:");
        additionalInfoLabel.setFont(labelFont);
        additionalInfoLabel.setBounds(20, 520, 250, 30);
        add(additionalInfoLabel);

        additionalNoteField = new JTextArea("");
        additionalNoteField.setFont(inputFont);
        additionalNoteField.setBounds(270, 520, 520, 80);
        additionalNoteField.setLineWrap(true); // Enable line wrapping
        additionalNoteField.setWrapStyleWord(true); // Wrap word boundaries

        // Line border with a specified color and thickness
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
        additionalNoteField.setBorder(border);

        // JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(additionalNoteField);
        scrollPane.setBounds(270, 520, 520, 80);
        add(scrollPane);

        // Generate Report Button
        generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(robotoBold);
        generateReportButton.setBounds(600, 630, 180, 50);
        generateReportButton.setBorderPainted(false);
        add(generateReportButton);

        // View Report Button
        viewButton = new JButton("View Report");
        viewButton.setFont(robotoBold);
        viewButton.setBounds(800, 630, 180, 50);
        viewButton.setBorderPainted(false);
        add(viewButton);


        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MinorReportMainPanel minorReportMainPanel = (MinorReportMainPanel) SwingUtilities.getAncestorOfClass(MinorReportMainPanel.class, MinorReportPanel.this);
                if (minorReportMainPanel != null) {
                    minorReportMainPanel.generatePDF();
                    minorReportMainPanel.saveMinorReportData();
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MinorReportMainPanel minorReportMainPanel = (MinorReportMainPanel) SwingUtilities.getAncestorOfClass(MinorReportMainPanel.class, MinorReportPanel.this);
                if (minorReportMainPanel != null) {
                    minorReportMainPanel.viewPDFReport();
                }
            }
        });
    }

    public String getPatientName() {
        return patientNameField.getText().toUpperCase();
    }

    public String getAge() {
        return ageField.getText();
    }

    public String getSex() {
        return "Female";
    }

    public String getDoctorName() {
        return drName;
    }

    public String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

    // Method to get the concatenated LMP string
    public String getLMP() {
        String day = (String) dayComboBox.getSelectedItem();
        if(Integer.parseInt(day) < 10){
            day = "0"+day;
        }
        String month = (String) monthComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();
        return day + "/" + month + "/" + year;
    }

    public String getGA() {
        String weekGA = weeksField.getText();
        String dayGA = daysField.getText();
        return weekGA + "W " + dayGA + "D";
    }

    public String getAGA() {
        String weekAGA = agaWeeksField.getText();
        String dayAGA = agaDaysField.getText();
        return weekAGA + "W " + dayAGA + "D";
    }

    public String getEdd1(){
        String day = (String) eddDayComboBox.getSelectedItem();
        if(Integer.parseInt(day) < 10){
            day = "0"+day;
        }
        String month = (String) eddMonthComboBox.getSelectedItem();
        String year = (String) eddYearComboBox.getSelectedItem();
        return day + "/" + month + "/" + year;
    }

    public String getEdd2(){
        String day = (String) edd2DayComboBox.getSelectedItem();
        if(Integer.parseInt(day) < 10){
            day = "0"+day;
        }
        String month = (String) edd2MonthComboBox.getSelectedItem();
        String year = (String) edd2YearComboBox.getSelectedItem();
        return day + "/" + month + "/" + year;
    }

    public String getLiverFetusNote(){
        return liverFetusNote.getText();
    }

    public String getLiverFetusNumNote(){
        return liverFetusNumField.getText();
    }

    public String getFoetalAndSacField() {
        return foetalAndSacField.getText();
    }

    public String getOs(){
        return osField.getText() +" "+ osNumField.getText();
    }

    public String getFetalCardiac(){
        return fetalCardiacField.getText();
    }

    public String getFBGSmm(){

        return gsNumField.getText() + " mm";
    }
    public String getFBGS(){
        return gsWeeksField.getText() + "W " + gsDaysField.getText() + "D";
    }

    public String getCRLmm(){
        return crlNumField.getText() + " mm";
    }

    public String getCRL(){
        return crlWeeksField.getText() + "W " + crlDaysField.getText() + "D";
    }

    public String getAmnioticFluid(){
        String amnioticF = (String) amnioticFComboBox.getSelectedItem();
        return amnioticF;

    }

    public String getImpressAndComm(){
        return impressAndCommField.getText();
    }

    public String getAddiNote(){
        return additionalNoteField.getText().trim();
    }

    // Input verifier for age field
    private class AgeInputVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText().trim();

            if (text.matches("\\d+") && Integer.parseInt(text) <= 60 && Integer.parseInt(text) >= 18) {
                return true;
            } else {
                JOptionPane.showMessageDialog(MinorReportPanel.this, "Please enter a valid age.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    // Utility method to get day strings
    private String[] getDayStrings() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    // Utility method to get month strings
    private String[] getMonthStrings() {
        String[] months = new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months;
    }

    // Utility method to get year strings
    private String[] getYearStrings() {
        String[] years = new String[3];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 3; i++) {
            years[i] = String.valueOf(currentYear - 2 + i);
        }
        return years;
    }

    private String[] getYearStringsEdd() {
        String[] years = new String[2];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 2; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        return years;
    }

    // Utility method to get Amniotic Fluid strings
    private String[] getAmnioticFluidStrings() {
        String[] fluid = new String[]{
                "Normal", "Oligohydramnios", "Polyhydramnios"
        };
        return fluid;
    }

}
