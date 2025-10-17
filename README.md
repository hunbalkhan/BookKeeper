# BookKeeper - Capstone Project 1

**Year Up United – Java Capstone Project 1**  
This is my first capstone project for the Year Up United program. The program is written in **Java** and simulates a simple bookkeeping application. Users can add deposits, make payments, view ledgers, and generate reports.


---

## Project Overview
BookKeeper is a simple yet powerful Java-based bookkeeping application designed to help users easily manage and track their personal or small business transactions. The goal of this project is to demonstrate core Java skills such as file handling, object-oriented programming, and error handling, while also providing a user-friendly interface.


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

## How to Use
1. Launch the application.
2. From the home menu, choose to add a deposit, make a payment, view the ledger, or generate reports.
3. Transactions are automatically recorded with the current date and time.
4. Use the "Search by Vendor" feature to quickly filter transactions.
5. Exit the program when finished; all data is saved in `transactions.csv`.

---

### 3. **Error Handling and Validation**
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

<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/859201ec-d00f-4ac7-9beb-699b23995c0d" />
This first screenshot is my CSV file and shown to be randomized.




<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/2bc3871a-3a7d-40ee-a951-2c09c099bb4d" />
This next screenshot is a display from the console for the user.

---

### 6. **User-Friendly Console Interface**
A clear, structured console interface that guides users step by step through adding deposits, payments, and generating reports.
<img width="400" height="600" alt="image" src="https://github.com/user-attachments/assets/cc6820c8-051f-4290-b1ed-9e4169d2d837" />

---

## Try It Out
Clone this repository and run the program to start managing your transactions today! Explore all features and see how BookKeeper simplifies bookkeeping.

## Future Possible Enhancements
- Add graphical reports for better visualization
- Add a manual cash entry for under the table entries that cannot be automatically recorded
- Support multiple accounts and categories

## Acknowledgements
- Year Up United for the capstone project
- Inspired by real-world bookkeeping needs

Thank you for checking out BookKeeper! Feedback and contributions are welcome.
