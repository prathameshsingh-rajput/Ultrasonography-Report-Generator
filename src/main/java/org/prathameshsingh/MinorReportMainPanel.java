package org.prathameshsingh;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.io.IOUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;


public class MinorReportMainPanel extends JPanel {
    private MinorReportPanel minorReportPanel;
    private String fileName;
    private File selectedImageFile;

    // Output PDF Global Font
    Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
    Font normalForNote = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL);
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    Font boldFontData = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    Font boldPoint = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
    Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);

    public MinorReportMainPanel() {
        setLayout(new BorderLayout());

        minorReportPanel = new MinorReportPanel();
        JScrollPane inputScrollPane = new JScrollPane(minorReportPanel);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(inputScrollPane, BorderLayout.CENTER);
    }

    public void generatePDF() {
        String patientName = minorReportPanel.getPatientName();
        String patientAge = minorReportPanel.getAge();

        // Validate user input
        if (patientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a patient name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (patientAge.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a patient age.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate file name
        fileName = patientName.replaceAll("[^a-zA-Z0-9]", "") + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";

        // Define the path to the 'USG Report/Minor Report' folder on the D drive
        Path usReportFolderPath = Path.of("D:", "USG Reports", "Minor Report");

        Document document = null;
        try {
            // Check if USReport folder exists, if not create it
            if (!Files.exists(usReportFolderPath)) {
                Files.createDirectories(usReportFolderPath);
            }

            // Define the full path to save the PDF
            File pdfFile = usReportFolderPath.resolve(fileName).toFile();

            document = new Document(PageSize.A4, 36, 36, 5, 5); // Reduce top margin to shift the image upwards
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // Add Image at the top
            try {
                InputStream imageStream = getClass().getClassLoader().getResourceAsStream("TopOfReportImg2.png");
                if (imageStream != null) {
                    Image topImage = Image.getInstance(IOUtils.toByteArray(imageStream));
                    topImage.scaleToFit(PageSize.A4.getWidth() - 50, PageSize.A4.getHeight() / 4); // scale to fit the top portion
                    topImage.setAlignment(Element.ALIGN_CENTER);
                    document.add(topImage);
                } else {
                    System.err.println("Top image not found in the resources folder.");
                }
            } catch (Exception e) {
                System.err.println("Top image failed to load: " + e.getMessage());
            }

            document.add(new Paragraph("\n"));

            // Add Patient and Doctor Information
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(5f);
            table.setSpacingAfter(0f);

            addTableCell(table, "Name  : " + minorReportPanel.getPatientName(), boldFont);
            addTableCell(table, "                                  Age: " + minorReportPanel.getAge() + "   Sex: " + minorReportPanel.getSex(), boldFont);
            addTableCell(table, "Ref By: " + minorReportPanel.getDoctorName(), boldFont);
            addTableCell(table, "                                  Date: " + minorReportPanel.getCurrentDate(), boldFont);
            document.add(table);

            document.add(new Paragraph("\n"));

            // Add Information [LMP, GA, EDD, AGA]
            PdfPTable table2 = new PdfPTable(3);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(5f);
            table2.setSpacingAfter(2f);

            addTableCellWithMixedFonts(table2, "LMP: ", minorReportPanel.getLMP(), boldPoint, normalFont);
            addTableCellWithMixedFonts(table2, "GA  : ", minorReportPanel.getGA(), boldPoint, normalFont);
            addTableCellWithMixedFonts(table2, "EDD: ", minorReportPanel.getEdd1(), boldPoint, normalFont);
            addBlankTableCell(table2);
            addTableCellWithMixedFonts(table2, "AGA: ", minorReportPanel.getAGA(), boldPoint, normalFont);
            addTableCellWithMixedFonts(table2, "EDD: ", minorReportPanel.getEdd2(), boldPoint, normalFont);
            document.add(table2);

            document.add(new Paragraph("\n"));

            // Add Information
            PdfPTable table3 = new PdfPTable(1);
            table3.setWidthPercentage(100);
            table3.setSpacingBefore(0f);
            table3.setSpacingAfter(0f);

            // Add Info 4 lines group
            addTableCellWithMixedFonts(table3, minorReportPanel.getLiverFetusNote(), minorReportPanel.getLiverFetusNumNote(), normalFont, boldFontData);
            addTableCellWithText(table3, minorReportPanel.getFoetalAndSacField(), normalFont);
            addTableCellWithText(table3, minorReportPanel.getOs(), normalFont);
            addTableCellWithText(table3, minorReportPanel.getFetalCardiac(), normalFont);
            document.add(table3);

            document.add(new Paragraph("\n"));

            // Add Information [Amniotic Fluid, Impression & Comments, Additional Notes]
            PdfPTable table4 = new PdfPTable(1);
            table4.setWidthPercentage(100);
            table4.setSpacingBefore(0f);
            table4.setSpacingAfter(0f);
            addTableCellWithMixedFonts(table4, "Amniotic Fluid: ", minorReportPanel.getAmnioticFluid(), boldFont, normalFont);
            document.add(table4);

            document.add(new Paragraph(""));
            // Add Information [Fetal Biometry]
            PdfPTable table5 = new PdfPTable(3);
            table5.setWidthPercentage(100);
            table5.setSpacingBefore(10f);
            table5.setSpacingAfter(0f);
            addTableCell(table5, "Fetal Biometry: ", boldFont);
            addBlankTableCell(table5);
            addBlankTableCell(table5);
            addBlankTableCell(table5);
            addTableCellWithMixedFonts(table5, "GS: ", minorReportPanel.getFBGSmm(), boldPoint, normalFont);
            addTableCellWithText(table5, minorReportPanel.getFBGS(), normalFont);
            addBlankTableCell(table5);
            addTableCellWithMixedFonts(table5, "CRL: ", minorReportPanel.getCRLmm(), boldPoint, normalFont);
            addTableCellWithText(table5, minorReportPanel.getCRL(), normalFont);
            document.add(table5);

            document.add(new Paragraph(""));
            // Add Information [Impression & Comments, Additional Notes]
            PdfPTable table6 = new PdfPTable(1);
            table6.setWidthPercentage(100);
            table6.setSpacingBefore(0f);
            table6.setSpacingAfter(0f);
            addTableCellWithMixedFonts(table6, "\nImpression & Comment: ", minorReportPanel.getImpressAndComm(), boldFont, boldFontData);
            if (!minorReportPanel.getAddiNote().isEmpty()) {
                addTableCellWithMixedFonts(table6, "Additional Note: ", minorReportPanel.getAddiNote(), boldFont, boldFontData);
            }
            document.add(table6);

            document.add(new Paragraph("\n"));

            // Add Information [Notes, Dr. Name]
            PdfPTable table7 = new PdfPTable(2);
            table7.setWidthPercentage(100);
            table7.setSpacingBefore(0f);
            table7.setSpacingAfter(0f);

            // Set column widths (60% for the first column, 40% for the second)
            float[] columnWidths = {0.60f, 0.40f};
            table7.setWidths(columnWidths);

            addTableCellWithText(table7, "NOTE:", boldFontData);
            addTableCellWithText(table7, "  ", smallNormal);

            // Create a paragraph with custom line spacing
            Paragraph noteText = new Paragraph("      - This is Level 1 ultrasound examination.\n" +
                    "      - It may not detect all congenital abnormalities.\n" +
                    "      - Clinical Celestial is essential.\n" +
                    "      - Level 2 ultrasound may be done at your discretion.\n", normalForNote);
            noteText.setLeading(14f); // Set the line spacing

            PdfPCell noteCell = new PdfPCell();
            noteCell.addElement(noteText);
            noteCell.setBorder(PdfPCell.NO_BORDER);
            table7.addCell(noteCell);

            Paragraph doctorInfo = new Paragraph();
            doctorInfo.setLeading(14f); // Set the line spacing
            doctorInfo.add(new Chunk("\n\n\n\n    Dr. MAYURI HIWALE\n", boldFont));
            doctorInfo.add(new Chunk("    MBBS. DNB (Obs & Gynae) Hyderabad \n" +
                    "    Fellowship in Ultrasonography (Mumbai)", smallNormal));

            PdfPCell doctorCell = new PdfPCell();
            doctorCell.addElement(doctorInfo);
            doctorCell.setBorder(PdfPCell.NO_BORDER);
            table7.addCell(doctorCell);

            document.add(table7);

            document.add(new Paragraph("\n"));
            // Add Information [Amniotic Fluid, Impression & Comments, Additional Notes]
            PdfPTable table8 = new PdfPTable(1);
            table8.setWidthPercentage(100);
            table8.setSpacingBefore(0f);
            table8.setSpacingAfter(0f);
            addTableCell(table8, "Please correlate clinically \n" +
                    "I Dr. MAYURI DHORAN, declare that I've not detected fetal sex not disclosed fetal set to any body in any manner while conducting USG study.", boldFontData);

            document.add(table8);


            JOptionPane.showMessageDialog(this, "PDF generated successfully! ", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to generate PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public void saveMinorReportData() {
        // Create an instance of DatabaseStorage and pass the data
        MinorReportDatabase databaseStorage = new MinorReportDatabase(
                fileName.substring(0, fileName.length()-4), //-4 to remove ".pdf"
                minorReportPanel.getPatientName(),
                minorReportPanel.getAge(),
                minorReportPanel.getSex(),
                minorReportPanel.getDoctorName(),
                minorReportPanel.getCurrentDate(),
                minorReportPanel.getLMP(),
                minorReportPanel.getGA(),
                minorReportPanel.getAGA(),
                minorReportPanel.getEdd1(),
                minorReportPanel.getEdd2(),
                minorReportPanel.getLiverFetusNote(),
                minorReportPanel.getLiverFetusNumNote(),
                minorReportPanel.getFoetalAndSacField(),
                minorReportPanel.getOs(),
                minorReportPanel.getFetalCardiac(),
                minorReportPanel.getFBGSmm(),
                minorReportPanel.getFBGS(),
                minorReportPanel.getCRLmm(),
                minorReportPanel.getCRL(),
                minorReportPanel.getAmnioticFluid(),
                minorReportPanel.getImpressAndComm(),
                minorReportPanel.getAddiNote()
        );
    }

    // Helper method to add table cells with text
    private void addTableCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(5f); // Add spacing after the cell
        table.addCell(cell);
    }

    // Helper method to add table cells with mixed fonts
    private void addTableCellWithMixedFonts(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk(label, labelFont));
        paragraph.add(new Chunk(value, valueFont));
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingTop(5f); // Spacing after the cell
        table.addCell(cell);
    }

    // Helper method to add a blank cell
    private void addBlankTableCell(PdfPTable table) {
        PdfPCell blankCell = new PdfPCell(new Paragraph(" "));
        blankCell.setBorder(Rectangle.NO_BORDER);
        blankCell.setPaddingTop(10f);
        table.addCell(blankCell);
    }

    // Helper method to add table cells with text
    private void addTableCellWithText(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingTop(5f);
        table.addCell(cell);
    }

    private void addTableCellWithTextWithoudPadding(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingTop(0f);
        table.addCell(cell);
    }

    public void viewPDFReport() {
        Path usReportFolderPath = Path.of("D:", "USG Reports", "Minor Report");

        // Check if USReport folder exists
        if (!Files.exists(usReportFolderPath)) {
            JOptionPane.showMessageDialog(this, "The 'USG Report/Minor Report' folder does not exist on the D drive.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Stream<Path> files = Files.list(usReportFolderPath)) {
            // Find the most recently modified PDF file
            Optional<Path> latestFilePath = files.filter(file -> file.toString().endsWith(".pdf"))
                    .max(Comparator.comparingLong(file -> file.toFile().lastModified()));

            if (latestFilePath.isPresent()) {
                File latestFile = latestFilePath.get().toFile();
                // Open the PDF file
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(latestFile);
                } else {
                    JOptionPane.showMessageDialog(this, "Opening PDFs is not supported on this platform.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No PDF files found in the 'USReport' folder.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to open the latest PDF file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Ultrasound Report Generator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                MinorReportMainPanel minorReportMainPanel = new MinorReportMainPanel();
                frame.add(minorReportMainPanel, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }

}
