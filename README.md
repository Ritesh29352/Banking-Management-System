#  Banking Management System

A **Java Console-Based Banking Management System** developed using **Java, JDBC, and MySQL**. This project allows users to perform banking operations such as creating accounts, depositing and withdrawing money, transferring funds, and managing customer account details through a menu-driven console application.

---

##  Features

-  Admin Login
-  Create New Bank Account
-  View All Accounts
-  Search Account by Account Number
-  Deposit Money
-  Withdraw Money
-  Transfer Money Between Accounts
-  Update Account Details
-  Delete Account
-  Check Account Balance
-  Store Data in MySQL Database
-  JDBC Database Connectivity

---

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- IntelliJ IDEA
- Git & GitHub

---

## Project Structure

```
BankingManagementSystem
│
├── src
│
├── dao
│     AccountDAO.java
│
├── model
│     Account.java
│
├── util
│     DBConnection.java
│
└── main
      Main.java

├── .gitignore
├── README.md
└── BankingManagementSystem.iml
```

---

##  Database Setup

### Step 1: Create Database

```sql
CREATE DATABASE banking_system;
```

### Step 2: Use Database

```sql
USE banking_system;
```

### Step 3: Create Table

```sql
CREATE TABLE accounts (

account_number BIGINT PRIMARY KEY,

holder_name VARCHAR(100),

age INT,

gender VARCHAR(20),

phone VARCHAR(15),

email VARCHAR(100),

address VARCHAR(255),

account_type VARCHAR(20),

balance DOUBLE

);
```

---

## Configure Database Connection

Open **DBConnection.java** and update your database credentials.

```java
private static final String URL = "jdbc:mysql://localhost:3307/banking_system";
private static final String USERNAME = "root";
private static final String PASSWORD = "YOUR_PASSWORD";
```

---

## ▶️ How to Run

1. Clone the repository.

```bash
git clone https://github.com/Ritesh29352/Banking-Management-System.git
```

2. Open the project in IntelliJ IDEA.

3. Add the MySQL JDBC Connector JAR.

4. Create the MySQL database and table.

5. Update the database credentials in `DBConnection.java`.

6. Run `Main.java`.

---

## Sample Menu

```
========================================
      BANKING MANAGEMENT SYSTEM
========================================

1. Create Account
2. View Accounts
3. Search Account
4. Deposit Money
5. Withdraw Money
6. Transfer Money
7. Update Account
8. Delete Account
9. Check Balance
10. Exit
```

---

## Sample Output

### Create Account

```
Enter Account Number : 1001

Enter Holder Name : Ritesh Kumar

Enter Age : 21

Enter Gender : Male

Enter Phone : 9876543210

Enter Email : ritesh@gmail.com

Enter Address : Bangalore

Enter Account Type : Savings

Enter Opening Balance : 10000

Account Created Successfully!
```

### Deposit Money

```
Enter Account Number : 1001

Enter Amount : 5000

₹5000 Deposited Successfully!

Current Balance : ₹15000
```

### Transfer Money

```
Sender Account : 1001

Receiver Account : 1002

Amount : 2000

Transfer Successful!
```

---

## Learning Outcomes

- Object-Oriented Programming (OOP)
- JDBC Connectivity
- MySQL Database Operations
- CRUD Operations
- SQL Transactions (Commit & Rollback)
- Exception Handling
- Java Collections & Scanner
- Project Structure and Code Organization
- Git & GitHub

---

## Future Enhancements

- Transaction History
- Customer Login
- Interest Calculation
- Loan Management
- ATM Simulation
- Export Account Details to Excel/PDF
- GUI using Java Swing or JavaFX

---

## Author

**Ritesh Kumar**

GitHub: https://github.com/Ritesh29352

---