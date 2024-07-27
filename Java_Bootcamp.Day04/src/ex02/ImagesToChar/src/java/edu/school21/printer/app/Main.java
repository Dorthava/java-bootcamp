package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import edu.school21.printer.logic.ArgsToColorConverter;
import edu.school21.printer.logic.ImageToCharConverter;

public class Main {
    public static final String DEFAULT_IMAGE_PATH = "/resources/it.bmp";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar images-to-chars-printer.jar --white=<color> --black=<color>");
            return;
        }
        ArgsToColorConverter converter = new ArgsToColorConverter();
        JCommander jCommander = JCommander.newBuilder().addObject(converter).build();

        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            jCommander.usage();
            return;
        }

        Integer whiteColorRgb = converter.getWhiteColor();
        Integer blackColorRgb = converter.getBlackColor();
        if (whiteColorRgb == null || blackColorRgb == null) {
            System.err.println("Данного цвета нет в наличии!");
            return;
        }

        ImageToCharConverter imageToCharConverter = new ImageToCharConverter(whiteColorRgb, blackColorRgb);
        imageToCharConverter.convertImageToChar();
    }
}
