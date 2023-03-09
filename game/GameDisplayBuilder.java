package game;

import color.Color;
import podium.Animal;

import static podium.Podium.BOTTOM_ROW;
import static podium.Podium.MIDDLE_ROW;
import static podium.Podium.TOP_ROW;

/**
 * Class that builds the game display for each turn.
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 */
public class GameDisplayBuilder {
    /** String representing the red podium name. */
    private static final String RED_PODIUM_STRING = "ROUGE";
    /** String representing the blue podium name. */
    private static final String BLUE_PODIUM_STRING = "BLEU";
    /** Associated game. */
    private final Game game;
    /** Size of a column. */
    private static final int COLUMN_SIZE = 10;
    /** Size of the separator (between start and goal situation). */
    private static final int SEPARATOR_SIZE = 6;
    /** Number of columns. */
    private static final int NUMBER_OF_COLUMNS = 4;

    /**
     * Constructor of the class.
     * @param game Associated game.
     */
    GameDisplayBuilder(Game game){
        this.game = game;
    }

    /**
     * Utility function that centers a string of a certain width
     * @param string string to center
     * @param width width of the string
     * @return centered string
     */
    private String centerString(String string, int width) {
        String out = String.format("%"+width+"s%s%"+width+"s", "",string,"");
        float mid = (out.length()/2);
        float start = mid - (width/2);
        float end = start + width;
        return out.substring((int)start, (int)end);
    }

    /**
     * Gets the display of the game, after calling all the sub-functions.
     * @return String representation of the game.
     */
    public String getDisplay(){
        return getRow(TOP_ROW)+System.lineSeparator()+
                getRow(MIDDLE_ROW)+System.lineSeparator()+
                getRow(BOTTOM_ROW)+System.lineSeparator()+
                getSepartorRow()+System.lineSeparator()+
                getPodiumColorRow()+System.lineSeparator()+
                getFooterRow()+System.lineSeparator()+
                getSupportedCommands();
    }

    /**
     * Gets one of the rows of all the podium by index.
     * @param index index of the row to get
     * @return String representation of the row.
     */
    private String getRow(int index) {
        Animal stratingBlueAnimal = game.getStartingSituation().getBlue().getAnimalByIndex(index);
        Animal stratingRedAnimal = game.getStartingSituation().getRed().getAnimalByIndex(index);
        Animal goalBlueAnimal = game.getGoalSituation().getBlue().getAnimalByIndex(index);
        Animal goalRedAnimal = game.getGoalSituation().getRed().getAnimalByIndex(index);
        return getColoredAnimalString(stratingBlueAnimal)+
                getColoredAnimalString(stratingRedAnimal)+
                String.format("%" + SEPARATOR_SIZE + "s", " ")+
                getColoredAnimalString(goalBlueAnimal)+
                getColoredAnimalString(goalRedAnimal);
    }

    private String getColoredAnimalString(Animal animal){
        if (animal == null)
            return centerString("",COLUMN_SIZE);
        return Color.coloredString(animal.getColor(), centerString(animal.toString(),COLUMN_SIZE));
    }

    /**
     * Gets the separator row of the display, displayed under the rows and above the colors of the podium.
     * @return String representation of the separator row.
     */
    private String getSepartorRow(){
        return centerString("----",COLUMN_SIZE)+
                centerString("----",COLUMN_SIZE)+
                centerString("==>",SEPARATOR_SIZE)+
                centerString("----",COLUMN_SIZE)+
                centerString("----",COLUMN_SIZE);
    }

    /**
     * Gets the podium color row of the display, displayed under the separator row.
     * @return String representation of the podium color row.
     */
    private String getPodiumColorRow(){
        /* We have to color after centering the string, because the color code is counted in the string length,
        making the string not properly centered.*/
        return Color.coloredString(Color.ANSI.BLUE, centerString(BLUE_PODIUM_STRING,COLUMN_SIZE)) +
                Color.coloredString(Color.ANSI.RED, centerString(RED_PODIUM_STRING,COLUMN_SIZE))+
                String.format("%" + SEPARATOR_SIZE + "s", " ")+
                Color.coloredString(Color.ANSI.BLUE, centerString(BLUE_PODIUM_STRING,COLUMN_SIZE))+
                Color.coloredString(Color.ANSI.RED, centerString(RED_PODIUM_STRING,COLUMN_SIZE));
    }

    /**
     * Gets the supported commands row of the display, displayed under the podium color row.
     * @return String representation of the supported commands row.
     */
    private String getSupportedCommands() {
        String coloredStringRed = Color.coloredString(Color.ANSI.RED, RED_PODIUM_STRING);
        String coloredStringBlue = Color.coloredString(Color.ANSI.BLUE, BLUE_PODIUM_STRING);
        return "KI : " + coloredStringBlue + " --> " + coloredStringRed +
                "\t\t" +
                "NI : " + coloredStringBlue + " ^" +
                System.lineSeparator() +
                "LO : " + coloredStringBlue + " <-- " + coloredStringRed +
                "\t\t" +
                "MA : " + coloredStringRed + " ^" +
                System.lineSeparator() +
                "SO : " + coloredStringBlue + " <-> " + coloredStringRed;
    }

    /**
     * Gets the footer row of the display, displayed under the supported commands row.
     * @return String representation of the footer row.
     */
    private String getFooterRow(){
        return new String(new char[SEPARATOR_SIZE+COLUMN_SIZE*NUMBER_OF_COLUMNS]).replace("\0", "-");
    }
}