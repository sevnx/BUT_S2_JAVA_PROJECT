package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.CombinationFinder.java
 * @brief Class finding the shortest combination of commands to get from a starting situation to a goal situation.
 */

import cards.Card;

import java.util.LinkedList;
import java.util.Queue;

public class CombinationFinder {
    /** The starting situation */
    private final Card startingSituation;
    /** The goal situation that we find the combination for*/
    private final Card goalSituation;

    /**
     * Enum representing the possible commands in the game
     */
    public enum POSSIBLE_COMMANDS{
        KI,LO,NI,MA,SO;
        /**
         * String representation of the enum
         * @return associated string
         */
        @Override
        public String toString(){
            return name();
        }
    }

    /**
     * Constructor of the class
     * @param startingSituation the starting situation
     * @param goalSituation the goal situation
     */
    public CombinationFinder(Card startingSituation, Card goalSituation){
        this.startingSituation=startingSituation;
        this.goalSituation=goalSituation;
    }

    /**
     * Applies all commands in the combination to the starting situation, and returns the resulting copy cards
     * @param combination the combination of commands to apply
     * @return the resulting copy cards after applying all commands in the combination to the starting situation,
     * or null if the combination is invalid (throws an exception)
     */
    public Card applyAllCommands(String combination){
        try {
            Card temp = new Card(startingSituation);
            for (String subCommand : combination.split("(?<=\\G.{2})"))
                if (subCommand.length() == 2)
                    temp.executeCommand(subCommand);
            return temp;
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * Finds the shortest combination of commands to get from the starting situation to the goal situation
     * @return the shortest combination of commands to get from the starting situation to the goal situation
     */
    public String findCombination(){
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        do{
            String currentCombination = queue.poll();
            for (POSSIBLE_COMMANDS command : POSSIBLE_COMMANDS.values()) {
                if (isCommandPossible(command, currentCombination)){
                    String newCombination = currentCombination + command;
                    Card newCardWithCombination = applyAllCommands(newCombination);
                    if (newCardWithCombination != null) {
                        if (newCardWithCombination.equals(goalSituation))
                            return newCombination;
                        else if (!newCardWithCombination.equals(startingSituation))
                            queue.add(newCombination);
                    }
                }
            }
        } while (!queue.isEmpty());
        return null;
    }

    /**
     * Checks if the command is possible to apply on the current combination
     * @param command to check
     * @param currentCombination to check
     * @return true if the command is possible to apply on the current combination, false otherwise
     */
    public boolean isCommandPossible(POSSIBLE_COMMANDS command, String currentCombination){
        Card temp;
        if (currentCombination.length()>2)
            temp=applyAllCommands(currentCombination);
        else
            temp=startingSituation;
        switch (command){
            case KI:
                return temp.canKI();
            case LO:
                return temp.canLO();
            case NI:
                return temp.canNI();
            case MA:
                return temp.canMA();
            case SO:
                return temp.canSO();
            default:
                return false;
        }
    }
}
