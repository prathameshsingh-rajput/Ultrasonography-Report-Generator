# Ultrasonography Report Generator 🩺📄

A Java-based desktop application to generate and print professional ultrasound (USG) examination reports. Built with AWT and Swing, this software simplifies medical documentation by allowing healthcare professionals to generate dynamic PDF reports, with integrated patient information and optional sonography images.

---

## 🔧 Features

✅ Generate three types of reports:
- **Minor USG Report** (for up to 10 weeks of pregnancy)  
- **Major USG Report** (after 10 weeks of pregnancy)  
- **Photo Copy Report** (with 6 or 8 uploaded sonography images)

✅ PDF Report Generation:
- Patient & Doctor details
- Table formatting for clean layout
- Image integration for photo copy reports
- Auto-generated filename based on patient name and timestamp

✅ Report Saving:
- Automatically saves reports to `USReport` folder on Desktop
- Displays popup message if the folder does not exist

✅ User-Friendly Interface:
- Clean UI using Java AWT/Swing
- Easy navigation with `Generate` and `View` options
- Roboto font and enhanced styling for professional output

✅ Error Handling:
- Ensures all required fields are filled
- Handles invalid inputs, missing folders, and image limits

---

## 💻 Technologies Used

- Java AWT & Swing (for GUI)
- iText / Apache PDFBox (for PDF generation)
- Java IO & Utility Libraries (for file handling, image processing)

---

## 🏥 Real-World Use

This software is live and successfully used at **Navoday Hospital, Malkapur**, helping healthcare professionals streamline ultrasound reporting.

---

## 📸 Screenshots

> *Coming soon* — Add screenshots or GIF demos showing:
- Main Interface
- Input Form
- Generated PDF Sample
- Photo Copy Report with Images

---

## 🚀 How to Run

1. **Clone the Repository**
   ```bash
   git clone https://github.com/prathameshsingh-rajput/Ultrasonography-Report-Generator.git
2. **Open in IDE (Eclipse, IntelliJ, NetBeans)**

3. **Add Dependencies**

   If using iText or PDFBox, ensure you add the required .jar files to the project.

5. **Run the Main Class**

   Look for MainFrame.java or the entry point class and run it.

---

## 📂 Folder Structure

ultrasonography-report-generator/
│
├── src/
│   ├── MainFrame.java
│   ├── MainPanel.java
│   ├── MinorReportPanel.java
│   ├── MajorReportPanel.java
│   ├── PhotoCopyPanel.java
│   ├── PDFGenerator.java
│   └── ...
├── assets/
│   └── logo.png
├── README.md
└── LICENSE

---

## 🔮 Future Improvements
- Cloud backup and online access

- Multi-language support (Marathi, Hindi, English)

- Integration with hospital management systems (HMS)

- Export report to email or WhatsApp directly

- Data encryption for medical privacy

---

🌟 Show Your Support
If you like this project, don't forget to:

⭐ Star this repo
🍴 Fork it
🐞 Report Issues or suggest new features

---

## 📌 Acknowledgment
Special thanks to Navoday Hospital, Malkapur for trusting and implementing this software in their sonography workflow.


---


