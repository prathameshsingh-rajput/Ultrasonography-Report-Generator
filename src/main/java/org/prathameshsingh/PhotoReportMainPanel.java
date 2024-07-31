package org.prathameshsingh;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.apache.pdfbox.io.IOUtils;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PhotoReportMainPanel extends JPanel {
    private PhotoReportPanel photoReportPanel;

    // Output PDF Global Font
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    Font boldPoint = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    Font myAdFont = new Font(Font.FontFamily.COURIER, 8, Font.ITALIC);

    public PhotoReportMainPanel() {
        setLayout(new BorderLayout());

        photoReportPanel = new PhotoReportPanel();
        add(photoReportPanel, BorderLayout.CENTER);
    }

    public void generatePDF() {
        String patientName = photoReportPanel.getPatientName();
        String patientAge = photoReportPanel.getAge();

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
        String fileName = patientName.replaceAll("[^a-zA-Z0-9]", "") + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";

        // Define the path to the 'USG Reports/Photo Report' folder on the 'D' drive
        Path usReportFolderPath = Path.of("D:", "USG Reports", "Photo Report");

        Document document = null;
        try {
            // Check if USReport folder exists, if not create it
            if (!Files.exists(usReportFolderPath)) {
                Files.createDirectories(usReportFolderPath);
            }

            // Define the full path to save the PDF
            File pdfFile = usReportFolderPath.resolve(fileName).toFile();

            document = new Document(PageSize.A4, 36, 36, 15, 5); // Reduce top margin to shift the image upwards
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // Add Image at the top
            try {
                InputStream imageStream = getClass().getClassLoader().getResourceAsStream("PhotoReportTopImg.png");
                if (imageStream != null) {
                    com.itextpdf.text.Image topImage = Image.getInstance(IOUtils.toByteArray(imageStream));
                    topImage.scaleToFit(PageSize.A4.getWidth() - 50, PageSize.A4.getHeight() / 4); // scale to fit the top portion
                    topImage.setAlignment(Element.ALIGN_CENTER);
                    topImage.setSpacingBefore(0); // No space before the image
                    topImage.setSpacingAfter(0); // Reduce space after the image
                    document.add(topImage);
                } else {
                    System.err.println("Top image not found in the resources folder.");
                }
            } catch (Exception e) {
                System.err.println("Top image failed to load: " + e.getMessage());
            }

            // Add a line separator under the data
            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setOffset(0); // Adjust offset to move the line closer to the image
            document.add(new Chunk(lineSeparator));

            // Reduce the space between the image and the text
//            document.add(new Paragraph("", new Font(Font.FontFamily.HELVETICA, 5)));

            // Add Patient and Doctor Information
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(0f);
            table.setSpacingAfter(2f);

            addTableCell(table, "Patient ID       : " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), boldFont);
            addTableCell(table, "                                  Date       : " + photoReportPanel.getCurrentDate(), boldFont);
            addTableCell(table, "Patient Name : " + photoReportPanel.getPatientName(), boldFont);
            addTableCell(table, "                                  Age/Sex : " + photoReportPanel.getAge() + "/ " + photoReportPanel.getSex(), boldFont);
            addTableCell(table, "Consulted by  : " + photoReportPanel.getDoctorName(), boldFont);
            addTableCell(table, "                                  Ref by    : " + photoReportPanel.getReferredBy(), boldFont);

            document.add(table);

            // Add a line separator under the data
            LineSeparator lineSeparator2 = new LineSeparator();
            lineSeparator2.setOffset(0); // Adjust offset to move the line closer to the image
            document.add(new Chunk(lineSeparator2));

            // Add selected images in two columns
            PdfPTable imageTable = new PdfPTable(2);
            imageTable.setWidthPercentage(100);
            imageTable.setSpacingBefore(5f);
            imageTable.setSpacingAfter(5f);

            List<ImageIcon> selectedImages = photoReportPanel.getSelectedImages();
            if(selectedImages.size() == 6){
                for (ImageIcon imageIcon : selectedImages) {
                    PdfPCell cell = new PdfPCell();
                    cell.setPaddingTop(15);
                    cell.setPaddingBottom(20);
                    cell.setPaddingLeft(30);
                    cell.setPaddingRight(10);
                    cell.setBorder(PdfPCell.NO_BORDER);

                    Image image = Image.getInstance(imageIcon.getImage(), null);
                    image.scaleToFit(200, 170);
                    cell.addElement(image);

                    imageTable.addCell(cell);
                }
            }else{
                for (ImageIcon imageIcon : selectedImages) {
                    PdfPCell cell = new PdfPCell();
                    cell.setPaddingTop(2);
                    cell.setPaddingBottom(5);
                    cell.setPaddingLeft(30);
                    cell.setPaddingRight(10);
                    cell.setBorder(PdfPCell.NO_BORDER);

                    Image image = Image.getInstance(imageIcon.getImage(), null);
                    image.scaleToFit(200, 170);
                    cell.addElement(image);

                    imageTable.addCell(cell);
                }
            }

            document.add(imageTable);


            // If the number of images is odd, add an empty cell to complete the table
//            if (selectedImages.size() % 2 != 0) {
//                PdfPCell emptyCell = new PdfPCell();
//                emptyCell.setBorder(PdfPCell.NO_BORDER);
//                imageTable.addCell(emptyCell);
//            }

            if(selectedImages.size() == 6) {
                document.add(new Paragraph("\n\n"));
                PdfPTable lastTable = new PdfPTable(2);
                lastTable.setWidthPercentage(100);
                lastTable.setSpacingBefore(80);
                lastTable.setSpacingAfter(0f);
                addTableCell(lastTable, "\n       www.linkedin.com/in/connect-psr", myAdFont);
                addTableCell(lastTable, "           Dr. Xyz ", boldPoint);


                document.add(lastTable);

            }else {
                document.add(new Paragraph("\n\n"));
                PdfPTable lastTable = new PdfPTable(2);
                lastTable.setWidthPercentage(100);
                lastTable.setSpacingBefore(15f);
                lastTable.setSpacingAfter(0f);
                addTableCell(lastTable, "\n       www.linkedin.com/in/connect-psr", myAdFont);
                addTableCell(lastTable, "           Dr. Xyz ", boldPoint);


                document.add(lastTable);
            }

            JOptionPane.showMessageDialog(this, "PDF generated successfully! ","Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to generate PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    public void viewPDFReport() {
        Path usReportFolderPath = Path.of("D:", "USG Reports", "Photo Report");

        // Check if USReport folder exists
        if (!Files.exists(usReportFolderPath)) {
            JOptionPane.showMessageDialog(this, "The 'USG Reports/Photo Report' folder does not exist on the D drive.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void addTableCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingBottom(2); // Reduce padding to adjust the space between rows
        table.addCell(cell);
    }


}

