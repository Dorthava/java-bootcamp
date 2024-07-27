package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.HashMap;
import java.util.Map;

@Parameters(separators = "=")
public class ArgsToColorConverter {
    private static final Map<String, Integer> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put("BLACK", 0);
        COLOR_MAP.put("RED", 1);
        COLOR_MAP.put("GREEN", 2);
        COLOR_MAP.put("YELLOW", 3);
        COLOR_MAP.put("BLUE", 4);
        COLOR_MAP.put("MAGENTA", 5);
        COLOR_MAP.put("CYAN", 6);
        COLOR_MAP.put("WHITE", 7);
    }

    @Parameter(names = {"--white"})
    private String whiteColor;

    @Parameter(names = {"--black"})
    private String blackColor;

    public Integer getWhiteColor() {
        return COLOR_MAP.get(whiteColor);
    }

    public Integer getBlackColor() {
        return COLOR_MAP.get(blackColor);
    }
}
