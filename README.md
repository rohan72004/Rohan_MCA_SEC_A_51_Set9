# Gym Locker Management System

## 👤 Student Details

* **Name:** Rohan Singh
* **Roll No:** 51
* **Section:** A
* **Programme:** MCA
* **Semester:** 2

---

## 📌 Assignment Details

* **Subject:** DBMS & Core Java
* **Set Number:** 9
* **Problem Title:** Gym Locker Management System

---

## 📖 Project Description

This is a console-based Gym Locker Management System developed using Core Java and MySQL (JDBC). The system manages gym members and locker allocation efficiently with proper validation and database integration.

---

## 🔹 Features Implemented

* Add new member (with duplicate prevention using phone number)
* Assign available locker automatically
* Release locker
* View locker status (occupied / free)
* View total number of members
* View all members with complete details

---

## 🗄️ Database Details

The system uses two tables:

### 1. members

* member_id (Primary Key)
* name
* phone (Unique)
* plan

### 2. lockers

* locker_id (Primary Key)
* locker_no
* member_id (Foreign Key)
* assigned_on

---

## 📂 Folder Structure

```
Rohan_MCA_SEC_A_51_Set9/
│
├── src/
│   ├── Main.java
│   └── DBConnection.java
│
├── lib/
│   └── mysql-connector-j-9.6.0.jar
│
├── sql/
│   └── schema.sql
│
├── screenshots/
│   └── screenshot.png
│
└── README.md
```

---

## ⚙️ Technologies Used

* Java (Core Java)
* JDBC (Java Database Connectivity)
* MySQL

---

## ▶️ How to Run

1. Clone or download the repository
2. Open project in VS Code / Eclipse
3. Setup MySQL database
4. Run `schema.sql` file
5. Update database credentials in `DBConnection.java`
6. Compile:

   ```
   javac -cp ".;lib/mysql-connector-j-9.6.0.jar" src/*.java
   ```
7. Run:

   ```
   java -cp ".;lib/mysql-connector-j-9.6.0.jar;src" Main
   ```

---

## 📸 Output

Program execution screenshots are available in the `screenshots` folder.

---

## 📢 Declaration

This project is my original work and has been completed as part of the DBMS & Java assignment.

---
