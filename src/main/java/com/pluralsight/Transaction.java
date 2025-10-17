package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    // This class is the template for each transaction recorded
    // Each transaction should include :)
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;
    // I am not sure why these are squiggly yellow lines, but I will move forward and test my code then come back.

    // ----- Getters -----
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public String getDescription() {
        return description;
    }
    public String getVendor() {
        return vendor;
    }
    public double getAmount() {
        return amount;
    }


    // calculating manually to center a text for the user
    private String centerText(String text, int width) {

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        String result = ""; // empty box

        for (int i = 0; i < left; i++) {
            result += " ";
        }
        result += text;

        for (int i = 0; i < right; i++) {
            result += " ";
        }
        return result;
    }

    // Should format how to display the transaction into the console.log
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);

        String formattedAmount = String.format("$%.2f", amount);

        return centerText(date.toString(), 14) + "|" +
                centerText(formattedTime, 12) + "|" +
                centerText(description, 28) + "|" +
                centerText(vendor, 25) + "|" +
                centerText(formattedAmount, 12) + "|";
    }

    // Old format
    /*
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);

        return String.format("%-12s | %-10s | %-23s | %20s | $%10.2f", date, formattedTime, description, vendor, amount);
    }
    */

}
