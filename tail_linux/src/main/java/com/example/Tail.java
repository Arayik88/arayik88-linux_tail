package com.example;

import java.util.Scanner;

public class Tail {

    public static void main(String[] args) {

        String command;

        try {
            command = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            command = "";
        }
        switch (command) {

            case "-c":
                printBytes();
                break;

            case "-n":
                printLines();
                break;

            case "--help":
                helpAndExit();
                break;

            default:
                System.out.println("Invalid command.\nSee 'java -jar Tail.jar --help'");
        }
    }


    private static void printLines() {

    }

    private static void printBytes() {

    }

    private static void printHelp(){
        System.out.println("Help message.................");
    }

    private static void helpAndExit() {
        printHelp();

        System.out.println("\nType 'exit' for exit.");

        Scanner scanner = new Scanner(System.in);

        while (true){

            if(scanner.nextLine().trim().equalsIgnoreCase("exit"))
                return;
            else
                printHelp();
        }

    }
}
