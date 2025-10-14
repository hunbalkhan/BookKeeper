package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

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

    // Should format how to display the transaction into the console.log
    @Override
    public String toString() {
        //return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
        return String.format("%s | %s | %s | %s | $%.2f", date, time, description, vendor, amount);
    }

//    public String toEncodedString(){
//
//    }



}
