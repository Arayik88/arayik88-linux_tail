/*
This class is the starting point for application.
The application allows to read from a file (or files): the last lines or
the last byte characters, depending which method is chosen and prints it in console.
In the whole application all methods are static: almost no OOP is used, because there is no need for that yet
 */

package com.example;

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
                ByteReader.printBytes(args);
                break;

            case "-n":
                StringReader.printLines(args);
                break;

            case "--help":
                Helper.helpAndExit();
                break;

            default:
                Helper.printInvalidCommandMessage();
        }
    }

}
