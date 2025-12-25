# Campus ID–Based Vending Payment System

A computer science project that simulates a **school-issued ID card payment system** for campus vending machines using **mock currency**. Each student automatically
receives a **weekly allowance every Monday**, which can only be spent on campus by swiping their student ID.

This project is designed as a realistic backend systems simulation and does not involve real money or financial institutions.

---

## Problem Statement
Many college campuses have vending machines that claim to accept student ID cards but don't actually work with the campus payment system. This project provides a **complete backend solution** that could enable this functionality, demonstrating how ID card payments could be securely implemented across campus vending machines.

## Project Overview

Many universities allow students to use their ID cards to purchase items on campus. This project recreates that environment by implementing:

- Student ID–linked accounts
- Vending machine purchase validation
- Secure transaction tracking using mock campus currency

The system is fully testable without access to real vending machines or physical ID hardware.

---



## Setup and Run Instructions ****** READ TO RUN PROGRAM

- Download and install **Java JDK 8+** and **MySQL Server 8.0+** from their official websites (remember your MySQL root password during installation)
- Download **MySQL Connector/J (JDBC Driver)** from [mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/) and save it somewhere accessible
- Clone this repository: `git clone https://github.com/zacton19/campus-vending-system.git`
- Open **MySQL Command Line** or **MySQL Workbench** and connect to your MySQL server with your root password
- Run the database schema: `source path/to/schema.sql` (replace `path/to/` with actual file path, or in MySQL Workbench: File → Open SQL Script → select `schema.sql` → click lightning bolt)
- Run the seed data: `source path/to/seed.sql` (this creates 100 students, 15 vending machines, 50 products, and fully stocked inventory)
- Open the project folder in **IntelliJ IDEA** (File → Open → select the cloned folder)
- Add the MySQL Connector JAR to your project: **File → Project Structure (Ctrl+Alt+Shift+S) → Libraries → + → Java** → navigate to and select the `mysql-connector-j-8.x.x.jar` file you downloaded → click OK
- Open `DatabaseManager.java` and update lines 6-7 with your MySQL credentials: change `"root"` if you used a different username, and replace `"your_password"` with your actual MySQL password
- **Start the server first:** Run **PaymentServer.java** (right-click the file → Run 'PaymentServer.main()') - you should see "=== Campus Payment Server Starting ===" and "Listening on port 8888" - **leave this running**
- **Then start the client:** Run **VendingMachineClient.java** in a separate run window (right-click → Run 'VendingMachineClient.main()') while the server is still running
- Select a vending machine location (1-15) when prompted
- Login with any Student ID from **S00001** to **S00100** (all test accounts use PIN: **1234**)
- Browse products, make purchases, switch between different vending machines, and watch your balance update in real-time across all locations
- The server handles all authentication, transactions, and database operations securely with password hashing and SQL injection prevention
- **Troubleshooting:** If you get "Cannot connect to payment server" - make sure PaymentServer is running first and port 8888 isn't blocked
- **Troubleshooting:** If you get "Authentication failed" - verify you're using Student ID format S00001-S00100 with PIN 1234
- **Troubleshooting:** If you get "No products available" - make sure you ran both schema.sql and seed.sql files
- **Troubleshooting:** If MySQL connection fails - verify MySQL Server is running (check Windows Services for "MySQL80") and credentials in DatabaseManager.java are correct

## Core Features

### Student Accounts
- Unique student ID associated with each account
- Secure balance tracking
- Persistent transaction history

### ID Card Swipe System
- Simulated ID swipe authentication
- Balance verification before purchase
- Instant deduction upon successful transaction

### Vending Machine Integration
- Each vending machine has:
  - A unique machine ID
  - Item inventory
  - Configurable prices
- Prevents purchases when funds are insufficient
- Logs all purchase activity

### Weekly Allowance Automation
- Every Monday, all active student accounts are credited with a fixed allowance
- Allowance amount is configurable
- Implemented using a scheduled background task

### Safety Constraints
- Uses mock, school-specific currency only
- Currency is restricted to campus use
- No real money, banking APIs, or payment processors involved

---

## Key Technical Features

- **Client-Server Architecture**: Distributed system with TCP socket communication between vending machines and central payment server
- **Secure Authentication**: PBKDF2 password hashing with SHA-256 and unique salts per user (10,000 iterations)
- **ACID Transactions**: Database transactions ensure balance updates and inventory changes happen atomically
- **Multi-threaded Server**: Handles multiple vending machines simultaneously
- **SQL Injection Prevention**: All database queries use prepared statements
- **Real-time Balance Updates**: Student balances sync across all 15 campus locations instantly

## System Architecture

Student ID Card
↓
Vending Machine
↓
Campus Payment API
↓
Student Account Database

---

## Technology Stack

- **Backend**: Java
- **Database**: MySQL with JDBC
- **Architecture**: Client-Server model with TCP sockets
- **Security**: PBKDF2WithHmacSHA256 password hashing
- **Networking**: Java Socket API for client-server communication
- **Data Transfer**: Java Object Serialization for request/response protocol

---

## How It Works

This is a **fully functional system** with:
- Real client-server communication over TCP sockets
- Actual MySQL database with persistent storage
- Working authentication and transaction processing
- Live balance updates across multiple machines

While it doesn't connect to physical vending machines or real card readers, all the backend logic, security, and data handling are production-ready implementations that could be integrated with real hardware.



## Learning Objectives

This project demonstrates:

- **Client-Server Architecture**: Multi-threaded server handling concurrent connections
- **Network Programming**: TCP socket communication and custom protocol design
- **Database Design**: Normalized schema with proper foreign keys and constraints
- **Security**: Password hashing, salt generation, and SQL injection prevention
- **Transaction Management**: ACID-compliant financial transactions
- **Object-Oriented Design**: DAO pattern, DTOs, and separation of concerns
- **Concurrency**: Thread-safe transaction processing with synchronization

---


This project uses **mock campus currency only**.
It does not process real money, connect to financial institutions, or integrate with actual vending machines.
This system is intended solely for academic, educational, and portfolio purposes.

---

## Future Enhancements

- Administrative dashboard for managing students and machines
- Fraud prevention (duplicate swipe detection)
- Spending analytics
- Mobile app integration
- Hardware abstraction for RFID/NFC readers

---

## Contributions

This is an open project. Suggestions, issues, and pull requests are welcome.
