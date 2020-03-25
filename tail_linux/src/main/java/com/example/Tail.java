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
