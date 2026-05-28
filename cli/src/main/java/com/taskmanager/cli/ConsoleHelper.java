package com.taskmanager.cli;

import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("\n--- Task Planner ---");
        System.out.println("1. Create task");
        System.out.println("2. List all tasks");
        System.out.println("3. Complete task");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    public static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    public static String readLine() {
        return scanner.nextLine();
    }
}