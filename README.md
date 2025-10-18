# Adyapana Institute - Student Management System

A comprehensive Java Swing-based Student Management System for educational institutes with admin dashboard functionality.

📖 Project Overview

Adyapana Institute Management System is a full-featured desktop application designed to streamline educational institute operations. It provides complete management of students, teachers, classes, subjects, payments, and attendance through an intuitive graphical interface.

🎯 Features

🔐 Authentication & Security
- Secure Admin Login with email validation
- Session-based authentication
- Role-based access control

👥 Student Management
- Complete student registration with personal details
- Student enrollment in classes
- Attendance tracking with real-time marking
- Search and filter capabilities

👨‍🏫 Teacher Management
- Teacher registration and profile management
- Subject assignment tracking
- Class-teacher association

📚 Academic Management
- Subject registration with pricing
- Class creation and scheduling
- Time slot management
- Teacher-subject assignment

💰 Financial Management
- Student payment processing
- Monthly fee tracking
- Payment due list generation
- Invoice generation with JasperReports
- Balance calculation

📊 Reporting & Analytics
- Payment due lists by month
- Student attendance reports
- Financial summaries
- Printable invoices

🏗️ System Architecture

Frontend
- Java Swing with FlatMacDarkLaf for modern UI
- JTable for data display
- JasperReports for invoice generation

Backend
- MySQL Database for data persistence
- JDBC for database connectivity
- MVC Pattern for clean architecture

Database Schema
Key tables include:
- `student` - Student information
- `teacher` - Teacher details  
- `subjects` - Course catalog
- `class` - Class schedules
- `enrollment` - Student-class relationships
- `invoice` - Payment records
- `attendance` - Attendance tracking
- `admin` - Administrator accounts

🚀 Installation & Setup

Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7+


Database Configuration
1. Create MySQL database named `adyapana`
2. Update connection details in `ConnectionClass.java`:
```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/adyapana",
    "root", 
    "****"
);
```

Required Dependencies
- `flatlaf` - Modern UI theme
- `mysql-connector-java` - MySQL driver
- `jasperreports` - Reporting engine


📁 Project Structure

```
src/
├── gui/
│   ├── Login.java                    # Authentication
│   ├── AdminDashBoard.java          # Main dashboard
│   ├── StudentRegistration.java     # Student CRUD
│   ├── TeacherRegistration.java     # Teacher CRUD
│   ├── SubjectRegistration.java     # Subject management
│   ├── ClassRegistration.java       # Class scheduling
│   ├── StudentEnrollment.java       # Enrollment management
│   ├── StudentPayment.java          # Payment processing
│   ├── DueList.java                 # Payment due tracking
│   └── StudentAttendance.java       # Attendance system
├── modal/
│   └── ConnectionClass.java         # Database connection
└── report/
    └── adyapana.jasper              # Invoice templates
```

🎮 Usage Guide

1. Login
- Launch the application
- Enter admin credentials (email/password)
- Access the main dashboard

2. Student Management
- Register new students with personal details
- Enroll students in available classes
- Mark daily attendance
- Process monthly payments

3. Teacher Management
- Add teacher profiles with subject expertise
- Assign teachers to classes
- Manage teacher schedules

4. Academic Setup
- Create subjects with pricing
- Schedule classes with time slots

5. Financial Operations
- Process student payments
- Generate invoices
- Track payment dues by month
- View payment history

🔧 Technical Features

UI/UX
- Dark theme with consistent color scheme
- Responsive design with proper form validation
- Keyboard navigation support


Data Management
- CRUD operations for all entities
- Real-time data validation
- Search and filter functionality
- Data integrity constraints

Security
- Input validation and sanitization
- SQL injection prevention
- Secure authentication flow

🛠️ Development

Extending the System
- Add new modules by extending existing patterns
- Implement new reports using JasperReports
- Add export functionality for data
- Implement backup/restore features

Customization
- Modify color schemes in individual forms
- Add new database fields with corresponding UI updates
- Extend reporting capabilities

👥 User Roles

Administrator
- Full system access
- User management
- System configuration
- Reporting and analytics

📊 Reports Available

1. Payment Invoices - Student payment receipts


