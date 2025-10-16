# BookKeeper - Capstone Project 1

**Year Up United – Java Capstone Project 1**  
This is my first capstone project for the Year Up United program. The program is written in **Java** and simulates a simple bookkeeping application. Users can add deposits, make payments, view ledgers, and generate reports.

---

## Features

- **Add Deposit:** Record positive transactions with description, vendor, and amount.  
- **Make Payment:** Record negative transactions with description, vendor, and amount.  
- **Ledger Menu:** View all transactions, only deposits, or only payments.  
- **Reports Menu:** Generate Month-to-Date, Previous Month, Year-to-Date, and Previous Year reports.  
- **Search by Vendor:** Quickly filter transactions by vendor name.  
- **Persistent Storage:** Transactions are saved in a CSV file for long-term storage.  

---

## 💡 Functional Highlights

### 1. **Transaction Flow Overview**
Demonstrates the full journey — from adding a deposit or payment to seeing it appear instantly in the ledger and saved in transactions.csv. as well as automatic time recordings and date recordings.

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/4d365edd-2b43-4c34-b4ae-415796cce13f" />

---

### 2. **Error Handling and Validation**
Handles invalid input gracefully (e.g. Entering empty texts, entering text instead of numbers etc).

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/9d60fe89-702b-44c3-9e9c-972ea36b28cb" />

---

### 4. **Dynamic Reports**
Filter transactions dynamically by selecting from:
- Month-to-Date  
- Previous Month  
- Year-to-Date  
- Previous Year 

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/cb4ff81b-3b05-492e-92ac-4ddc4c22f9ed" />

---

### 5. **Vendor Search with Partial Matches**
Searches for transactions by vendor, supporting partial text (e.g., typing “ama” returns *Amazon* and *Amanda’s Shop*).

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/b08528d5-ce30-4dec-ae79-665287cd66e5" />


---

### 5. **Sorted Transactions**
Transactions are automatically sorted by newest date and time (descending order), so recent activity always appears first.



---

### 6. **Data Persistence**
All data remains stored even after the program is closed, thanks to the `saveEachTransaction()` method writing to `transactions.csv`.
![Vendor Search Screenshot](screenshots/vendor_search.png)

---

### 7. CSV File Example
Demonstrates how transactions are stored in `transactions.csv`.

![CSV File Screenshot](screenshots/csv_file.png)

---
