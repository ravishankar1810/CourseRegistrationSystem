# 🎓 Online Course Registration System

A comprehensive, full-stack web application for managing student course enrollments. This system allows university students to view available courses, register in real-time, track their weekly timetable, and view their academic grades.

Built using **Java Servlets**, **JSP**, and **MySQL** for data persistence, featuring a modern **Bootstrap 5** user interface.

---

## 📸 Project Screenshots

### Login & Dashboard
| Login Page | Student Dashboard |
|:---:|:---:|
| ![Login Page](https://github.com/user-attachments/assets/85aeeda6-5ea0-4221-a0ea-8bb07d11fa5) | ![Dashboard](https://github.com/user-attachments/assets/64099193-5ff0-470b-ba80-5c73e472ba50) |

### Course Management
| Course Catalog | Enrollment Success |
|:---:|:---:|
| ![Catalog](https://github.com/user-attachments/assets/2c58a327-121d-4025-a07a-2fa7dfd151e0) | ![Success Alert](https://github.com/user-attachments/assets/4c23b36d-6b77-4e1b-bccd-25c14a9cb7b8) |

### Student Features
| Weekly Timetable | Academic Grades | Profile Management |
|:---:|:---:|:---:|
| ![Timetable](https://github.com/user-attachments/assets/b9632247-88a1-4490-8253-f44a68789952) | ![Grades](https://github.com/user-attachments/assets/8ea2a1ad-4d4e-412f-8d05-699332f1a2bf) | ** |

---

## ✨ Features implemented

* **🔐 Secure Authentication:** Session-based login system (`HttpSession`) with password change functionality.
* **🚪 Logout:** Securely invalidates the user session.
* **📚 Live Course Catalog:** Displays course details and **real-time seat availability** from the MySQL database.
* **✅ Transactional Enrollment:**
    * Prevents over-enrollment.
    * Uses SQL `UPDATE...WHERE remainingSeats > 0` for atomic seat management.
    * Manages relationships using a dedicated `enrollments` table.
* **📅 Personal Timetable:** Automatically generates a weekly schedule based on enrolled courses.
* **📊 Grade Portal:** Students can view their academic history from the `grades` table.
* **👤 Profile Management:** Students can view their information and update their password.
* **📱 Responsive Design:** Fully responsive UI using **Bootstrap 5**, accessible on mobile and desktop.
* **💾 MySQL Database:** All data is stored in a relational MySQL database. The **DAO (Data Access Object)** layer uses **JDBC** to perform all SQL operations.

---

## 🛠️ Tech Stack

* **Backend:** Java (JDK 11+), Jakarta EE Servlets
* **Frontend:** JavaServer Pages (JSP), JSTL, Bootstrap 5 CSS
* **Database:** **MySQL 8.0+**
* **Database Driver:** **JDBC** (via `mysql-connector-j`)
* **Build Tool:** Apache Maven
* **Server:** Apache Tomcat 10/11

---

## 🚀 Installation & Setup

Follow these steps to run the project locally.

### 1. Prerequisites
* Java Development Kit (JDK) 11 or newer.
* Apache Tomcat 10 or 11.
* IntelliJ IDEA (Recommended) or Eclipse.
* **MySQL Server** and **MySQL Workbench** (or a similar tool).

### 2. Database Setup (Crucial Step)

1.  **Create the Database:** Open MySQL Workbench and run the following command:
    ```sql
    CREATE DATABASE course_registration;
    ```
2.  **Run the Schema Script:** Run the full SQL script (provided with the project) to create all the tables (`students`, `courses`, `grades`, `enrollments`) and insert the sample data.

### 3. Java Project Configuration (Crucial Step)

1.  **Open the Project** in IntelliJ IDEA.
2.  **Update `pom.xml`:** Make sure you have the `mysql-connector-j` dependency.
    ```xml
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.3.0</version> </dependency>
    ```
3.  **Configure the Database Connection:**
    * Open `src/main/java/com/example/util/DBUtil.java`.
    * Change the `PASSWORD` variable to your actual MySQL root password.
    ```java
    // --- UPDATE THESE DETAILS ---
    private static final String URL = "jdbc:mysql://localhost:3306/course_registration";
    private static final String USER = "root"; // Or your MySQL username
    private static final String PASSWORD = "YourPassword"; // <-- CHANGE THIS
    // ----------------------------
    ```

### 4. Run the Application

1.  **Load Maven Changes** (Right-click `pom.xml` -> Maven -> Reload Project).
2.  **Configure Tomcat:**
    * Click `Add Configuration` -> `Tomcat Server` -> `Local`.
    * In the **Deployment** tab, add the artifact: `CourseRegistrationSystem:war exploded`.
3.  **Run** the server. Your browser should open to the login page.

---

## 📖 Usage Guide

1.  **Login Credentials:**
    * **Username:** `Ravi` | **Password:** `p1`
    * **Username:** `r2` | **Password:** `p2`
    *(These are based on the `INSERT` script for the `students` table).*
2.  **Enrolling:**
    * Navigate to the **Catalog**.
    * Click **Enroll**. If successful, the seat count decreases immediately.
3.  **Dropping:**
    * Click **Drop** on any enrolled course to free up the seat.
4.  **Viewing Data:**
    * Check the **Timetable** to see your specific schedule.
    * Check **Grades** to see past performance.
5.  **Profile:**
    * Click the **Profile** link to change your password.

---

## 📂 Project Structure (MVC)
```CourseRegistrationSystem/
├── src/
│ └── main/
│ ├── java/
│ │ └── com/
│ │ └── example/
│ │ ├── dao/ # DAOs (JDBC logic)
│ │ ├── model/ # POJOs (Student, Course)
│ │ ├── servlet/ # Servlets (Controllers)
│ │ └── util/ # DBUtil.java (Connection)
│ │
│ └── webapp/
│ ├── WEB-INF/
│ │ └── web.xml
│ │
│ ├── catalog.jsp
│ ├── dashboard.jsp
│ ├── footer.jspf
│ ├── grades.jsp
│ ├── header.jspf
│ ├── login.jsp
│ ├── profile.jsp
│ └── timetable.jsp
│
├── pom.xml
└── README.md 
``` 

---

## 👥 Contributors
---

_Developed for the Bachelor of Engineering Mini-Project at Chandigarh University._


