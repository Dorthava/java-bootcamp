package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToCharConverter;

public class Main {
    public static final String DEFAULT_IMAGE_PATH = "/resources/it.bmp";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar images-to-chars-printer.jar --white=<color> --black=<color>");
            return;
        }
        if (args[0].length() != 1 || args[1].length() != 1) {
            System.out.println("One character must be transmitted");
            return;
        }
        char whiteChar = args[0].charAt(0);
        char blackChar = args[1].charAt(0);
        ImageToCharConverter imageToCharConverter = new ImageToCharConverter(whiteChar, blackChar);
        imageToCharConverter.convertImageToChar();
    }
}
