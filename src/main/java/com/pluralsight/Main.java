package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) {
        homeMenu(); // Our program starts here immediately calling the home menu first.
    }


    // --------- The Home Menu (Main Loop) ---------
    public static void homeMenu() {
        while (true) {
            System.out.println("\n--------- Welcome to BookKeeper ---------\n");
            System.out.println("\n--- The Home Menu ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option");

            String choice = ConsoleHelper.promptForString("");

            switch (choice.toUpperCase().trim()) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerMenu();
                    break;
                case "X":
                    System.out.println("""
                            \n--------- Thank you for using BookKeeper! ---------
                            
                                              <- Goodbye! ->
                            """);
                    return;
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

        // Records the current date and time;
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        // I moved my line builder down below
        Transaction deposit = new Transaction(today, now, description, vendor, amount);

        saveEachTransaction(deposit);
        System.out.println("Deposit Added: " + deposit);
    }


    // --------- Make Payment --------- I realised I utilized almost the exact same as the Add Deposit and I am thinking further down the line I might simplify this step and add a class to call for repeated steps.
    public static void makePayment() {
        System.out.println("\n--- Make Payment ---");

        String description = ConsoleHelper.promptForString("Description");
        String vendor = ConsoleHelper.promptForString("Vendor");
        double amount = ConsoleHelper.promptForDouble("Amount (numbers only)");

        if (amount <= 0) {
            System.out.println("\nAmount must be greater than $0.00. Deposit cancelled.");
            return;
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        // This builds the csv line so that it can be added to the csv file and read correctly as intended, hopefully this time its working.
        Transaction payment = new Transaction(today, now, description, vendor, -amount);

        saveEachTransaction(payment);
        System.out.println("Payment recorded: " + payment);
    }


    // --------- The Ledger Menu ---------
    public static void ledgerMenu() {

        while (true) {
            System.out.println("\n--- The Ledger Menu ---");
            System.out.println("A) All entries");
            System.out.println("D) Deposits only");
            System.out.println("P) Payments only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option");

            String choice = ConsoleHelper.promptForString("");
            ArrayList<Transaction> transactions = readTransactions();

            switch (choice.toUpperCase().trim()) {
                case "A":
                    displayTransactions(transactions);
                    break;
                case "D":
                    depositsOnly(transactions);
                    break;
                case "P":
                    paymentsOnly(transactions);
                    break;
                case "R":
                    reportsMenu();
                    break;
                case "H":
                    return; // makes it go back once to home.
                default:
                    System.out.println("Invalid option - Please try again.");
                    break;
            }
        }
    }


    public static void displayTransactions(ArrayList<Transaction> transactions) {
        System.out.println("\n--- All Transactions ---\n");
        for (Transaction t : transactions) {
            System.out.println(t);
//            System.out.printf("%s | %s | %s | %s | $%.2f\n",
//                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }
    }

    public static void depositsOnly(ArrayList<Transaction> transactions) {
        System.out.println("\n--- All Deposits ---");
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.println(t);
//                System.out.printf("%s | %s | %s | %s | $%.2f\n",
//                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    public static void paymentsOnly(ArrayList<Transaction> transactions) {
        System.out.println("\n--- All Payments ---");
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.println(t);
//                System.out.printf("%s | %s | %s | %s | $%.2f%n",
//                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    // --------- The Reports Menu --------- (inside the ledger screen)
    public static void reportsMenu() {

        // This reads all transactions from csv, stores them in transactions
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

                // Here I call transactions in the brackets, it sends the list to each case
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
//                case "6":
//                    customSearch(transactions);
//                    break;
                case "0":
                   return; // Goes back to Ledger Menu.
                default:
                    System.out.println("Invalid choice! Please Please try again.");
                    break;
            }
        }
    }

    public static void monthToDate(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Month-to-Date Report ---");

        int currentMonth = LocalDate.now().getMonthValue(); // this converts the month into a number.
        int currentYear = LocalDate.now().getYear(); // gets year

        //start a for loop to go through each transaction that is inside the csv file
        for (Transaction t : transactions) {
            LocalDate date = t.getDate(); // get date of a transaction

            // If the transaction happened in the current month and year then do the following
            if (date.getMonthValue() == currentMonth  &&  date.getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }

    public static void previousMonth(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Previous Month Report ---");

        int prevMonth = LocalDate.now().getMonthValue() - 1; // gets the prev month
        int currentYear = LocalDate.now().getYear();

        // I noticed after a January transaction, this would be a bug and wouldn't print correctly.
        if (prevMonth == 0) { // 1 - January we get 0
            prevMonth = 12; // if prev month is 0 then make prevMonth December
            currentYear -= 1;
        }

        for (Transaction t : transactions) {
            LocalDate date = t.getDate(); // get date of a transaction

            // If the transaction happened in the prev month and current year then do the following
            if (date.getMonthValue() == prevMonth && date.getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }

    public static void yearToDate(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Year-to-Date Report ---");

        int currentYear = LocalDate.now().getYear();

        for (Transaction t : transactions) {
            LocalDate date = t.getDate();

            if (date.getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }

    public static void previousYear(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Previous Year Report ---");

        int prevYear = LocalDate.now().getYear() -1; // gets last year

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == prevYear) { // print only if transactions match prev year...
                System.out.println(t);
            }
        }
    }





    public static void searchByVendor(ArrayList<Transaction> transactions) {
        System.out.println("\n--- Search by Vendor ---");

        String vendorSearch = ConsoleHelper.promptForString("Enter Vendor name to search");

        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorSearch)) { // loop through all transactions, if it matches regardless of casing then print all matched ones.
                System.out.println(t);
            }
        }
    }




    // First to make it here in Main then to move it to a different class to keep this Main.java clean
    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");      // This opens the csv file
            BufferedReader bufferedReader = new BufferedReader(fileReader);  // This reads each line in a faster way

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");  // Splits everything into each piece by the | line symbol

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                if (parts.length == 5) { // checks if the line has 5 different parts.
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim(), timeFormatter);
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }

            bufferedReader.close();

            // This sorts the csv from newest to oldest
            transactions.sort((a, b) -> {
                if (a.getDate().equals(b.getDate())) { // If both transactions happened on the same date
                    return b.getTime().compareTo(a.getTime()); // then compare for the time, printing earliest first
                }
                else {
                    return b.getDate().compareTo(a.getDate()); // Otherwise sort them by their date
                }
            });


        } catch (IOException e) {
            System.out.println("Something went wrong reading the file...");
        }
        return transactions;
    }
    // I'm thinking of moving this entire method^^ to a different class maybe names file manager or file reader, that way my main will look much cleaner.


    // Started making this method so that everything is a little cleaner up top and reduce repeated lines of code.
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
            System.out.println("Error saving transaction: ");
        }
    }

      // --------- Lastly polish up and clean code where it can be cleaned ---------

}