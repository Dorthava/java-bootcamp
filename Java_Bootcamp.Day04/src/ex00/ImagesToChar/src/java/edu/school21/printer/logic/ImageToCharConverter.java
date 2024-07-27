package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class ImageToCharConverter {
    private final char whiteChar;
    private final char blackChar;

    public ImageToCharConverter(char whiteChar, char blackChar) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
    }

    public void convertImageToChar(BufferedImage bufferedImage) {
        for (int i = 0; i != bufferedImage.getHeight(); ++i) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j != bufferedImage.getWidth(); j++) {
                int pixel = bufferedImage.getRGB(j, i);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                if (((red + green + blue) / 3) > 128) line.append(whiteChar);
                else line.append(blackChar);
            }
            System.out.println(line);
        }
    }
}
