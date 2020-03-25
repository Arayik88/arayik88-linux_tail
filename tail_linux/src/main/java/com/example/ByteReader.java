/*
This class is for reading bytes from files parsing to strings and printing
them in the console

 */

package com.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;

public class ByteReader {

    private static final String CHARSET = "UTF-32";

    //prints strings using bytes from one file
    //here is used exactly one charset for encoding-decoding: UTF-32
    //This is because of we don't know what number of bytes has user typed
    //for example UTF-8 uses one byte for encoding when the character can be converted using one byte,
    //but when the character is for example a chinese hieroglyph or a surrogate pair then
    //UTF-8 extends and can use 2 byte. So we are not sure what characters are in the files and
    //what number has user typed. Maybe at this particular moment user has typed 99 for bytes
    //but the encoding uses 2 bytes, so we can't be sure that the most left character is parsed
    //correctly because we need 2 bytes but we have only one byte (99 % 2 = 1). That's why
    //here is used exactly UTF-32, which means that this charset never extends, it always
    //uses 4 bytes or 32 bites for one character
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

    //invokes the printBytes() method many times
    //the count of invocations depends on how many file paths are there
    //when starting the application
    static void prinBytesFromOneFile(int count, String filePath) {
        int remainder = count % 4;

        try {

            String text = new String(Files.readAllBytes(Paths.get(filePath)));

            byte[] temp = text.getBytes(CHARSET);

            System.out.println("______________________________________________________________________");
            System.out.println("Retrieving " + count + " byte characters from " + "'" + filePath + "'");
            System.out.println("______________________________________________________________________");

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
                System.out.println("\n\n");
            }

        } catch (NoSuchFileException e) {
            System.out.println(filePath + "  (No such file or directory)\n");
            Helper.printInvalidCommandMessage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Helper.printInvalidCommandMessage();
        }

    }
}
