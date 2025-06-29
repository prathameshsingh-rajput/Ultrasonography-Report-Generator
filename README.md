# Ultrasonography Report Generator 🏥📄

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/PDF_Generation-FF0000?style=for-the-badge&logo=adobeacrobatreader&logoColor=white" alt="PDF">
  <img src="https://img.shields.io/badge/Medical-Tech-9cf?style=for-the-badge" alt="Medical Tech">
  <br>
  <img src="https://img.shields.io/badge/Version-1.0.0-blue?style=flat-square" alt="Version">
  <img src="https://img.shields.io/badge/License-MIT-green?style=flat-square" alt="License">
  <img src="https://img.shields.io/badge/Status-Production%20Ready-brightgreen?style=flat-square" alt="Status">
</div>

<br>

A professional Java desktop application for generating ultrasound examination reports with PDF output. Designed for medical practitioners to streamline diagnostic documentation workflow.

![Application Screenshot](assets/demo-screenshot.png) <!-- Replace with actual screenshot -->

## ✨ Key Features

### 📋 Comprehensive Report Types
| Report Type | Description |
|-------------|-------------|
| **Minor USG Report** | For pregnancies up to 10 weeks gestation |
| **Major USG Report** | For pregnancies beyond 10 weeks gestation |
| **Photo Copy Report** | Includes 6-8 sonography images with annotations |

### 🚀 Core Functionality
- **Dynamic PDF Generation** with professional formatting
- **Automated File Management** (saves to `~/Desktop/USReport`)
- **Patient Data Security** with proper record handling
- **Multi-image Support** for comprehensive photo documentation

### 🖥️ User Experience
- Intuitive Swing-based GUI with modern styling
- Input validation for data integrity
- Responsive design with clear error messaging
- Quick report generation (<5 seconds)

## 🛠️ Technical Specifications

```text
Platform: Java SE 11+
GUI Framework: AWT/Swing
PDF Library: iText 7.2.3
Dependencies: See pom.xml
Build Tool: Maven
