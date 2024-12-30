package org.prathameshsingh;

import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.draw.LineSeparator;
import org.apache.pdfbox.io.IOUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;


public class MajorReportMainPanel extends JPanel {
    private MajorReportPanel majorReportPanel;
    private String fileName;

    //Global PDF fonts
    Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
    Font normalForNote = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL);
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    Font boldFontHead = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    Font boldFontData = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    Font boldPoint = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
    Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);

    public MajorReportMainPanel() {
        setLayout(new BorderLayout());

        majorReportPanel = new MajorReportPanel();
        add(majorReportPanel, BorderLayout.CENTER);
    }

    public void generateMajorReport() {
        String patientName = majorReportPanel.getPatientNameM();
        String age = majorReportPanel.getAgeM();

        if(patientName.isEmpty()){
           JOptionPane.showMessageDialog(null, "Please enter patient name!", "Error", JOptionPane.ERROR_MESSAGE);
           return;
        }
        if(age.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter age!",  "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate file name
        fileName = patientName.replaceAll("[^a-zA-Z0-9]", "") + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";

        // Define the path to the 'USG Reports/Photo Report' folder on the 'D' drive
        Path usReportFolderPath = Path.of("D:", "USG Reports", "Major Report");

        Document document = null;
        try {
            // Check if USReport folder exists, if not create it
            if (!Files.exists(usReportFolderPath)) {
                Files.createDirectories(usReportFolderPath);
            }

            // Define the full path to save the PDF
            File pdfFile = usReportFolderPath.resolve(fileName).toFile();

            document = new Document(PageSize.A4, 35, 30, 3, 3); // Reduce top margin to shift the image upwards
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


            document.add(new Paragraph(""));
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(5f);
            table.setSpacingAfter(0f);
            addTableCell(table, "Name   : " + majorReportPanel.getPatientNameM(), boldFont);
            addTableCell(table, "                                  Age/Sex : " + majorReportPanel.getAgeM() + "/" + majorReportPanel.getSexPatientM(), boldFont);
            addTableCell(table, "Ref By : " + majorReportPanel.getDrNameM(), boldFont);
            addTableCell(table, "                                  Date       : " + majorReportPanel.getCurrDateM(), boldFont);
            document.add(table);

            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineWidth(1f); // Set the width of the line
            document.add(new Chunk(lineSeparator));

            document.add(new Paragraph());
            PdfPTable table2 = new PdfPTable(1);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(0f);
            table2.setSpacingAfter(0f);
            addTableCellWithText(table2, "TEST: USE OF GRAVID UTERUS", boldFontHead);
            document.add(table2);

            document.add(new Paragraph(""));
            PdfPTable table3 = new PdfPTable(1);
            table3.setWidthPercentage(100);
            table3.setSpacingBefore(5f);
            table3.setSpacingAfter(0f);
            addTableCellWithMixedFonts(table3, "• "+majorReportPanel.getLivesData(),majorReportPanel.getLiveDataBold(), normalFont, boldFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getFoetalCardiacData(), normalFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getPlacentaGradeData(), normalFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getPlacentalClotsData(), normalFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getAFI(), boldPoint);
            addTableCellWithText(table3, "• "+majorReportPanel.getAmnioticFluidData(), normalFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getOsData(), normalFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getUmbilicalCordData(), boldFont);
            addTableCellWithText(table3, "• "+majorReportPanel.getFSR(), boldPoint);
            document.add(table3);


            document.add(new Paragraph(""));
            PdfPTable table4 = new PdfPTable(2);
            table4.setWidthPercentage(100);
            table4.setSpacingBefore(5f);
            table4.setSpacingAfter(0f);
            float[] columnWidths4 = {0.70f, 0.30f};
            table4.setWidths(columnWidths4);
            addTableCellWithText(table4, "Foetal Biometry: ", boldFontData);
            addBlankTableCell(table4);
            addTableCellWithMixedFonts(table4, "                LMP: ", majorReportPanel.getLMP(), boldPoint, normalFont);
            addTableCellWithMixedFonts(table4, "EDD: ", majorReportPanel.getEdd1(), boldPoint, normalFont);
            document.add(table4);


            // Create a table with 4 rows and 7 columns, and populate each cell with data
            PdfPTable newTable = new PdfPTable(7); // 7 columns
            newTable.setWidthPercentage(100);
            newTable.setSpacingBefore(10f);
            newTable.setSpacingAfter(0f);

// Method to create a cell with centered text and increased height


            newTable.addCell(createCell("BPD"));
            newTable.addCell(createCell(majorReportPanel.getRC00()));
            newTable.addCell(createCell("CM"));
            newTable.addCell(createCell(majorReportPanel.getRC01()));
            newTable.addCell(createCell("WEEKS"));
            newTable.addCell(createCell(majorReportPanel.getRC02()));
            newTable.addCell(createCell("DAY"));

            newTable.addCell(createCell("HC"));
            newTable.addCell(createCell(majorReportPanel.getRC10()));
            newTable.addCell(createCell("CM"));
            newTable.addCell(createCell(majorReportPanel.getRC11()));
            newTable.addCell(createCell("WEEKS"));
            newTable.addCell(createCell(majorReportPanel.getRC12()));
            newTable.addCell(createCell("DAY"));

            newTable.addCell(createCell("AC"));
            newTable.addCell(createCell(majorReportPanel.getRC20()));
            newTable.addCell(createCell("CM"));
            newTable.addCell(createCell(majorReportPanel.getRC21()));
            newTable.addCell(createCell("WEEKS"));
            newTable.addCell(createCell(majorReportPanel.getRC22()));
            newTable.addCell(createCell("DAY"));

            newTable.addCell(createCell("FM"));
            newTable.addCell(createCell(majorReportPanel.getRC30()));
            newTable.addCell(createCell("CM"));
            newTable.addCell(createCell(majorReportPanel.getRC31()));
            newTable.addCell(createCell("WEEKS"));
            newTable.addCell(createCell(majorReportPanel.getRC32()));
            newTable.addCell(createCell("DAY"));

            document.add(newTable);

            document.add(new Paragraph(""));
            PdfPTable table5 = new PdfPTable(2);
            table5.setWidthPercentage(100);
            table5.setSpacingBefore(5f);
            table5.setSpacingAfter(0f);
            float[] columnWidths5 = {0.70f, 0.30f};
            table5.setWidths(columnWidths5);
            addTableCellWithMixedFonts(table5, "AVG AGE BY USG         : ", majorReportPanel.getAvgAge(), boldFontData, normalFont);
            addTableCellWithMixedFonts(table5, "EDD: ", majorReportPanel.getEdd2(), boldFontData, normalFont);
            addTableCellWithMixedFonts(table5, "Estimated Foetal Weight  : ", majorReportPanel.getFoetalWeight(), boldFontData, normalFont);
            addBlankTableCell(table5);
            document.add(table5);

            document.add(new Paragraph(""));
            PdfPTable table6 = new PdfPTable(1);
            table6.setWidthPercentage(100);
            table6.setSpacingBefore(6f);
            table6.setSpacingAfter(0f);
            addTableCell(table6, "IMP     :  "+majorReportPanel.getImp(), boldPoint);
            if (!majorReportPanel.getAddiNote().isEmpty()) {
                addTableCellWithMixedFonts(table6, "NOTE  :  ", majorReportPanel.getAddiNote(), boldPoint, boldPoint);
            }
            document.add(table6);

            document.add(new Paragraph("\n"));
            PdfPTable table7 = new PdfPTable(2);
            table7.setWidthPercentage(100);
            table7.setSpacingBefore(10f);
            table7.setSpacingAfter(0f);
            float[] columnWidths7 = {0.60f, 0.40f};
            table7.setWidths(columnWidths7);
            addBlankTableCell(table7);
            Paragraph doctorInfo = new Paragraph();
            doctorInfo.setLeading(14f); // Set the line spacing
            doctorInfo.add(new Chunk("\n\n    Dr. MAYURI HIWALE\n", boldFont));
            doctorInfo.add(new Chunk("    MBBS. DNB (Obs & Gynae) Hyderabad \n" +
                    "    Fellowship in Ultrasonography (Mumbai)", smallNormal));

            PdfPCell doctorCell = new PdfPCell();
            doctorCell.addElement(doctorInfo);
            doctorCell.setBorder(PdfPCell.NO_BORDER);
            table7.addCell(doctorCell);
            document.add(table7);


            JOptionPane.showMessageDialog(this, "PDF generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to generate PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    public void saveMajorReportData() {
        // Create an instance of DatabaseStorage and pass the data
        MajorReportDatabase databaseStorage = new MajorReportDatabase(
                fileName.substring(0, fileName.length()-4), //-4 to remove ".pdf"
                majorReportPanel.getPatientNameM(),
                majorReportPanel.getAgeM(),
                majorReportPanel.getSexPatientM(),
                majorReportPanel.getCurrDateM(),
                majorReportPanel.getDrNameM(),
                majorReportPanel.getLivesData(),
                majorReportPanel.getLiveDataBold(),
                majorReportPanel.getFoetalCardiacData(),
                majorReportPanel.getPlacentaGradeData(),
                majorReportPanel.getFSR(),
                majorReportPanel.getPlacentalClotsData(),
                majorReportPanel.getAFI(),
                majorReportPanel.getOsData(),
                majorReportPanel.getAmnioticFluidData(),
                majorReportPanel.getUmbilicalCordData(),
                majorReportPanel.getLMP(),
                majorReportPanel.getEdd1(),
                majorReportPanel.getEdd2(),
                majorReportPanel.getRC00(),
                majorReportPanel.getRC01(),
                majorReportPanel.getRC02(),
                majorReportPanel.getRC10(),
                majorReportPanel.getRC11(),
                majorReportPanel.getRC12(),
                majorReportPanel.getRC20(),
                majorReportPanel.getRC21(),
                majorReportPanel.getRC22(),
                majorReportPanel.getRC30(),
                majorReportPanel.getRC31(),
                majorReportPanel.getRC32(),
                majorReportPanel.getAvgAge(),
                majorReportPanel.getFoetalWeight(),
                majorReportPanel.getImp(),
                majorReportPanel.getAddiNote()
        );
    }

    private void addTableCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingTop(5f); // Add spacing after the cell
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

    private void addTableCellWithMixedFonts(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk(label, labelFont));
        paragraph.add(new Chunk(value, valueFont));
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingTop(5f); // Spacing after the cell
        table.addCell(cell);
    }

    PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setMinimumHeight(25f); // Increase cell height
        return cell;
    }

    public void viewMajorReportPDF() {
        Path majorReportFolderPath = Path.of("D:", "USG Reports", "Major Report");

        System.out.println("in view PDF report function");
        // Check if USReport folder exists
        if (!Files.exists(majorReportFolderPath)) {
            JOptionPane.showMessageDialog(this, "The 'USG Report/Major Report' folder does not exist on the D drive.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Stream<Path> files = Files.list(majorReportFolderPath)) {
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

}
