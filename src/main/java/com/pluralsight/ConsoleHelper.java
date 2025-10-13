package com.pluralsight;

import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
    public static int promptForInt(String prompt){
        System.out.print(prompt + ": ");
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }
    public static float promptForFloat(String prompt){
        System.out.print(prompt + ": ");
        float result = scanner.nextFloat();
        scanner.nextLine();
        return result;
    }
    public static long promptForLong(String prompt){
        System.out.print(prompt + ": ");
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }
    public static double promptForDouble(String prompt){
        System.out.print(prompt + ": $"); // Made changes from the println, to print. and added the $ symbol to represent money.
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }
}