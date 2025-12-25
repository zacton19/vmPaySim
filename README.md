# Campus ID–Based Vending Payment System

A computer science project that simulates a **school-issued ID card payment system** for campus vending machines using **mock currency**. Each student automatically
receives a **weekly allowance every Monday**, which can only be spent on campus by swiping their student ID.

This project is designed as a realistic backend systems simulation and does not involve real money or financial institutions.

---

**** REALISTIC SOLUTION TO ONGOING PROBLEM ON COLLEGE CAMPUS
    Vending machines do not accept payment via campus ID cards, even though they claim to do so. This is supposed to simulate a real working solution.

## Project Overview

Many universities allow students to use their ID cards to purchase items on campus. This project recreates that environment by implementing:

- Student ID–linked accounts
- Automated weekly allowance distribution
- Vending machine purchase validation
- Secure transaction tracking using mock campus currency

The system is fully testable without access to real vending machines or physical ID hardware.

---

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

- Backend: Java / Python / Node.js (implementation-dependent)
- API: RESTful architecture
- Database: SQL or NoSQL for account and transaction storage
- Scheduler: Cron-style job for weekly allowance distribution
- Simulation: Virtual vending machines and ID swipe emulator

---

## Simulation Environment

Because real vending machines and card readers are not available, the project includes:

- Virtual vending machines
- Simulated ID swipes
- Mock student accounts
- End-to-end purchase testing

This allows the full system to be developed, tested, and demonstrated entirely in software.

---

## Repository Structure

backend/
  api/
  services/
  models/
  scheduler/

database/
  schema.sql

simulator/
  vending_machine_simulator.py

docs/
  architecture.md

---

## Learning Objectives

This project demonstrates understanding of:

- Object-oriented programming
- Backend system design
- API development
- Scheduled automation
- Data modeling
- Transaction validation
- Secure system simulation

---

## Disclaimer

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
