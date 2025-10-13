package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = readTransactions();

        System.out.println("Transactions found in file:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

    }

    // --------- The Home Menu ---------
    public static void homeMenu () {

    }







    // --------- Make CSV File ---------
    // what if I make a transactions class, to assist me in the printing of what a transaction is supposed to look like. First to make it here in Main then to move it to a different class to keep this Main.java clean

    // --------- Main Method for the brains of the transactions, how it can read and write. Taking inspo form SearchInventory project ---------
    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");      // This opens the csv file
            BufferedReader bufferedReader = new BufferedReader(fileReader);  // This reads each line in a faster way

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");  // Splits everything into each piece by the | line symbok

                if (parts.length == 5) { // checks if the line has 5 different parts.
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        }   catch (IOException e) {
            System.out.println("Something went wrong reading the file...");
        } return transactions;
    }
// I'm thinking of moving this entire method^^ to a different class maybe names file manager or file reader, that way my main will look much cleaner.







    // --------- The Ledger Screen ---------
    public static void ledgerScreen () {

    }

    // --------- The Reports Screen ---------
    public static void reportsScreen () {

    }

    // --------- Lastly polish up and clean code where it can be cleaned ---------
}