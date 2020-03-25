package com.example;

import java.util.Scanner;

public class Helper {

    static void printHelp() {
        System.out.println("Help message.................");
    }

    static void helpAndExit() {
        printHelp();

        System.out.println("\nType 'exit' for exit.");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            if (scanner.nextLine().trim().equalsIgnoreCase("exit"))
                return;
            else
                printHelp();
        }

    }

    static void printInvalidCommandMessage() {
        System.out.println("Invalid command.");
        Helper.printHelp();
    }
}
