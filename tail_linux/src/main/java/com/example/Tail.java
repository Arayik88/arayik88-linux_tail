package com.example;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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
                printLines(args);
                break;

            case "--help":
                helpAndExit();
                break;

            default:
                printInvalidCommandMessage();
        }
    }

    private static void printInvalidCommandMessage() {
        System.out.println("Invalid command.");
        printHelp();
    }

    private static void printLinesFromOneFile(int count, String filePath) {
        LinkedList<String> queue = new LinkedList<>();

        File file = new File(filePath);

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNext()) {

                queue.add(sc.nextLine());

                if (queue.size() > count) {
                    queue.poll();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Retrieving " + count + " lines from " + "'" + filePath + "'");
        System.out.println("------------------------------------------------------------");


        if(count > queue.size()){
            System.out.println("\nWARNING: There are less lines in the file " + "'" + filePath + "'" +
                    " than you try to retrieve.\n");
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    private static void printLines(String[] args) {
        if (args.length >= 3) {
            int i = 2;
            try {
                while (i < args.length) {
                    printLinesFromOneFile(Integer.parseInt(args[1]), args[i]);
                    ++i;
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        } else
            printInvalidCommandMessage();
    }

    private static void printBytes() {

    }

    private static void printHelp() {
        System.out.println("Help message.................");
    }

    private static void helpAndExit() {
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
}
