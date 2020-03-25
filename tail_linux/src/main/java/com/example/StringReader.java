/*
This class is for reading strings from files
 */

package com.example;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class StringReader {

    //prints lines from one file
    static void printLinesFromOneFile(int count, String filePath) {
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
            System.out.println(e.getMessage());
            Helper.printInvalidCommandMessage();
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
        System.out.println("\n\n");
    }

    //invokes the printLinesFromOneFile() method many times
    //the count of invocations depends on how many file paths are there
    //when starting the application
    static void printLines(String[] args) {
        if (args.length >= 3) {
            int i = 2;
            try {
                while (i < args.length) {
                    printLinesFromOneFile(Integer.parseInt(args[1]), args[i]);
                    ++i;
                }
            } catch (NumberFormatException e) {
                System.out.println("The second argument must be an integer\n\n");
                Helper.printHelp();
            }
        } else
            Helper.printInvalidCommandMessage();
    }
}
