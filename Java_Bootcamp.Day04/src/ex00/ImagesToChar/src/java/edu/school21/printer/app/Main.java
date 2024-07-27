package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToCharConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(args.length);
            System.out.println("Usage: java Main <image_path> <char_for_white> <char_for_black>");
            return;
        }
        if (args[1].length() != 1 || args[2].length() != 1 || !args[0].matches("^.*\\.bmp$")) {
            System.out.println("One character must be transmitted or incorrect format");
            return;
        }
        String imagePath = args[0];
        char whiteChar = args[1].charAt(0);
        char blackChar = args[2].charAt(0);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            if (bufferedImage.getHeight() != 16 && bufferedImage.getWidth() != 16)
                throw new IOException("The image size must be 16 by 16!");
            ImageToCharConverter imageToCharConverter = new ImageToCharConverter(whiteChar, blackChar);
            imageToCharConverter.convertImageToChar(bufferedImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
