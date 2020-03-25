package com.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ByteReader {

    private static final String CHARSET = "UTF-32";

    static void printBytes(String[] args) {
        if (args.length >= 3) {
            int i = 2;
            try {
                while (i < args.length) {
                    prinBytesFromOneFile(Integer.parseInt(args[1]), args[i++]);
                }
            } catch (NumberFormatException e) {
                System.out.println("The second argument must be an integer\n\n");
                Helper.printHelp();
            }
        } else
            Helper.printInvalidCommandMessage();
    }

    static void prinBytesFromOneFile(int count, String filePath) {
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
}
