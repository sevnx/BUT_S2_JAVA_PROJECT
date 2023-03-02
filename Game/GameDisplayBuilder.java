package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.GameDisplayBuilder.java
 * @brief Class that builds the game display for each turn.
 */

import color.Color;

import static podium.Podium.BOTTOM_ROW;
import static podium.Podium.MIDDLE_ROW;
import static podium.Podium.TOP_ROW;

public class GameDisplayBuilder {
    private static final String RED_PODIUM_STRING = "ROUGE";
    private static final String BLUE_PODIUM_STRING = "BLEU";
    private final Game game;
    private static final int COLUMN_SIZE = 10;
    private static final int SEPARATOR_SIZE = 6;
    private static final int NUMBER_OF_COLUMNS = 4;

    GameDisplayBuilder(Game game){
        this.game = game;
    }

    private String centerString(String s, int len) {
        String out = String.format("%"+len+"s%s%"+len+"s", "",s,"");
        float mid = (out.length()/2);
        float start = mid - (len/2);
        float end = start + len;
        return out.substring((int)start, (int)end);
    }

    public String getDisplay(){
        return getRow(TOP_ROW)+System.lineSeparator()+
                getRow(MIDDLE_ROW)+System.lineSeparator()+
                getRow(BOTTOM_ROW)+System.lineSeparator()+
                getSepartorRow()+System.lineSeparator()+
                getPodiumColorRow()+System.lineSeparator()+
                getFooterRow()+System.lineSeparator()+
                getSupportedCommands();
    }

    private String getRow(int index) {
        return centerString(game.getCurrentSituation().getBlue().getAnimalStringByIndex(index),COLUMN_SIZE)+
                centerString(game.getCurrentSituation().getRed().getAnimalStringByIndex(index),COLUMN_SIZE)+
                String.format("%" + SEPARATOR_SIZE + "s", " ")+
                centerString(game.getGoalSituation().getBlue().getAnimalStringByIndex(index),COLUMN_SIZE)+
                centerString(game.getGoalSituation().getRed().getAnimalStringByIndex(index),COLUMN_SIZE);
    }

    private String getSepartorRow(){
        return centerString("----",COLUMN_SIZE)+
                centerString("----",COLUMN_SIZE)+
                centerString("==>",SEPARATOR_SIZE)+
                centerString("----",COLUMN_SIZE)+
                centerString("----",COLUMN_SIZE);
    }

    private String getPodiumColorRow(){
        return Color.coloredString(Color.ANSI.BLUE, centerString(BLUE_PODIUM_STRING,COLUMN_SIZE)) +
                Color.coloredString(Color.ANSI.RED, centerString(RED_PODIUM_STRING,COLUMN_SIZE))+
                String.format("%" + SEPARATOR_SIZE + "s", " ")+
                Color.coloredString(Color.ANSI.BLUE, centerString(BLUE_PODIUM_STRING,COLUMN_SIZE))+
                Color.coloredString(Color.ANSI.RED, centerString(RED_PODIUM_STRING,COLUMN_SIZE));
    }

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

    private String getFooterRow(){
        return new String(new char[SEPARATOR_SIZE+COLUMN_SIZE*NUMBER_OF_COLUMNS]).replace("\0", "-");
    }
}