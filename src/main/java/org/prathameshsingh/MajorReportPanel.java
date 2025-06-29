package org.prathameshsingh;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MajorReportPanel extends JPanel{
    private JTextField patientNameFieldM;
    private JTextField ageFieldM;
    private JButton generateRepButton;
    private JButton vieReportButton;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JTextField weeksField;
    private JTextField daysField;
    private JComboBox<String> eddDayComboBox;
    private JComboBox<String> eddMonthComboBox;
    private JComboBox<String> eddYearComboBox;
    private JComboBox<String> edd2DayComboBox;
    private JComboBox<String> edd2MonthComboBox;
    private JComboBox<String> edd2YearComboBox;
    private JTextField numOflives;
    private JTextField numOflivesCephalic;
    private JTextField placentaField;
    private JTextField afiField;
    private JTextField osField;
    private JTextField clotsField;
    private JTextField foetalCardicActivityField;
    private JTextField fsrField;
    private JTextField amnioticFluidField;
    private JTextField umbilicalCordField;
    private JTextArea additionalNoteField;
    private JTextField avgAgeWeeksField;
    private JTextField avgAgeDaysField;
    private JTextField foetalWeightField1;
    private JTextField foetalWeightField2;
    private JTextField impField;
    private JTextField rC00Field;
    private JTextField rC10Field;
    private JTextField rC20Field;
    private JTextField rC30Field;
    private JTextField rC01Field;
    private JTextField rC11Field;
    private JTextField rC21Field;
    private JTextField rC31Field;
    private JTextField rC02Field;
    private JTextField rC12Field;
    private JTextField rC22Field;
    private JTextField rC32Field;

    String drName = "Dr. Prathameshsingh Rajput";
    String sexPatient = "Female";

    Font labelFont = new Font("Roboto", Font.BOLD, 15);
    Font inputFont = new Font("Verdana", Font.PLAIN, 13);
    Font buttonFont = new Font("Roboto", Font.BOLD, 18);



    public MajorReportPanel() {
        setLayout(null);

        JLabel patientNameM = new JLabel("Patient Name:");
        patientNameM.setFont(labelFont);
        patientNameM.setBounds(20, 20, 150, 30);
        add(patientNameM);

        patientNameFieldM = new JTextField();
        patientNameFieldM.setFont(inputFont);
        patientNameFieldM.setBounds(165, 20, 350, 30);
        add(patientNameFieldM);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        ageLabel.setBounds(720, 20, 50, 30);
        add(ageLabel);

        ageFieldM = new JTextField();
        ageFieldM.setFont(inputFont);
        ageFieldM.setBounds(770, 20, 50, 30);
        ageFieldM.setInputVerifier(new MajorReportPanel.AgeInputVerifier()); // Set input verifier
        add(ageFieldM);

        JLabel sex = new JLabel("Sex: "+ sexPatient);
        sex.setFont(labelFont);
        sex.setBounds(850, 20, 150, 30);
        add(sex);

        JLabel currDate = new JLabel("Date:");
        currDate.setFont(labelFont);
        currDate.setBounds(1200, 20, 50, 30);
        add(currDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currDateString = sdf.format(new Date());
        JLabel currDateLabel = new JLabel(currDateString);
        currDateLabel.setFont(labelFont);
        currDateLabel.setBounds(1250, 20, 100, 30);
        add(currDateLabel);

        JLabel drNameLabel = new JLabel("Doctor Name :    " + drName);
        drNameLabel.setFont(labelFont);
        drNameLabel.setBounds(20, 60, 450, 30);
        add(drNameLabel);

        // Separator line
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setBounds(0, 100, 1600, 2);
        add(separator);

        //Four Lines Data
        numOflives = new JTextField("Single live intrauterine gestation is noted in ");
        numOflives.setFont(inputFont);
        numOflives.setBounds(20, 120, 307, 30);
        add(numOflives);

        numOflivesCephalic = new JTextField("Cephalic presentation.");
        numOflivesCephalic.setFont(inputFont);
        numOflivesCephalic.setBounds(326, 120, 200, 30);
        add(numOflivesCephalic);

        foetalCardicActivityField = new JTextField("Foetal cardiac activity & movements appeared normal.");
        foetalCardicActivityField.setFont(inputFont);
        foetalCardicActivityField.setBounds(600, 120, 400, 30);
        add(foetalCardicActivityField);

        osField = new JTextField("Internal Os is Closed.");
        osField.setFont(inputFont);
        osField.setBounds(1075, 120, 180, 30);
        add(osField);

        placentaField = new JTextField("Placenta is ANTERIOR with grade 3 maturity, no Previa.");
        placentaField.setFont(inputFont);
        placentaField.setBounds(20, 180, 400, 30);
        add(placentaField);

        clotsField = new JTextField("No obvious retro placental clots.");
        clotsField.setFont(inputFont);
        clotsField.setBounds(600, 180, 250, 30);
        add(clotsField);

        amnioticFluidField = new JTextField("Adequate amniotic fluid is noted.");
        amnioticFluidField.setFont(inputFont);
        amnioticFluidField.setBounds(1075, 180, 250, 30);
        add(amnioticFluidField);

        umbilicalCordField = new JTextField("Umbilical cord shows three vessels.");
        umbilicalCordField.setFont(inputFont);
        umbilicalCordField.setBounds(20, 240, 280, 30);
        add(umbilicalCordField);

        // AVG components
        JLabel avgAgeLabel = new JLabel("AVG AGE BY USG:");
        avgAgeLabel.setFont(labelFont);
        avgAgeLabel.setBounds(600, 240, 150, 30);
        add(avgAgeLabel);

        avgAgeWeeksField = new JTextField();
        avgAgeWeeksField.setFont(inputFont);
        avgAgeWeeksField.setBounds(750, 240, 30, 30);
        add(avgAgeWeeksField);

        JLabel avgAgeWLabel = new JLabel("WEEKS");
        avgAgeWLabel.setFont(labelFont);
        avgAgeWLabel.setBounds(785, 240, 60, 30);
        add(avgAgeWLabel);

        avgAgeDaysField = new JTextField();
        avgAgeDaysField.setFont(inputFont);
        avgAgeDaysField.setBounds(850, 240, 30, 30);
        add(avgAgeDaysField);

        JLabel avgAgedLabel = new JLabel("DAY");
        avgAgedLabel.setFont(labelFont);
        avgAgedLabel.setBounds(885, 240, 35, 30);
        add(avgAgedLabel);

        JLabel fsrLabel = new JLabel("FSR:");
        fsrLabel.setFont(labelFont);
        fsrLabel.setBounds(1075, 240, 50, 30);
        add(fsrLabel);

        fsrField = new JTextField("134 bpm");
        fsrField.setFont(inputFont);
        fsrField.setBounds(1120, 240, 65, 30);
        add(fsrField);


        JLabel afiLabel = new JLabel("AFI:");
        afiLabel.setFont(labelFont);
        afiLabel.setBounds(1230, 240, 40, 30);
        add(afiLabel);

        afiField = new JTextField("10 CM");
        afiField.setFont(inputFont);
        afiField.setBounds(1270, 240, 50, 30);
        add(afiField);

        // Fetal Biometry Component
        JLabel fetalBioLabel = new JLabel("Fetal Biometry:");
        fetalBioLabel.setFont(labelFont);
        fetalBioLabel.setBounds(20, 300, 130, 30);
        add(fetalBioLabel);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel rC00 = new JLabel("BPD");
        rC00.setFont(labelFont);
        rC00.setBounds(160, 330, 50, 40);
        add(rC00);

        JLabel rC10 = new JLabel("HC");
        rC10.setFont(labelFont);
        rC10.setBounds(160, 380, 50, 40);
        add(rC10);

        JLabel rC20 = new JLabel("AC");
        rC20.setFont(labelFont);
        rC20.setBounds(160, 430, 50, 40);
        add(rC20);

        JLabel rC30 = new JLabel("FL");
        rC30.setFont(labelFont);
        rC30.setBounds(160, 480, 50, 40);
        add(rC30);

        rC00Field = new JTextField("");
        rC00Field.setFont(inputFont);
        rC00Field.setBounds(230, 330, 60, 40);
        add(rC00Field);

        rC10Field = new JTextField("");
        rC10Field.setFont(inputFont);
        rC10Field.setBounds(230, 380, 60, 40);
        add(rC10Field);

        rC20Field = new JTextField("");
        rC20Field.setFont(inputFont);
        rC20Field.setBounds(230, 430, 60, 40);
        add(rC20Field);

        rC30Field = new JTextField("");
        rC30Field.setFont(inputFont);
        rC30Field.setBounds(230, 480, 60, 40);
        add(rC30Field);

        JLabel rC01 = new JLabel("CM");
        rC01.setFont(labelFont);
        rC01.setBounds(300, 330, 50, 40);
        add(rC01);

        JLabel rC11 = new JLabel("CM");
        rC11.setFont(labelFont);
        rC11.setBounds(300, 380, 50, 40);
        add(rC11);

        JLabel rC21 = new JLabel("CM");
        rC21.setFont(labelFont);
        rC21.setBounds(300, 430, 50, 40);
        add(rC21);

        JLabel rC31 = new JLabel("CM");
        rC31.setFont(labelFont);
        rC31.setBounds(300, 480, 50, 40);
        add(rC31);

        rC01Field = new JTextField("");
        rC01Field.setFont(inputFont);
        rC01Field.setBounds(380, 330, 60, 40);
        add(rC01Field);

        rC11Field = new JTextField("");
        rC11Field.setFont(inputFont);
        rC11Field.setBounds(380, 380, 60, 40);
        add(rC11Field);

        rC21Field = new JTextField("");
        rC21Field.setFont(inputFont);
        rC21Field.setBounds(380, 430, 60, 40);
        add(rC21Field);

        rC31Field = new JTextField("");
        rC31Field.setFont(inputFont);
        rC31Field.setBounds(380, 480, 60, 40);
        add(rC31Field);

        JLabel rC02 = new JLabel("WEEKS");
        rC02.setFont(labelFont);
        rC02.setBounds(450, 330, 60, 40);
        add(rC02);

        JLabel rC12 = new JLabel("WEEKS");
        rC12.setFont(labelFont);
        rC12.setBounds(450, 380, 60, 40);
        add(rC12);

        JLabel rC22 = new JLabel("WEEKS");
        rC22.setFont(labelFont);
        rC22.setBounds(450, 430, 60, 40);
        add(rC22);

        JLabel rC32 = new JLabel("WEEKS");
        rC32.setFont(labelFont);
        rC32.setBounds(450, 480, 60, 40);
        add(rC32);

        rC02Field = new JTextField("");
        rC02Field.setFont(inputFont);
        rC02Field.setBounds(560, 330, 60, 40);
        add(rC02Field);

        rC12Field = new JTextField("");
        rC12Field.setFont(inputFont);
        rC12Field.setBounds(560, 380, 60, 40);
        add(rC12Field);

        rC22Field = new JTextField("");
        rC22Field.setFont(inputFont);
        rC22Field.setBounds(560, 430, 60, 40);
        add(rC22Field);

        rC32Field = new JTextField("");
        rC32Field.setFont(inputFont);
        rC32Field.setBounds(560, 480, 60, 40);
        add(rC32Field);

        JLabel rC03 = new JLabel("DAY");
        rC03.setFont(labelFont);
        rC03.setBounds(630, 330, 50, 40);
        add(rC03);

        JLabel rC13 = new JLabel("DAY");
        rC13.setFont(labelFont);
        rC13.setBounds(630, 380, 50, 40);
        add(rC13);

        JLabel rC23 = new JLabel("DAY");
        rC23.setFont(labelFont);
        rC23.setBounds(630, 430, 50, 40);
        add(rC23);

        JLabel rC33 = new JLabel("DAY");
        rC33.setFont(labelFont);
        rC33.setBounds(630, 480, 50, 40);
        add(rC33);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // EDD Label and Date Input using JComboBox
        JLabel eddLabel = new JLabel("EDD 1:");
        eddLabel.setFont(labelFont);
        eddLabel.setBounds(850, 330, 60, 30);
        add(eddLabel);

        // Day selection
        eddDayComboBox = new JComboBox<>(getDayStrings());
        eddDayComboBox.setFont(inputFont);
        eddDayComboBox.setBounds(920, 330, 50, 30);
        add(eddDayComboBox);

        // Month selection
        eddMonthComboBox = new JComboBox<>(getMonthStrings());
        eddMonthComboBox.setFont(inputFont);
        eddMonthComboBox.setBounds(980, 330, 60, 30);
        add(eddMonthComboBox);

        // Year selection
        eddYearComboBox = new JComboBox<>(getYearStringsEdd());
        eddYearComboBox.setFont(inputFont);
        eddYearComboBox.setBounds(1050, 330, 70, 30);
        add(eddYearComboBox);

        // EDD2
        JLabel edd2Label = new JLabel("EDD 2:");
        edd2Label.setFont(labelFont);
        edd2Label.setBounds(850, 380, 60, 30);
        add(edd2Label);

        // Day selection
        edd2DayComboBox = new JComboBox<>(getDayStrings());
        edd2DayComboBox.setFont(inputFont);
        edd2DayComboBox.setBounds(920, 380  , 50, 30);
        add(edd2DayComboBox);

        // Month selection
        edd2MonthComboBox = new JComboBox<>(getMonthStrings());
        edd2MonthComboBox.setFont(inputFont);
        edd2MonthComboBox.setBounds(980, 380, 60, 30);
        add(edd2MonthComboBox);

        // Year selection
        edd2YearComboBox = new JComboBox<>(getYearStringsEdd());
        edd2YearComboBox.setFont(inputFont);
        edd2YearComboBox.setBounds(1050, 380, 70, 30);
        add(edd2YearComboBox);

        JLabel lmpLabel = new JLabel("LMP  :");
        lmpLabel.setFont(labelFont);
        lmpLabel.setBounds(850, 430, 60, 30);
        add(lmpLabel);

        // Day selection
        dayComboBox = new JComboBox<>(getDayStrings());
        dayComboBox.setFont(inputFont);
        dayComboBox.setBounds(920, 430, 50, 30);
        add(dayComboBox);

        // Month selection
        monthComboBox = new JComboBox<>(getMonthStrings());
        monthComboBox.setFont(inputFont);
        monthComboBox.setBounds(980, 430, 60, 30);
        add(monthComboBox);

        // Year selection
        yearComboBox = new JComboBox<>(getYearStrings());
        yearComboBox.setFont(inputFont);
        yearComboBox.setBounds(1050, 430, 70, 30);
        add(yearComboBox);

        JLabel imp = new JLabel("IMP - ");
        imp.setFont(labelFont);
        imp.setBounds(20, 560, 60, 30);
        add(imp);

        impField = new JTextField("SINGLE LIVE INTRAUTERINE PREGNANCY OF 37 WEEKS 03 DAYS.");
        impField.setFont(inputFont);
        impField.setBounds(80, 560, 470, 30);
        add(impField);


        JLabel foetalWeightLabel = new JLabel("Estimated Foetal Weight:");
        foetalWeightLabel.setFont(labelFont);
        foetalWeightLabel.setBounds(850, 560, 190, 30);
        add(foetalWeightLabel);

        foetalWeightField1 = new JTextField();
        foetalWeightField1.setFont(inputFont);
        foetalWeightField1.setBounds(1045, 560, 50, 30);
        add(foetalWeightField1);

        JLabel foetalWtGMLabel1 = new JLabel("GM +/- ");
        foetalWtGMLabel1.setFont(labelFont);
        foetalWtGMLabel1.setBounds(1095, 560, 60, 30);
        add(foetalWtGMLabel1);

        foetalWeightField2 = new JTextField();
        foetalWeightField2.setFont(inputFont);
        foetalWeightField2.setBounds(1155, 560, 60, 30);
        add(foetalWeightField2);

        JLabel foetalWtGMLabel2 = new JLabel("GM");
        foetalWtGMLabel2.setFont(labelFont);
        foetalWtGMLabel2.setBounds(1215, 560, 30, 30);
        add(foetalWtGMLabel2);

        // Textarea for additional notes and its label
        JLabel additionalInfoLabel = new JLabel("Additional Note:");
        additionalInfoLabel.setFont(labelFont);
        additionalInfoLabel.setBounds(20, 630, 160, 30);
        add(additionalInfoLabel);

        additionalNoteField = new JTextArea("");
        additionalNoteField.setFont(inputFont);
        additionalNoteField.setBounds(170, 630, 450, 50);
        additionalNoteField.setLineWrap(true); // Enable line wrapping
        additionalNoteField.setWrapStyleWord(true); // Wrap word boundaries

        // Line border with a specified color and thickness
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
        additionalNoteField.setBorder(border);

        // JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(additionalNoteField);
        scrollPane.setBounds(170, 630, 450, 50);
        add(scrollPane);

        generateRepButton = new JButton("Generate Report");
        generateRepButton.setFont(buttonFont);
        generateRepButton.setBounds(700, 635, 180, 50);
        generateRepButton.setBorderPainted(false);
        add(generateRepButton);

        vieReportButton = new JButton("View Report");
        vieReportButton.setFont(buttonFont);
        vieReportButton.setBounds(900, 635, 180, 50);
        vieReportButton.setBorderPainted(false);
        add(vieReportButton);


        generateRepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MajorReportMainPanel majorReportMainPanel = (MajorReportMainPanel) SwingUtilities.getAncestorOfClass(MajorReportMainPanel.class, MajorReportPanel.this);
                if (majorReportMainPanel != null) {
                    majorReportMainPanel.generateMajorReport();
                    majorReportMainPanel.saveMajorReportData();
                }
            }
        });

        vieReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MajorReportMainPanel majorReportMainPanel = (MajorReportMainPanel) SwingUtilities.getAncestorOfClass(MajorReportMainPanel.class, MajorReportPanel.this);
                if (majorReportMainPanel != null) {
                    majorReportMainPanel.viewMajorReportPDF();
                }
            }
        });

    }

    private class AgeInputVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText().trim();

            if (text.matches("\\d+") && Integer.parseInt(text) <= 60 && Integer.parseInt(text) >= 18) {
                return true;
            } else {
                JOptionPane.showMessageDialog(MajorReportPanel.this, "Please enter a valid age.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    private String[] getDayStrings() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    private String[] getMonthStrings() {
        String[] months = new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        return months;
    }

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


    public String getPatientNameM() {
        return patientNameFieldM.getText().toUpperCase();
    }

    public String getAgeM() {
        return ageFieldM.getText();
    }

    public String getSexPatientM(){
        return "F";
    }

    public String getCurrDateM(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }

    public String getDrNameM(){
        return drName;
    }

    public String getLivesData(){
        return numOflives.getText();
    }

    public String getLiveDataBold(){
        return numOflivesCephalic.getText();
    }

    public String getFoetalCardiacData(){
        return foetalCardicActivityField.getText();
    }

    public String getPlacentaGradeData(){
        return placentaField.getText();
    }

    public String getFSR(){
        return "FSR - "+ fsrField.getText();
    }

    public String getPlacentalClotsData(){
        return clotsField.getText();
    }

    public String getAFI(){
        return "AFI - " + afiField.getText();
    }

    public String getOsData(){
        return osField.getText();
    }

    public String getAmnioticFluidData(){
        return amnioticFluidField.getText();
    }

    public String getUmbilicalCordData(){
        return umbilicalCordField.getText();
    }

    public String getLMP() {
        String day = (String) dayComboBox.getSelectedItem();
        if(Integer.parseInt(day) < 10){
            day = "0"+day;
        }
        String month = (String) monthComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();
        return day + "/" + month + "/" + year;
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getRC00(){
        return rC00Field.getText();
    }
    public String getRC01(){
        return rC01Field.getText();
    }
    public String getRC02(){
        return rC02Field.getText();
    }

    public String getRC10(){
        return rC10Field.getText();
    }
    public String getRC11(){
        return rC11Field.getText();
    }
    public String getRC12(){
        return rC12Field.getText();
    }

    public String getRC20(){
        return rC20Field.getText();
    }
    public String getRC21(){
        return rC21Field.getText();
    }
    public String getRC22(){
        return rC22Field.getText();
    }

    public String getRC30(){
        return rC30Field.getText();
    }
    public String getRC31(){
        return rC31Field.getText();
    }
    public String getRC32(){
        return rC32Field.getText();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getAvgAge() {
        String weekAvg = avgAgeWeeksField.getText();
        String dayAvg = avgAgeDaysField.getText();
        return weekAvg + " WEEKS  " + dayAvg + " DAY";
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

    public String getFoetalWeight() {
        String weight1 = foetalWeightField1.getText();
        String weight2 = foetalWeightField2.getText();
        return weight1 + " +/- " + weight2;
    }

    public String getImp(){
        return impField.getText();
    }

    public String getAddiNote(){
        return additionalNoteField.getText().trim();
    }
}
