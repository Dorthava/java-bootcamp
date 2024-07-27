package edu.school21.printer.logic;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import edu.school21.printer.app.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageToCharConverter {
    private final int whiteColorCode;
    private final int blackColorCode;

    public ImageToCharConverter(int whiteColorCode, int blackColorCode) {
        this.whiteColorCode = whiteColorCode;
        this.blackColorCode = blackColorCode;
    }

    public void convertImageToChar() {
        try {
            InputStream inputStream = getClass().getResourceAsStream(Main.DEFAULT_IMAGE_PATH);
            if (inputStream == null) throw new IOException("inputStream == null!");
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            if (bufferedImage == null) {
                throw new IOException("bufferedImage == null!");
            } else if (bufferedImage.getHeight() != 16 || bufferedImage.getWidth() != 16) {
                throw new IOException("The image size must be 16 by 16!");
            }

            for (int i = 0; i != bufferedImage.getHeight(); ++i) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j != bufferedImage.getWidth(); j++) {
                    int pixel = bufferedImage.getRGB(j, i);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;
                    if (((red + green + blue) / 3) > 128)
                        line.append(Ansi.colorize(" ", Attribute.BACK_COLOR(whiteColorCode)));
                    else
                        line.append(Ansi.colorize(" ", Attribute.BACK_COLOR(blackColorCode)));
                }
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
