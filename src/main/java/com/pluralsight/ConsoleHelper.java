package com.pluralsight;

import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        String input = "";
        while (input.isEmpty()) {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
        return input;
    }
//    public static int promptForInt(String prompt){
//        System.out.print(prompt + ": ");
//        int result = scanner.nextInt();
//        scanner.nextLine();
//        return result;
//    }
//    public static float promptForFloat(String prompt){
//        System.out.print(prompt + ": ");
//        float result = scanner.nextFloat();
//        scanner.nextLine();
//        return result;
//    }
//    public static long promptForLong(String prompt){
//        System.out.print(prompt + ": ");
//        long result = scanner.nextLong();
//        scanner.nextLine();
//        return result;
//    }
    public static double promptForDouble(String prompt){
        double result = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt + ": $");  // Keep your $ symbol
            String input = scanner.nextLine().trim(); // Read input as String

            try {
                result = Double.parseDouble(input); // Try converting to double
                valid = true;                        // Success!
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number."); // Error message
            }
        }

        return result;
    }
}