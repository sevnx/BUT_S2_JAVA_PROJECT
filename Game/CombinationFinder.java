package game;

import cards.Card;

import java.util.LinkedList;
import java.util.Queue;

public class CombinationFinder {
    private Card startingSituation;
    private Card goalSituation;
    enum POSSIBLE_COMMANDS{
        KI,LO,NI,MA,SO;
        @Override
        public String toString(){
            return name();
        }
    }
    public CombinationFinder(Card startingSituation, Card goalSituation){
        this.startingSituation=startingSituation;
        this.goalSituation=goalSituation;
    }

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

    public String findCombination(){
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        do{
            String currentCombination = queue.poll();
            for (POSSIBLE_COMMANDS command : POSSIBLE_COMMANDS.values()) {
                if (isCommandPossible(command, currentCombination)){
                    String newCombination = currentCombination + command;
                    Card newCardWithCombination = applyAllCommands(newCombination);
                    if (newCardWithCombination == null)
                        continue;
                    else if (newCardWithCombination.equals(goalSituation))
                        return newCombination;
                    else if (!newCardWithCombination.equals(startingSituation))
                        queue.add(newCombination);
                }
            }
        } while (!queue.isEmpty());
        return null;
    }

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
