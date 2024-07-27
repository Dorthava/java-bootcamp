package edu.school21.printer.logic;

import edu.school21.printer.app.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageToCharConverter {
    private final char whiteChar;
    private final char blackChar;

    public ImageToCharConverter(char whiteChar, char blackChar) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
    }

    public void convertImageToChar() {
        try {
            InputStream inputStream = getClass().getResourceAsStream(Main.DEFAULT_IMAGE_PATH);
            if (inputStream == null) return;
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null || bufferedImage.getHeight() != 16 || bufferedImage.getWidth() != 16)
                throw new IOException("The image size must be 16 by 16!");
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
