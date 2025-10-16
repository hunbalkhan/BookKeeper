package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {
        homeMenu(); // Start the program by showing the Home Menu
    }


    // --------- Home Menu (Main Loop) ---------
    public static void homeMenu() {
        while (true) {
            System.out.println("\n--------- Welcome to BookKeeper ---------\n");
            System.out.println("\n--- Home Menu ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("\nChoose an option");

            String choice = ConsoleHelper.promptForString("");

            switch (choice.toUpperCase().trim()) {
                case "D":
                    addDeposit(); // Add a deposit transaction
                    break;
                case "P":
                    makePayment(); // Add a payment transaction
                    break;
                case "L":
                    ledgerMenu(); // Go to Ledger Menu
                    break;
                case "X":
                    System.out.println("""
                            \n--------- Thank you for using BookKeeper! ---------
                            

                                              <- Goodbye! ->
                            """);
                    return; // Exit program
                default:
                    System.out.println("Invalid option - Please try again.");
                    break;
            }
        }
    }

    public static void addDeposit() {
        System.out.println("\n--- Add Deposit ---");

        String description = ConsoleHelper.promptForString("Description");
        String vendor = ConsoleHelper.promptForString("Vendor");
        double amount = ConsoleHelper.promptForDouble("Amount (numbers only)");

        if (amount <= 0) {
            System.out.println("\nAmount must be greater than $0.00. Deposit cancelled.");
            return;
        }

        // Record current date and time
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        Transaction deposit = new Transaction(today, now, description, vendor, amount);

        saveEachTransaction(deposit); // Save transaction to CSV
        System.out.println("Deposit Added: " + deposit);
    }

    public static void makePayment() {
        System.out.println("\n--- Make Payment ---");

        String description = ConsoleHelper.promptForString("Description");
        String vendor = ConsoleHelper.promptForString("Vendor");
        double amount = ConsoleHelper.promptForDouble("Amount (numbers only)");

        if (amount <= 0) {
            System.out.println("\nAmount must be greater than $0.00. Payment cancelled.");
            return;
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        Transaction payment = new Transaction(today, now, description, vendor, -amount);

        saveEachTransaction(payment); // Save payment to CSV
        System.out.println("Payment recorded: " + payment);
    }

    public static void ledgerMenu() {
        while (true) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A) All entries");
            System.out.println("D) Deposits only");
            System.out.println("P) Payments only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("\nChoose an option");

            String choice = ConsoleHelper.promptForString("");
            ArrayList<Transaction> transactions = readTransactions();

            switch (choice.toUpperCase().trim()) {
                case "A":
                    displayTransactions(transactions); // Show all transactions
                    break;
                case "D":
                    depositsOnly(transactions); // Show only deposits
                    break;
                case "P":
                    paymentsOnly(transactions); // Show only payments
                    break;
                case "R":
                    reportsMenu(); // Go to reports menu
                    break;
                case "H":
                    return; // Return to Home Menu
                default:
                    System.out.println("Invalid option - Please try again.");
                    break;
            }
        }
    }


    // --------- Display Functions ---------
    public static void displayTransactions(ArrayList<Transaction> transactions) {
        System.out.println("\n--- All Transactions ---\n");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public static void depositsOnly(ArrayList<Transaction> transactions) {
        System.out.println("\n--- All Deposits ---");
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }
    }

    public static void paymentsOnly(ArrayList<Transaction> transactions) {
        System.out.println("\n--- All Payments ---");
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }

    // --------- Reports Menu ---------
    public static void reportsMenu() {
        ArrayList<Transaction> transactions = readTransactions();

        while (true) {
            System.out.println("\n--- Reports Menu ---");
            System.out.println("1) Month-to-Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year-to-Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String choice = ConsoleHelper.promptForString("Choose an option");

            switch (choice.trim()) {
                case "1":
                    monthToDate(transactions);
                    break;
                case "2":
                    previousMonth(transactions);
                    break;
                case "3":
                    yearToDate(transactions);
                    break;
                case "4":
                    previousYear(transactions);
                    break;
                case "5":
                    searchByVendor(transactions);
                    break;
                case "0":
                    return; // Return to Ledger Menu
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }


    // --------- Report Functions ---------
    public static void monthToDate(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Month-to-Date Report ---");
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        for (Transaction t : transactions) {
            LocalDate date = t.getDate();
            if (date.getMonthValue() == currentMonth && date.getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }

    public static void previousMonth(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Previous Month Report ---");
        int prevMonth = LocalDate.now().getMonthValue() - 1;
        int currentYear = LocalDate.now().getYear();

        if (prevMonth == 0) { // Handle January case
            prevMonth = 12;
            currentYear -= 1;
        }

        for (Transaction t : transactions) {
            LocalDate date = t.getDate();
            if (date.getMonthValue() == prevMonth && date.getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }

    public static void yearToDate(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Year-to-Date Report ---");
        int currentYear = LocalDate.now().getYear();

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }

    public static void previousYear(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Previous Year Report ---");
        int prevYear = LocalDate.now().getYear() - 1;

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == prevYear) {
                System.out.println(t);
            }
        }
    }

    public static void searchByVendor(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Search by Vendor ---");
        String vendorSearch = ConsoleHelper.promptForString("Enter Vendor name to search");

        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorSearch)) {
                System.out.println(t);
            }
        }
    }



    // --------- Read Transactions from CSV ---------
    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim(), timeFormatter);
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }

            bufferedReader.close();

            // Sort transactions: newest first by date and time
            transactions.sort((a, b) -> {
                if (a.getDate().equals(b.getDate())) {
                    return b.getTime().compareTo(a.getTime()); // Same date, sort by time
                } else {
                    return b.getDate().compareTo(a.getDate()); // Otherwise sort by date
                }
            });

        } catch (IOException e) {
            System.out.println("Something went wrong reading the file...");
        }

        return transactions;
    }

    // --------- Save Transaction to CSV ---------
    public static void saveEachTransaction(Transaction t) {
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = t.getTime().format(timeFormatter);

            bw.write(String.format("%s|%s|%s|%s|%.2f\n",
                    t.getDate(), formattedTime, t.getDescription(), t.getVendor(), t.getAmount()));

            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }
}