/*
This class is helper, its methods print help messages
 */

package com.example;

import java.util.Scanner;

public class Helper {

    //prints help message
    static void printHelp() {
        System.out.println("______HELP______\n");

        System.out.println("java -jar Tail.jar '-c' 'num' 'array of file paths' -> prints the last 'num' byte characters\n" +
                "for every file in the array\n");
        System.out.println("java -jar Tail.jar '-n' 'num' 'array of file paths' -> prints the last 'num' lines\n" +
                "for every file in the array\n");
        System.out.println("java -jar Tail.jar '--help' -> prints the help message");
    }

    //prints help message and waits until user types 'exit' for exit
    static void helpAndExit() {
        printHelp();

        System.out.println("\nType 'exit' for exit.");
        Scanner scanner = new Scanner(System.in);

        if (!scanner.nextLine().trim().equalsIgnoreCase("exit"))
            helpAndExit();
    }

    //prints help message when invalid input
    static void printInvalidCommandMessage() {
        System.out.println("Invalid command.\n");
        Helper.printHelp();
        System.exit(1);
    }
}
