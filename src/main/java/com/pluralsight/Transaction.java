package com.pluralsight;

import java.security.PrivateKey;

public class Transaction {
    // This class is the template for each transaction recorded
    // Each transaction should include :)
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;
    // I am not sure why these are squiggly yellow lines, but I will move forward and test my code then come back.

    // ----- Getters -----
    public String getDate() {
        return date;
    }
    public String getTime() {
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

    // Should format how to display the transaction into the console.log
    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
    }



}
