package color;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Color.java
 * @brief Class that takes care of the colors.
 */

public class Color {
    public enum ANSI {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        private final String code;

        ANSI(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static String coloredString(ANSI color, String message) {
        return color.getCode() + message + ANSI.RESET.getCode();
    }
}
