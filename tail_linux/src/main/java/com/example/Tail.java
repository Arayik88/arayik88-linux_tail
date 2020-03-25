package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Tail {

    public static final String CHARSET = "UTF-32";

    public static void main(String[] args) {

        String command;

        try {
            command = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            command = "";
        }
        switch (command) {

            case "-c":
                printBytes(args);
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


        if (count > queue.size()) {
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
            } catch (NumberFormatException e) {
                System.out.println("The second argument must be an integer\n\n");
                printHelp();
            }
        } else
            printInvalidCommandMessage();
    }

    private static void printBytes(String[] args) {
        if (args.length >= 3) {
            int i = 2;
            try {
                while (i < args.length) {
                    prinBytesFromOneFile(Integer.parseInt(args[1]), args[i++]);
                }
            } catch (NumberFormatException e) {
                System.out.println("The second argument must be an integer\n\n");
                printHelp();
            }
        } else
            printInvalidCommandMessage();
    }

    private static void prinBytesFromOneFile(int count, String filePath) {
        int remainder = count % 4;
        System.out.println("______________________________________________________________________");
        System.out.println("Retrieving " + count + " byte characters from " + "'" + filePath + "'");
        System.out.println("______________________________________________________________________");

        try {
            String text = new String(Files.readAllBytes(Paths.get(filePath)));

            byte[] temp = text.getBytes(CHARSET);

            if (count > temp.length) {
                System.out.println("\nWARNING: There are less bytes in the file " + "'" + filePath + "'" +
                        " than you try to retrieve.\n");
                System.out.println(text);

            } else {

                byte[] tail = Arrays.copyOfRange(temp, temp.length - (count - remainder), temp.length);

                if (remainder != 0) {

                    byte[] firstCharacterBytes = Arrays.copyOfRange(temp, temp.length - 4 - (count - remainder),
                            temp.length - (count - remainder));
                    String firstCharacter = new String(firstCharacterBytes, Charset.forName(CHARSET));

                    System.out.println("\nWARNING: Because the number of bytes you have entered is non dividable" +
                            " by '4' the " + "first character may be misparsed.\nBut in any case the right first " +
                            "character is -> " + firstCharacter + " <-\n");
                }

                System.out.println(new String(tail, Charset.forName(CHARSET)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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
