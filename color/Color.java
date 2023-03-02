package color;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Color.java
 * @brief Class that takes care of the colors.
 */
public class Color {
    public enum ANSI {
        /** ANSI reset code. */
        RESET("\u001B[0m"),
        /** ANSI black color code. */
        BLACK("\u001B[30m"),
        /** ANSI red color code. */
        RED("\u001B[31m"),
        /** ANSI green color code. */
        GREEN("\u001B[32m"),
        /** ANSI yellow color code. */
        YELLOW("\u001B[33m"),
        /** ANSI blue color code. */
        BLUE("\u001B[34m"),
        /** ANSI purple color code. */
        PURPLE("\u001B[35m"),
        /** ANSI cyan color code. */
        CYAN("\u001B[36m"),
        /** ANSI white color code. */
        WHITE("\u001B[37m");

        /** ANSI code. */
        private final String code;

        /**
         * Constructor of the enum class.
         * @param code ANSI code of the color.
         */
        ANSI(String code) {
            this.code = code;
        }

        /**
         * Getter of the code attribute.
         * @return code
         */
        public String getCode() {
            return code;
        }
    }

    /**
     * Utility function that returns a colored string.
     * @param color Color of the string.
     * @param message String to color.
     * @return Colored string.
     */
    public static String coloredString(ANSI color, String message) {
        return color.getCode() + message + ANSI.RESET.getCode();
    }
}
